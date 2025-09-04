package dev.xavier72bit.bttn.runner;

import dev.xavier72bit.bttn.repository.NodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class NodeOnlineStatusResetRunner implements ApplicationRunner {
    @Autowired
    private NodeRepository nodeRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("执行节点状态初始化，全部设置为离线");
        int effectedRow = nodeRepository.setAllNodeOffline();
        log.info("初始化完成，{} 个节点已设置为下线状态", effectedRow);
    }
}
