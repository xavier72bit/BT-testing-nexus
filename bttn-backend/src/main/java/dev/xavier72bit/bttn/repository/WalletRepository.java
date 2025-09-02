package dev.xavier72bit.bttn.repository;

import dev.xavier72bit.bttn.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Wallet findByPublicKeyAndPrivateKey(String publicKey, String privateKey);
}
