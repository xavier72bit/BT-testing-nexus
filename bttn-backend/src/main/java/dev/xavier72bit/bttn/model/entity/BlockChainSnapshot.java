package dev.xavier72bit.bttn.model.entity;


import dev.xavier72bit.bttn.model.entity.base.WithVersionBase;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 区块链的概要快照数据
 */
@Entity
@Data
@Table(name = "bttn_blockchain_snapshot")
public class BlockChainSnapshot extends WithVersionBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long chainLength;

    @Column
    private Long TotalDifficulty;

    @Column
    private String lastBlockHash;

    @Column
    private LocalDateTime snapshotTime;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "node_id", nullable = false)
    private Node node;
}
