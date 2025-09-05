package dev.xavier72bit.bttn.model.entity;

import dev.xavier72bit.bttn.model.entity.base.WithVersionBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name = "bttn_nodes", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"api_address", "version_id"})
})
public class Node extends WithVersionBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "api_address")
    private String apiAddress;

    @NotNull
    @Column(name = "is_online")
    private Boolean isOnline;
}
