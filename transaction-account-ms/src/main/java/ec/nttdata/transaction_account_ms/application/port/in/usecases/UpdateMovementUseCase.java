package ec.nttdata.transaction_account_ms.application.port.in.usecases;

import ec.nttdata.transaction_account_ms.domain.models.Movement;
import ec.nttdata.transaction_account_ms.domain.result.Result;

public interface UpdateMovementUseCase {
    Result<Movement> execute(Long id, Movement movement);
}
