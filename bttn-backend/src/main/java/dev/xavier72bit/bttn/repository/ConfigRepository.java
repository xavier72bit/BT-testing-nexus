package dev.xavier72bit.bttn.repository;

import dev.xavier72bit.bttn.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConfigRepository extends JpaRepository<Config, Long> {}
