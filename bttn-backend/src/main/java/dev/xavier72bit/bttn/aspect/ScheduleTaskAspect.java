package dev.xavier72bit.bttn.aspect;


import dev.xavier72bit.bttn.config.RunnerCompletionManager;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ScheduleTaskAspect {
    @Autowired
    private RunnerCompletionManager runnerCompletionManager;

    /**
     *
     * @param pjp 被拦截的方法的封装
     */
    @Around("@annotation(dev.xavier72bit.bttn.annotation.WaitForRunners)")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        if (!runnerCompletionManager.getRunnersComplete()) {
            log.info("Runner尚未执行完毕, 跳过本轮执行: {}", pjp.getTarget().getClass().getName());
            return null;
        }
        return pjp.proceed();
    }
 }
