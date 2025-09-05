package dev.xavier72bit.bttn.interaction.base;

import dev.xavier72bit.bttn.model.entity.Config;
import dev.xavier72bit.bttn.repository.ConfigRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Component
public abstract class DynamicConfigScheduler {
    @Autowired
    private TaskScheduler taskScheduler;
    @Autowired
    private ConfigRepository configRepository;

    private final AtomicLong msInterval = new AtomicLong(60000);

    /**
     *  子类实现，需要执行的具体任务，让子类提供任务的实例
     */
    protected abstract ScheduledTaskToRun getScheduledTaskToRun();

    /**
     * 子类实现，需要到数据库Config表里查询的key
     */
    protected abstract String getConfigKey();

    @PostConstruct
    public void init() {
        log.info("启动动态定时调度器, 初始间隔：{} ms", msInterval.get());

        // 仅调度一次，生成task，然后绑定一个持久的Trigger，通过修改Trigger，来实现动态变化间隔时间
        taskScheduler.schedule(this::executeTaskAndRefreshInterval, getDynamicTrigger());
    }

    private void executeTaskAndRefreshInterval() {
        try {
            getScheduledTaskToRun().execute();
        } catch (Exception e) {
            log.error("任务执行失败", e);
        } finally {
            updateIntervalFromConfig();
        }
    }

    private Trigger getDynamicTrigger() {
        return new Trigger() {
            @Override
            public Instant nextExecution(TriggerContext triggerContext) {
                Instant lastCompletion = triggerContext.lastCompletion();
                if (lastCompletion == null) {
                    return Instant.now();
                }

                long currentInterval = msInterval.get();
                return lastCompletion.plusMillis(currentInterval);
            }
        };
    }

    private void updateIntervalFromConfig() {
        Optional<Config> configOpt = configRepository.findByKey(getConfigKey());
        configOpt.ifPresent(config -> {
            try {
                long newInterval = Long.parseLong(config.getValue());

                if (newInterval != msInterval.get())   // 基础数据类型，栈上比值
                {
                    log.info("任务执行间隔发生变化，{} -> {} ms", msInterval.get(), newInterval);
                    msInterval.set(newInterval);
                }
            } catch (Exception e) {
                log.error("设置时间间隔失败, 维持 {} ms",msInterval.get() ,e);
            }
        });
    }
}
