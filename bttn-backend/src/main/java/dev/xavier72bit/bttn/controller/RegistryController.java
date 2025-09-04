package dev.xavier72bit.bttn.controller;


import dev.xavier72bit.bttn.model.entity.Miner;
import dev.xavier72bit.bttn.model.entity.Node;
import dev.xavier72bit.bttn.model.entity.Wallet;
import dev.xavier72bit.bttn.repository.MinerRepository;
import dev.xavier72bit.bttn.repository.WalletRepository;
import dev.xavier72bit.bttn.service.NodeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// TODO: Controller应该返回统一的R

@Slf4j
@RestController
@RequestMapping("/registry")
public class RegistryController {
    @Autowired
    private MinerRepository minerRepository;
    @Autowired
    private NodeService nodeService;
    @Autowired
    private WalletRepository walletRepository;

    @PostMapping("/node")
    public Node registryNode(@RequestBody Node node) {
        return nodeService.upsertNode(node);
    }

    @PostMapping("/wallet")
    public Wallet registryWallet(@RequestBody Wallet wallet) {
        Wallet existedWallet = walletRepository.findByPublicKeyAndPrivateKey(wallet.getPublicKey(), wallet.getPrivateKey());
        if (existedWallet != null) {
            existedWallet.setIsOnline(true);
            return walletRepository.save(existedWallet);
        }

        log.info("wallet {} 注册", wallet.getApiAddress());
        wallet.setIsOnline(true);
        return walletRepository.save(wallet);
    }

    @PostMapping("/miner")
    public Miner registryMiner(@RequestBody Miner miner) {
        Miner existedMiner = minerRepository.findByApiAddress(miner.getApiAddress());
        if (existedMiner != null) {
            existedMiner.setPublicKey(miner.getPublicKey());  // 对于存在的Miner, 是需要更新它的publicKey的
            existedMiner.setIsOnline(true);
            return minerRepository.save(existedMiner);
        }

        log.info("miner {} 注册", miner.getApiAddress());
        miner.setIsOnline(true);
        return minerRepository.save(miner);
    }
}
