package dev.xavier72bit.bttn.repository;

import dev.xavier72bit.bttn.model.entity.BlockChainSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlockChainSnapshotRepository extends JpaRepository<BlockChainSnapshot, Long> {}
