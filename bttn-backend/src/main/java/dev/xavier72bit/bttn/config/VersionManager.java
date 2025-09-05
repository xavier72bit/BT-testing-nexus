package dev.xavier72bit.bttn.config;

import dev.xavier72bit.bttn.model.entity.Version;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

@Component
public class VersionManager {
    private final AtomicReference<Version> currentVersion = new AtomicReference<>();

    public Version getCurrentVersion() {
        return currentVersion.get();
    }

    public void setCurrentVersion(Version version) {
        currentVersion.set(version);
    }
}
