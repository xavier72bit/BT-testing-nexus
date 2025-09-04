package dev.xavier72bit.bttn.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "bttn_nodes")
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "api_address", unique = true)
    private String apiAddress;

    @NotNull
    @Column(name = "is_online")
    private Boolean isOnline;
}
