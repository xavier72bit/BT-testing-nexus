package dev.xavier72bit.bttn.controller;


import dev.xavier72bit.bttn.entity.Node;
import dev.xavier72bit.bttn.repository.MinerRepository;
import dev.xavier72bit.bttn.repository.NodeRepository;
import dev.xavier72bit.bttn.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
