package dev.xavier72bit.bttn.runner;

import dev.xavier72bit.bttn.config.RunnerCompletionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(Integer.MIN_VALUE)  // 确保最后一个执行
public class RunnerCompletionNotifier implements ApplicationRunner {
    @Autowired
    private RunnerCompletionManager runnerCompletionManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        runnerCompletionManager.setRunnersCompleted();
    }
}
