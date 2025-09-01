package dev.xavier72bit.bttn.testing.client;

import dev.xavier72bit.bttn.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * 连接Wallet的Debug API
 */
@Component
public class WalletDebugClient {
    private final RestTemplate restTemplate;
    private final WalletRepository walletRepository;

    @Autowired
    public WalletDebugClient(RestTemplate restTemplate, WalletRepository walletRepository) {
        this.restTemplate = restTemplate;
        this.walletRepository = walletRepository;
    }
}
