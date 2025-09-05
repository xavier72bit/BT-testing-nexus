package dev.xavier72bit.bttn.interaction.task;

import dev.xavier72bit.bttn.annotation.WaitForRunners;
import dev.xavier72bit.bttn.interaction.base.ScheduledTaskToRun;
import dev.xavier72bit.bttn.interaction.client.WalletDebugClient;
import dev.xavier72bit.bttn.model.entity.Transaction;
import dev.xavier72bit.bttn.model.entity.Wallet;
import dev.xavier72bit.bttn.model.vo.WalletDebugResponse;
import dev.xavier72bit.bttn.service.TransactionService;
import dev.xavier72bit.bttn.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Random;

/**
 * 创建区块链交易的任务
 */

@Component
@Slf4j
public class CreateTransactionTask implements ScheduledTaskToRun {
    @Autowired
    private WalletService walletService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private WalletDebugClient walletDebugClient;

    private final Random random = new Random();

    @Override
    @WaitForRunners
    public void execute() {
        Long amount = random.nextLong(100) + 1;

        Wallet sender = walletService.getRandomWalletByCurrentVersionOnline();
        Wallet recipient = walletService.getRandomWalletByCurrentVersion();

        if (sender == null || recipient == null) {
            log.info("未获取到sender与recipient，跳过本次交易数据生成");
            return;
        }

        WalletDebugResponse walletDebugResponse = walletDebugClient.generateTx(sender, recipient, amount);

        if (! walletDebugResponse.success()) {
            log.info("调用Wallet debug API失败, 跳过本次交易数据生成");
            return;
        }

        Transaction generatedTransaction = new Transaction();
        generatedTransaction.setSender(sender);
        generatedTransaction.setRecipient(recipient);
        generatedTransaction.setAmount(amount);

        transactionService.create(generatedTransaction);
    }
}
