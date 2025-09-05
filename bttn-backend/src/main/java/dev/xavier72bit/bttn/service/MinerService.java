package dev.xavier72bit.bttn.service;

import dev.xavier72bit.bttn.config.VersionManager;
import dev.xavier72bit.bttn.model.entity.Miner;
import dev.xavier72bit.bttn.model.entity.Version;
import dev.xavier72bit.bttn.repository.MinerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MinerService {
    @Autowired
    private MinerRepository minerRepository;
    @Autowired
    private VersionManager versionManager;

    /**
     * 同一版本下，相同的apiAddress视为同一个Miner
     */
    @Transactional
    public Miner upsertMiner(Miner miner) {
        Version currentVersion = versionManager.getCurrentVersion();

        Miner existedMiner = minerRepository.findByApiAddressAndVersion(miner.getApiAddress(), currentVersion);
        if (existedMiner != null) {
            existedMiner.setPublicKey(miner.getPublicKey());  // 对于存在的Miner, 是需要更新它的publicKey的
            existedMiner.setIsOnline(true);
            return minerRepository.save(existedMiner);
        }

        miner.setIsOnline(true);
        miner.setVersion(currentVersion);
        return minerRepository.save(miner);
    }
}
