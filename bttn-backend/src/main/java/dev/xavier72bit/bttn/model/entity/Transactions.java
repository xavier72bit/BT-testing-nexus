package dev.xavier72bit.bttn.model.entity;


import dev.xavier72bit.bttn.model.entity.base.WithVersionBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name="bttn_transactions")
public class Transactions extends WithVersionBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "sender_id")
    private Wallet sender;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "recipient_id")
    private Wallet recipient;

    @NotNull
    @Column(name = "amount")
    private Long amount;
}
