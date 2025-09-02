package dev.xavier72bit.bttn.repository;

import dev.xavier72bit.bttn.model.entity.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface NodeRepository extends JpaRepository<Node, Long> {
    List<Node> findByIsOnlineTrue();

    Node findByApiAddress(String apiAddress);
}
