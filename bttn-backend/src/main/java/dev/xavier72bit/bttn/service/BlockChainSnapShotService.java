package dev.xavier72bit.bttn.service;

import dev.xavier72bit.bttn.config.VersionManager;
import dev.xavier72bit.bttn.model.entity.BlockChainSnapshot;
import dev.xavier72bit.bttn.model.entity.Version;
import dev.xavier72bit.bttn.repository.BlockChainSnapshotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class BlockChainSnapShotService {
    @Autowired
    private BlockChainSnapshotRepository blockChainSnapshotRepository;
    @Autowired
    private VersionManager versionManager;

    @Transactional
    public BlockChainSnapshot create(BlockChainSnapshot blockChainSnapshot) {
        Version currentVersion = versionManager.getCurrentVersion();
        blockChainSnapshot.setVersion(currentVersion);
        return blockChainSnapshotRepository.save(blockChainSnapshot);
    }
}
