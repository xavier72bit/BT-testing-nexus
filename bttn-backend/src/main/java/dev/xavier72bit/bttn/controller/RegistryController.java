package dev.xavier72bit.bttn.controller;


import dev.xavier72bit.bttn.model.entity.Miner;
import dev.xavier72bit.bttn.model.entity.Node;
import dev.xavier72bit.bttn.model.entity.Wallet;
import dev.xavier72bit.bttn.repository.MinerRepository;
import dev.xavier72bit.bttn.repository.NodeRepository;
import dev.xavier72bit.bttn.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/registry")
public class RegistryController {
    @Autowired
    private MinerRepository minerRepository;
    @Autowired
    private NodeRepository nodeRepository;
    @Autowired
    private WalletRepository walletRepository;

    @PostMapping("/node")
    public Node registryNode(@RequestBody Node node) {
        return nodeRepository.save(node);
    }

    @PostMapping("/wallet")
    public Wallet registryWallet(@RequestBody Wallet wallet) {
        log.info("wallet {} 注册", wallet.getApiAddress());
        wallet.setIsOnline(true);
        return walletRepository.save(wallet);
    }

    @PostMapping("/miner")
    public Miner registryMiner(@RequestBody Miner miner) {
        log.info("miner {} 注册", miner.getApiAddress());
        miner.setIsOnline(true);
        return minerRepository.save(miner);
    }
}
