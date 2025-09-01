package dev.xavier72bit.bttn.repository;

import dev.xavier72bit.bttn.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {}
