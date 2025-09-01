package dev.xavier72bit.bttn.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "bttn_wallets")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address")
    private String address;

    @Column(name = "private_key")
    private String privateKey;

    @Column(name = "api_address")
    private String apiAddress;
}
