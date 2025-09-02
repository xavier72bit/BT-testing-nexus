package dev.xavier72bit.bttn.interaction.client;

import dev.xavier72bit.bttn.model.entity.Node;
import dev.xavier72bit.bttn.model.vo.BlockChainSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class BlockChainDebugClient {
    private final RestTemplate restTemplate;

    @Autowired
    public BlockChainDebugClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BlockChainSummary getBlockChainSummary(Node node) {
        String url = node.getApiAddress() + "/blockchain/summary";
        return restTemplate.getForObject(url, BlockChainSummary.class);
    }
}
