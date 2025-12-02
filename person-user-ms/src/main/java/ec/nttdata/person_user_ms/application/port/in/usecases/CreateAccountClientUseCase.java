package ec.nttdata.person_user_ms.application.port.in.usecases;

import ec.nttdata.person_user_ms.domain.models.Client;
import ec.nttdata.person_user_ms.domain.result.Result;

import java.math.BigDecimal;

public interface CreateAccountClientUseCase {
    Result<Client> execute(Long clientId, String typeAccount, BigDecimal balance);
}
