package dev.xavier72bit.bttn.interaction.task;

import dev.xavier72bit.bttn.annotation.WaitForRunners;
import dev.xavier72bit.bttn.interaction.base.ScheduledTaskToRun;
import dev.xavier72bit.bttn.interaction.client.BlockChainDebugClient;
import dev.xavier72bit.bttn.model.entity.BlockChainSnapshot;
import dev.xavier72bit.bttn.model.entity.Node;
import dev.xavier72bit.bttn.model.vo.BlockChainSummary;
import dev.xavier72bit.bttn.repository.BlockChainSnapshotRepository;
import dev.xavier72bit.bttn.repository.NodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class BlockChainSnapShotTask implements ScheduledTaskToRun {

    @Autowired
    private NodeRepository nodeRepository;

    @Autowired
    private BlockChainSnapshotRepository blockChainSnapshotRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BlockChainDebugClient blockChainDebugClient;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Override
    @WaitForRunners
    public void execute() {
        log.info("开始执行BlockChain Summary Snapshot采集任务");
        List<Node> onlineNodes = nodeRepository.findByIsOnlineTrue();

        if (onlineNodes.isEmpty()) {
            log.info("目前没有在线的Node，跳过执行");
            return;
        }

        // 线程之间的同步工具：让主线程等待所有子线程执行完再继续
        CountDownLatch latch = new CountDownLatch(onlineNodes.size());

        // 每个节点一个线程，并发获取并入库
        for (Node node : onlineNodes) {
            executorService.submit(() -> {
                try {
                    fetchAndSaveSnapshot(node);
                } catch (Exception e) {
                    log.error("节点 {} 的快照获取失败", node.getApiAddress(), e);
                } finally {
                    latch.countDown();
                }
            });
        }
    }

    private void fetchAndSaveSnapshot(Node node) {
        BlockChainSummary blockChainSummary = blockChainDebugClient.getBlockChainSummary(node);

        if (blockChainSummary != null) {
            BlockChainSnapshot blockChainSnapshot = new BlockChainSnapshot();

            blockChainSnapshot.setChainLength(blockChainSummary.totalLength());
            blockChainSnapshot.setTotalDifficulty(blockChainSummary.totalDifficulty());
            String lastBlockHash = blockChainSummary.blocks().isEmpty()
                    ? null
                    : blockChainSummary.blocks().get(blockChainSummary.blocks().size() - 1).hash();
            blockChainSnapshot.setLastBlockHash(lastBlockHash);

            blockChainSnapshot.setSnapshotTime(LocalDateTime.now());
            blockChainSnapshot.setNode(node);

            blockChainSnapshotRepository.save(blockChainSnapshot);

            log.info("节点 {} 保存快照成功", node.getApiAddress());
        }
    }
}
