package dev.xavier72bit.bttn.interaction.client;

import dev.xavier72bit.bttn.model.entity.Wallet;
import dev.xavier72bit.bttn.model.vo.WalletDebugResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 连接Wallet的Debug API
 */
@Component
public class WalletDebugClient {
    private final RestTemplate restTemplate;

    @Autowired
    public WalletDebugClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WalletDebugResponse generateTx(Wallet fromWallet, Wallet toWallet, Long amount) {
        String url = fromWallet.getApiAddress() + "/generate_tx";

        Map<String, Object> requestBody = Map.of(
                "raddr", toWallet.getPublicKey(),
                "amount", amount
        );

        return restTemplate.postForObject(url, requestBody, WalletDebugResponse.class);
    }
}
