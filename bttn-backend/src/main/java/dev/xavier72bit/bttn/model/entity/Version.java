package dev.xavier72bit.bttn.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "bttn_version")
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="version_name")
    private String versionName;

    @NotNull
    @Column(name="create_time")
    private LocalDateTime createTime;
}
