package dev.xavier72bit.bttn.model.entity;


import dev.xavier72bit.bttn.model.entity.base.WithVersionBase;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "bttn_txpool_snapshot")
public class TransactionPoolSnapshot extends WithVersionBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long TransactionAmount;

    @Column
    private Long ConfirmedTransactionAmount;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "node_id", nullable = false)
    private Node node;
}
