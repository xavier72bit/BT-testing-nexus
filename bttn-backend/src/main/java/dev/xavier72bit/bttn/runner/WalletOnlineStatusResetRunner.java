package dev.xavier72bit.bttn.runner;

import dev.xavier72bit.bttn.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WalletOnlineStatusResetRunner implements ApplicationRunner {
    @Autowired
    private WalletRepository walletRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("执行钱包状态初始化, 全部设置为离线");
        int effectedRow = walletRepository.setAllWalletOffline();
        log.info("初始化完成, {} 个钱包已设置为下线状态", effectedRow);
    }
}
