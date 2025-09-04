package dev.xavier72bit.bttn.interaction.scheduler;

import dev.xavier72bit.bttn.constant.ConfigKey;
import dev.xavier72bit.bttn.interaction.base.DynamicConfigScheduler;
import dev.xavier72bit.bttn.interaction.task.BlockChainSnapShotTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


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
