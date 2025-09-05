package dev.xavier72bit.bttn.interaction.task;

import dev.xavier72bit.bttn.annotation.WaitForRunners;
import dev.xavier72bit.bttn.config.VersionManager;
import dev.xavier72bit.bttn.interaction.base.ScheduledTaskToRun;
import dev.xavier72bit.bttn.interaction.client.MinerDebugClient;
import dev.xavier72bit.bttn.model.entity.Miner;
import dev.xavier72bit.bttn.model.entity.Version;
import dev.xavier72bit.bttn.model.vo.MinerDebugResponse;
import dev.xavier72bit.bttn.repository.MinerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
@Slf4j
public class MiningTask implements ScheduledTaskToRun {
    @Autowired
    private MinerDebugClient minerDebugClient;

    @Autowired
    private MinerRepository minerRepository;

    @Autowired
    private VersionManager versionManager;

    private final ExecutorService executorService = Executors.newFixedThreadPool(5);

    @Override
    @WaitForRunners
    public void execute() {
        Version version = versionManager.getCurrentVersion();
        List<Miner> onlineMiner = minerRepository.findByIsOnlineTrueAndVersion(version);

        if (onlineMiner.isEmpty()) {
            log.info("当前没有在线的Node, 跳过执行，查询版本ID: {}", version.getId());
            return;
        }

        // 线程之间的同步工具：让主线程等待所有子线程执行完再继续
        CountDownLatch latch = new CountDownLatch(onlineMiner.size());

        for (Miner miner: onlineMiner) {
            executorService.submit(() -> {
                try {
                    MinerDebugResponse minerDebugResponse = minerDebugClient.startMining(miner);
                    if (minerDebugResponse.success()) {
                        log.info("已通知{}开始挖矿", miner.getApiAddress());
                    } else {
                        log.error("通知{}失败", miner.getApiAddress());
                    }
                } catch (Exception e) {
                    log.error("通知{}挖矿出现错误", miner.getApiAddress());
                } finally {
                    latch.countDown();
                }
            });
        }
    }
}
