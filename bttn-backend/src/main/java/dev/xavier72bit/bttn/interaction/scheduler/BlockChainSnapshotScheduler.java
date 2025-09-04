package dev.xavier72bit.bttn.interaction.scheduler;

import dev.xavier72bit.bttn.common.ConfigKey;
import dev.xavier72bit.bttn.interaction.base.DynamicConfigScheduler;
import dev.xavier72bit.bttn.interaction.client.BlockChainDebugClient;
import dev.xavier72bit.bttn.interaction.task.BlockChainSnapShotTask;
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
public class BlockChainSnapshotScheduler extends DynamicConfigScheduler {
    @Autowired
    private BlockChainSnapShotTask blockChainSnapShotTask;

    @Override
    protected String getConfigKey() {
        return ConfigKey.BLOCKCHAIN_SUMMARY_COLLECT_RATE.getKeyName();
    }

    @Override
    protected BlockChainSnapShotTask getScheduledTaskToRun() {
        return blockChainSnapShotTask;
        
    }
}
