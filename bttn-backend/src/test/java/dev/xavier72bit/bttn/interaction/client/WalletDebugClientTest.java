package dev.xavier72bit.bttn.interaction.client;

import dev.xavier72bit.bttn.model.entity.Wallet;
import dev.xavier72bit.bttn.model.vo.WalletDebugResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

/**
 * 框架的测试，需要测试的代码与main下的代码处于同一棵树上，也就是包名一致。<br/>
 * 此时WalletDebugClient能自动注入
 */
class WalletDebugClientTest {

    private WalletDebugClient walletDebugClient;
    private MockRestServiceServer mockServer;

    @BeforeEach
    void setUp() {
        RestTemplate restTemplate = new RestTemplate();
        mockServer = MockRestServiceServer.createServer(restTemplate);

        walletDebugClient = new WalletDebugClient(restTemplate);
    }

    @Test
    void generateTx_shouldReturnWalletDebugResponse() {
        String jsonResponse = """
        {
          "data": {
            "error_type": 12,
            "message": "余额不足",
            "success": false
          },
          "message": "成功调用钱包的`generate_transaction`",
          "success": true
        }
        """;

        Wallet fromWallet = new Wallet();
        fromWallet.setApiAddress("http://localhost:10096/generate_transaction");

        Wallet toWallet = new Wallet();
        toWallet.setAddress("abcde");

        mockServer.expect(requestTo(fromWallet.getApiAddress()))
                .andExpect(method(org.springframework.http.HttpMethod.POST))
                .andRespond(withSuccess(jsonResponse, MediaType.APPLICATION_JSON));

        WalletDebugResponse response = walletDebugClient.generateTx(fromWallet, toWallet, 123);

        assertThat(response).isNotNull();
        assertThat(response.success()).isTrue();
        assertThat(response.message()).contains("成功调用");
        assertThat(response.data().errorType()).isEqualTo(12);
        assertThat(response.data().message()).isEqualTo("余额不足");
        assertThat(response.data().success()).isFalse();

        mockServer.verify();
    }
}
