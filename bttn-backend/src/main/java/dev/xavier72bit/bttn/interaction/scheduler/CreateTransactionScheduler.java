package dev.xavier72bit.bttn.interaction.scheduler;

import dev.xavier72bit.bttn.constant.ConfigKey;
import dev.xavier72bit.bttn.interaction.base.DynamicConfigScheduler;
import dev.xavier72bit.bttn.interaction.base.ScheduledTaskToRun;
import dev.xavier72bit.bttn.interaction.task.CreateTransactionTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateTransactionScheduler extends DynamicConfigScheduler {
    @Autowired
    private CreateTransactionTask createTransactionTask;

    @Override
    protected CreateTransactionTask getScheduledTaskToRun() {
        return createTransactionTask;
    }

    @Override
    protected String getConfigKey() {
        return ConfigKey.TRANSACTION_GENERATION_RATE.getKeyName();
    }
}
