package dev.xavier72bit.bttn.interaction.client;

import dev.xavier72bit.bttn.model.entity.Miner;
import dev.xavier72bit.bttn.model.vo.MinerDebugResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MinerDebugClient {
    @Autowired
    private RestTemplate restTemplate;

    public MinerDebugResponse startMining(Miner miner) {
        String url = miner.getApiAddress() + "/mining";
        return restTemplate.getForObject(url, MinerDebugResponse.class);
    }
}
