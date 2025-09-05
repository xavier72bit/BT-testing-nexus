package dev.xavier72bit.bttn.repository;

import dev.xavier72bit.bttn.model.entity.Version;
import dev.xavier72bit.bttn.model.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    List<Wallet> findByPublicKeyAndPrivateKey(String publicKey, String privateKey);

    @Transactional
    @Modifying
    @Query("UPDATE Wallet w SET w.isOnline = false")
    int setAllWalletOffline();

    Wallet findByPublicKeyAndPrivateKeyAndVersion(String publicKey, String privateKey, Version version);

    @Query("SELECT w from Wallet w WHERE w.version = :version ORDER BY FUNCTION('RAND') LIMIT 1")
    Wallet findRandomWalletByVersion(@Param("version") Version version);

    @Query("SELECT w from Wallet w WHERE w.version = :version AND w.isOnline = :isOnline ORDER BY FUNCTION('RAND') LIMIT 1")
    Wallet findRandomWalletByVersionAndIsOnlineTrue(@Param("version") Version version, @Param("isOnline") Boolean isOnline);
}
