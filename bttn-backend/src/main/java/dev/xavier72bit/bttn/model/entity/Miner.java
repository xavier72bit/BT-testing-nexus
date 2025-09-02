package dev.xavier72bit.bttn.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "bttn_miners")
public class Miner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "public_key")
    private String publicKey;

    @NotNull
    @Column(name = "api_address")
    private String apiAddress;

    @NotNull
    @Column(name = "is_online")
    private Boolean isOnline;
}
