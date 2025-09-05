package dev.xavier72bit.bttn.repository;

import dev.xavier72bit.bttn.model.entity.Miner;
import dev.xavier72bit.bttn.model.entity.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MinerRepository extends JpaRepository<Miner, Long> {
    List<Miner> findByApiAddress(String apiAddress);

    @Transactional
    @Modifying
    @Query("UPDATE Miner m SET m.isOnline = false")
    int setAllMinerOffline();

    Miner findByApiAddressAndVersion(String string, Version version);
}
