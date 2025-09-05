package dev.xavier72bit.bttn.model.entity;

import dev.xavier72bit.bttn.model.entity.base.WithVersionBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "bttn_wallets")
public class Wallet extends WithVersionBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "public_key")
    private String publicKey;

    @NotNull
    @Column(name = "private_key")
    private String privateKey;

    @NotNull
    @Column(name = "api_address")
    private String apiAddress;

    @NotNull
    @Column(name = "is_online")
    private Boolean isOnline;

    @NotNull
    @Column(name = "balance")
    private Long balance;
}
