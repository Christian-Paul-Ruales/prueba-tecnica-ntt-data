package ec.nttdata.person_user_ms.application.port.out.clients;

import ec.nttdata.person_user_ms.infrastructure.dtos.AccountRequestDTO;
import ec.nttdata.person_user_ms.infrastructure.dtos.AccountResponseDTO;
import ec.nttdata.person_user_ms.infrastructure.dtos.MovementRequestDTO;
import ec.nttdata.person_user_ms.infrastructure.dtos.MovementResponseDTO;
import reactor.core.publisher.Mono;

public interface TransactionAccountPort {
    Mono<AccountResponseDTO> createAccountPerson(AccountRequestDTO request);
    Mono<MovementResponseDTO> createMovement(MovementRequestDTO request);
}
