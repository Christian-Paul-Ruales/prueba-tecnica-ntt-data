package ec.nttdata.person_user_ms.infrastructure.adaptars.out.client;

import ec.nttdata.person_user_ms.application.port.out.clients.TransactionAccountPort;
import ec.nttdata.person_user_ms.infrastructure.dtos.AccountRequestDTO;
import ec.nttdata.person_user_ms.infrastructure.dtos.AccountResponseDTO;
import ec.nttdata.person_user_ms.infrastructure.dtos.MovementRequestDTO;
import ec.nttdata.person_user_ms.infrastructure.dtos.MovementResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@RequiredArgsConstructor
@Component
public class TransactionAccountAdapter implements TransactionAccountPort {
    private final WebClient webClient;

    /**
     * Create account
     * **/
    @Override
    public Mono<AccountResponseDTO> createAccountPerson(AccountRequestDTO request) {
        return webClient.post()
                .uri("/transaction-account-ms/api/v1/accounts")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(AccountResponseDTO.class)
                .timeout(Duration.ofSeconds(4))
                .doOnSuccess(resp -> log.info(" --- Creation proccess success {}", resp))
                .doOnError(err ->  log.error(" --- Error creating account: ", err));
    }

    /**
     * Create movement
     * */
    @Override
    public Mono<MovementResponseDTO> createMovement(MovementRequestDTO request) {
        return webClient.post()
                .uri("/transaction-account-ms/api/v1/movements")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(MovementResponseDTO.class)
                .timeout(Duration.ofSeconds(4))
                .doOnSuccess(resp -> log.info(" --- Creation proccess success {}", resp))
                .doOnError(err ->  log.error(" --- Error creating account: ", err));
    }


}
