package dev.xavier72bit.bttn.repository;

import dev.xavier72bit.bttn.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findByPublicKeyAndPrivateKey(String publicKey, String privateKey);

    @Transactional
    @Modifying
    @Query("UPDATE Wallet w SET w.isOnline = false")
    int setAllWalletOffline();
}
