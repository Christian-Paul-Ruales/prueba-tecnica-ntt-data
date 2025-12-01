package ec.nttdata.transaction_account_ms.application.port.in.usecases;

import ec.nttdata.transaction_account_ms.application.commands.CreateMovementCommand;
import ec.nttdata.transaction_account_ms.domain.models.Movement;
import ec.nttdata.transaction_account_ms.domain.result.Result;

public interface CreateMovementUseCase {
    Result<Movement> execute(CreateMovementCommand command);
}
