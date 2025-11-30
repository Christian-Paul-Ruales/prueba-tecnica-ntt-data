package ec.nttdata.transaction_account_ms.application.port.in.usecases;

import ec.nttdata.transaction_account_ms.domain.result.Result;

public interface DeleteMovementUseCase {
    Result<Boolean> execute(Long id);
}
