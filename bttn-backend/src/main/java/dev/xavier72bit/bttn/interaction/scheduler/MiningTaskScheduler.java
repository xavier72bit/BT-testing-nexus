package dev.xavier72bit.bttn.interaction.scheduler;

import dev.xavier72bit.bttn.constant.ConfigKey;
import dev.xavier72bit.bttn.interaction.base.DynamicConfigScheduler;
import dev.xavier72bit.bttn.interaction.task.MiningTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MiningTaskScheduler extends DynamicConfigScheduler {
    @Autowired
    private MiningTask miningTask;

    @Override
    protected MiningTask getScheduledTaskToRun() {
        return miningTask;
    }

    @Override
    protected String getConfigKey() {
        return ConfigKey.MINER_START_MINING_RATE.getKeyName();
    }
}
