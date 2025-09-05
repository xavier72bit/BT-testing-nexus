package dev.xavier72bit.bttn.repository;

import dev.xavier72bit.bttn.model.entity.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionRepository extends JpaRepository<Version, Long> {}
