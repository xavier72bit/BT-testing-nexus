package dev.xavier72bit.bttn.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记Scheduler的Task，让它等到所有的Spring Runner执行完毕后，再执行Scheduler的Task
 * TODO: 加上运行时的检查，仅ScheduledTaskToRun的execute方法可附加此注解
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WaitForRunners {}
