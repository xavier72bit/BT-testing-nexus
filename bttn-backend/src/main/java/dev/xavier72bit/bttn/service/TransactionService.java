package dev.xavier72bit.bttn.service;

import dev.xavier72bit.bttn.config.VersionManager;
import dev.xavier72bit.bttn.model.entity.Transaction;
import dev.xavier72bit.bttn.model.entity.Version;
import dev.xavier72bit.bttn.model.entity.Wallet;
import dev.xavier72bit.bttn.repository.TransactionRepository;
import dev.xavier72bit.bttn.repository.WalletRepository;
import jakarta.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Slf4j
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private VersionManager versionManager;

    @Transactional
    public void create(Transaction transaction) {
        Version currentVersion = versionManager.getCurrentVersion();

        Wallet sender = transaction.getSender();
        Wallet recipient = transaction.getRecipient();

        if (sender == null || recipient == null) {
            throw new ValidationException("创建交易必须完整填写发送人与接收人");
        }

        if (Objects.equals(sender.getId(), recipient.getId())) {
            log.info("发送方与接受方一致，跳过交易创建");
            return;
        }

        if (! Objects.equals(sender.getVersion().getId(), currentVersion.getId())
                || ! Objects.equals(recipient.getVersion().getId(), currentVersion.getId())) {
            throw new ValidationException("钱包对象的版本错误");
        }

        Long amount = transaction.getAmount();
        if (sender.getBalance() <= amount) {
            log.error(
                "当前 {}数量的交易：{} -> {} 无效",
                amount, transaction.getSender().getPublicKey(), transaction.getRecipient().getPublicKey()
            );
            transaction.setIsValid(false);
        } else {
            // 验证通过，处理发送方与交易方的余额
            transaction.setIsValid(true);
            sender.setBalance(sender.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);
            walletRepository.save(sender);
            walletRepository.save(recipient);
        }

        transaction.setVersion(currentVersion);
        transactionRepository.save(transaction);
        log.info("创建交易: {}, {} -> {}", amount, transaction.getSender().getPublicKey(), transaction.getRecipient().getPublicKey());
    }
}
