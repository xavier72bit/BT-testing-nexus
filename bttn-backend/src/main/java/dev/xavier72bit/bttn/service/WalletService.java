package dev.xavier72bit.bttn.service;

import dev.xavier72bit.bttn.model.entity.Wallet;
import dev.xavier72bit.bttn.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class WalletService {
    @Autowired
    private WalletRepository walletRepository;

    @Transactional
    public Wallet upsertWallet(Wallet wallet) {
        Wallet existedWallet = walletRepository.findByPublicKeyAndPrivateKey(wallet.getPublicKey(), wallet.getPrivateKey());
        if (existedWallet != null) {
            existedWallet.setIsOnline(true);
            return walletRepository.save(existedWallet);
        }

        wallet.setIsOnline(true);
        return walletRepository.save(wallet);
    }
}
