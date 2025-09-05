package dev.xavier72bit.bttn.controller;


import dev.xavier72bit.bttn.model.entity.Miner;
import dev.xavier72bit.bttn.model.entity.Node;
import dev.xavier72bit.bttn.model.entity.Wallet;
import dev.xavier72bit.bttn.model.server.R;
import dev.xavier72bit.bttn.service.MinerService;
import dev.xavier72bit.bttn.service.NodeService;
import dev.xavier72bit.bttn.service.WalletService;
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
    private MinerService minerService;
    @Autowired
    private NodeService nodeService;
    @Autowired
    private WalletService walletService;

    @PostMapping("/node")
    public R<Node> registryNode(@RequestBody Node node) {
        try {
            Node result = nodeService.upsertNode(node);
            return R.ok(result);
        } catch (Exception e) {
            return R.error("Node注册失败: {}" + e.getMessage());
        }
    }

    @PostMapping("/wallet")
    public R<Wallet> registryWallet(@RequestBody Wallet wallet) {
        try {
            Wallet result = walletService.upsertWallet(wallet);
            return R.ok(result);
        } catch (Exception e) {
            return R.error("Wallet注册失败" + e.getMessage());
        }
    }

    @PostMapping("/miner")
    public R<Miner> registryMiner(@RequestBody Miner miner) {
        try {
            Miner result = minerService.upsertMiner(miner);
            return R.ok(result);
        } catch (Exception e) {
            return R.error("Miner注册失败" + e.getMessage());
        }
    }
}
