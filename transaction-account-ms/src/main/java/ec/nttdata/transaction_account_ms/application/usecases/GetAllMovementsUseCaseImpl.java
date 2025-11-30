package ec.nttdata.transaction_account_ms.application.usecases;

import ec.nttdata.transaction_account_ms.application.port.in.usecases.GetAllMovementsUseCase;
import ec.nttdata.transaction_account_ms.application.port.out.repositories.MovementRepository;
import ec.nttdata.transaction_account_ms.domain.models.Movement;
import ec.nttdata.transaction_account_ms.domain.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GetAllMovementsUseCaseImpl implements GetAllMovementsUseCase {

    private final MovementRepository movementRepository;

    @Override
    public Result<List<Movement>> execute(Long id) {

        return new Result.Success<>(movementRepository.findAll());
    }
}
