package dev.xavier72bit.bttn.model.entity.base;

import dev.xavier72bit.bttn.model.entity.Version;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass // 不实例化，需要子类继承
public class WithVersionBase {
    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "version_id", nullable = false)
    private Version version;
}
