package ec.nttdata.transaction_account_ms.application.usecases;

import ec.nttdata.transaction_account_ms.application.port.in.usecases.GetMovementUseCase;
import ec.nttdata.transaction_account_ms.application.port.out.repositories.MovementRepository;
import ec.nttdata.transaction_account_ms.domain.models.Movement;
import ec.nttdata.transaction_account_ms.domain.result.Result;
import ec.nttdata.transaction_account_ms.domain.result.ResultError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GetMovementUseCaseImpl implements GetMovementUseCase {

    private final MovementRepository movementRepository;

    @Override
    public Result<Movement> execute(Long id) {
        Optional<Movement> optional = movementRepository.findById(id);

        return optional.isPresent() ?
                new Result.Success<>(optional.get())
                : new Result.Failure<>(new ResultError("1", "Cannot get client [%s]".formatted(id)));
    }
}
