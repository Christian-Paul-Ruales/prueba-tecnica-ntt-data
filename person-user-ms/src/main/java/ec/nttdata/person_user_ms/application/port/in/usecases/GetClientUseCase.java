package ec.nttdata.person_user_ms.application.port.in.usecases;

import ec.nttdata.person_user_ms.domain.models.Client;
import ec.nttdata.person_user_ms.domain.result.Result;

public interface GetClientUseCase {
    Result<Client> execute(Long id);
}
