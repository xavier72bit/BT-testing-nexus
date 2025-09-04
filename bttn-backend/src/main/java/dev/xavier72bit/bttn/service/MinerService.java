package dev.xavier72bit.bttn.service;

import dev.xavier72bit.bttn.model.entity.Miner;
import dev.xavier72bit.bttn.repository.MinerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MinerService {
    @Autowired
    private MinerRepository minerRepository;

    @Transactional
    public Miner upsertMiner(Miner miner) {
        Miner existedMiner = minerRepository.findByApiAddress(miner.getApiAddress());
        if (existedMiner != null) {
            existedMiner.setPublicKey(miner.getPublicKey());  // 对于存在的Miner, 是需要更新它的publicKey的
            existedMiner.setIsOnline(true);
            return minerRepository.save(existedMiner);
        }

        miner.setIsOnline(true);
        return minerRepository.save(miner);
    }
}
