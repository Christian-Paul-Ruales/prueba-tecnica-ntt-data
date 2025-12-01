package ec.nttdata.transaction_account_ms.application.usecases;

import ec.nttdata.transaction_account_ms.application.port.in.usecases.DeleteAccountUseCase;
import ec.nttdata.transaction_account_ms.application.port.in.usecases.DeleteMovementUseCase;
import ec.nttdata.transaction_account_ms.application.port.out.repositories.MovementRepository;
import ec.nttdata.transaction_account_ms.domain.result.Result;
import ec.nttdata.transaction_account_ms.domain.result.ResultError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteMovementUseCaseImpl implements DeleteMovementUseCase {

    private final MovementRepository movementRepository;

    @Override
    public Result<Boolean> execute(Long id) {
        if(movementRepository.existById(id)) {
            movementRepository.deleteById(id);

            return new Result.Success<>(true);
        }

        return new Result.Failure<>(new ResultError("1", "Account not found"));
    }

}
