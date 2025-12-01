package ec.nttdata.transaction_account_ms;

import com.fasterxml.jackson.databind.ObjectMapper;
import ec.nttdata.transaction_account_ms.domain.constants.AccountType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Unit test for create account
 * */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {
        "CONFIG_SERVER_HOST=localhost",
        "CONFIG_SERVER_USER=configuser",
        "CONFIG_SERVER_PASS=configpass",
        "SPRING_EUREKA_HOST=localhost",
        "SPRING_EUREKA_PASSWORD=eurekapass123",
        "SPRING_EUREKA_PORT=8761",
        "SPRING_CONFIG_HOST=localhost",
        "SPRING_PROFILES_ACTIVE=default"
        })
@AutoConfigureWebTestClient
class AccountControllerIntegrationTest {
    @Autowired
    private WebTestClient webTestClient;



    @Test
    @DisplayName("Test create account success")
    void createAccount_shouldSuccess() throws Exception {
        webTestClient.post()
                .uri("/v1/accounts")
                .bodyValue(ConstantsTest.ACCOUNT_REQUEST)
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody()
                .jsonPath("$.type")
                .isEqualTo(AccountType.AHORROS.getCode());

    }



}
