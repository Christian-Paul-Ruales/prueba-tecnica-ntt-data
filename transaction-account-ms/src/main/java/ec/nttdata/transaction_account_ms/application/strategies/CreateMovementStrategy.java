package ec.nttdata.transaction_account_ms.application.strategies;

import ec.nttdata.transaction_account_ms.application.commands.CreateMovementProcessorCommand;
import ec.nttdata.transaction_account_ms.domain.constants.MovementType;
import ec.nttdata.transaction_account_ms.domain.models.Movement;
import ec.nttdata.transaction_account_ms.domain.result.Result;

public interface CreateMovementStrategy {
    Result<Movement> execute(CreateMovementProcessorCommand command);
    MovementType getType();
}
