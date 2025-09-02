package dev.xavier72bit.bttn.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "bttn_nodes")
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "api_address")
    private String apiAddress;

    @NotNull
    @Column(name = "is_online")
    private Boolean isOnline;

    @OneToMany(mappedBy = "node", cascade = CascadeType.ALL)
    private List<BlockChainSnapshot> blockSnapshotList = new ArrayList<>();

    @OneToMany(mappedBy = "node", cascade = CascadeType.ALL)
    private List<TransactionPoolSnapshot> transactionPoolSnapshotList = new ArrayList<>();
}
