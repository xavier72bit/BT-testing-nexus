package dev.xavier72bit.bttn.repository;

import dev.xavier72bit.bttn.model.entity.TransactionPoolSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionPoolSnapshotRepository extends JpaRepository<TransactionPoolSnapshot, Long> {}
