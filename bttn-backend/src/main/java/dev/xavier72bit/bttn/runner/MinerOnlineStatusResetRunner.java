package dev.xavier72bit.bttn.runner;

import dev.xavier72bit.bttn.repository.MinerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MinerOnlineStatusResetRunner implements ApplicationRunner {
    @Autowired
    private MinerRepository minerRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("执行矿工状态初始化，全部设置为离线");
        int effectedRow = minerRepository.setAllMinerOffline();
        log.info("初始化完成，{} 个矿工已设置为下线状态", effectedRow);
    }
}
