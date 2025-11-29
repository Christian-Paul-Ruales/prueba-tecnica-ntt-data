package ec.nttdata.person_user_ms.application.port.in.usecases;

import ec.nttdata.person_user_ms.domain.models.Client;
import ec.nttdata.person_user_ms.domain.result.Result;

import java.util.List;

public interface GetAllClientsUseCase {
    Result<List<Client>> execute();
}
