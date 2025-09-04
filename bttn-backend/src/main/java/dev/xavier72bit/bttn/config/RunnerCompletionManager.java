package dev.xavier72bit.bttn.config;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;


/**
 * @see dev.xavier72bit.bttn.aspect.ScheduleTaskAspect 将支持Schedule任务延迟到Runner执行完之后再执行<br/>
 * 此Manger会被注入到切面中，用来检查状态
 */
@Component
public class RunnerCompletionManager {
    private final AtomicBoolean runnersCompleted = new AtomicBoolean(false);

    public void setRunnersCompleted() {
        runnersCompleted.set(true);
    }

    public boolean getRunnersComplete() {
        return runnersCompleted.get();
    }
}
