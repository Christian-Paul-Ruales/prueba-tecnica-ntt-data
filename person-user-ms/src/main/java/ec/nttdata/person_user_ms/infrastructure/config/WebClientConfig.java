package ec.nttdata.person_user_ms.infrastructure.config;

import io.netty.channel.ChannelOption;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
public class WebClientConfig {

    @Value("${gateway.uri}")
    private String gatewayUri;


    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(gatewayUri)
                .defaultHeader("Content-Type", "application/json")
                .clientConnector(new ReactorClientHttpConnector(
                        HttpClient.create()
                                .responseTimeout(Duration.ofSeconds(3))
                                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 2000)
                ))
                .build();
    }
}
