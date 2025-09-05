package dev.xavier72bit.bttn.runner;

import dev.xavier72bit.bttn.config.VersionManager;
import dev.xavier72bit.bttn.model.entity.Version;
import dev.xavier72bit.bttn.repository.VersionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class VersionInitializerRunner implements ApplicationRunner {
    @Autowired
    private VersionRepository versionRepository;
    @Autowired
    private VersionManager versionManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Version newVersion = new Version();
        newVersion.setCreateTime(LocalDateTime.now());

        Version savedVersion = versionRepository.save(newVersion);
        versionManager.setCurrentVersion(savedVersion);

        log.info("程序启动, 设定Version ID: {}", savedVersion.getId());
    }
}
