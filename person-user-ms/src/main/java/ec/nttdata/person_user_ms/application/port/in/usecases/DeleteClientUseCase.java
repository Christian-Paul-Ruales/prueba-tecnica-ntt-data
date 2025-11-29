package ec.nttdata.person_user_ms.application.port.in.usecases;

import ec.nttdata.person_user_ms.domain.result.Result;

public interface DeleteClientUseCase {
    Result<Boolean> execute(Long id);
}
