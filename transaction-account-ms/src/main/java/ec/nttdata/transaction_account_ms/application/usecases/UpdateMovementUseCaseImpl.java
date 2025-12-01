package ec.nttdata.transaction_account_ms.application.usecases;

import ec.nttdata.transaction_account_ms.application.port.in.usecases.UpdateMovementUseCase;
import ec.nttdata.transaction_account_ms.application.port.out.repositories.MovementRepository;
import ec.nttdata.transaction_account_ms.domain.models.Movement;
import ec.nttdata.transaction_account_ms.domain.result.Result;
import ec.nttdata.transaction_account_ms.domain.result.ResultError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UpdateMovementUseCaseImpl implements UpdateMovementUseCase {

    private final MovementRepository accountRepository;

    @Override
    public Result<Movement> execute(Long id, Movement movement) {
        Optional<Movement> optional = accountRepository.findById(id);
        if(optional.isPresent()) {

            Movement save = accountRepository.save(build(id, movement, optional.get()));
            return new Result.Success<>(save);
        }

        return new Result.Failure<>(new ResultError("1", "Account not found"));
    }


    private Movement build(Long id, Movement movement, Movement old) {

        Long setId = movement.getId() != null ? movement.getId() : id;
        return movement.toBuilder()
                .balance(old.getBalance())
                .dateTime(old.getDateTime())
                .id(setId)
                .build();
    }
}
