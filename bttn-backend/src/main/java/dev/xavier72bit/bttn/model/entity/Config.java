package dev.xavier72bit.bttn.model.entity;


import jakarta.persistence.*;
import lombok.Data;

/**
 * 系统配置，可以实时调整测试执行方式
 */
@Entity
@Data
@Table(name = "bttn_config")
public class Config {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "config_key")
    private String key;

    @Column(name = "config_value")
    private String value;
}
