package ec.nttdata.transaction_account_ms.application.factory;

import ec.nttdata.transaction_account_ms.application.commands.CreateMovementProcessorCommand;
import ec.nttdata.transaction_account_ms.application.strategies.CreateMovementStrategy;
import ec.nttdata.transaction_account_ms.application.port.out.repositories.AccountRepository;
import ec.nttdata.transaction_account_ms.domain.constants.MovementType;
import ec.nttdata.transaction_account_ms.domain.models.Account;
import ec.nttdata.transaction_account_ms.domain.models.Movement;
import ec.nttdata.transaction_account_ms.domain.result.Result;
import ec.nttdata.transaction_account_ms.domain.result.ResultError;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CreateMovementFactory {

    private final Map<MovementType, CreateMovementStrategy> movements;

    public CreateMovementFactory(List<CreateMovementStrategy> movements, AccountRepository accountRepository) {
        this.movements = movements.stream()
                .collect(Collectors.toMap(
                        CreateMovementStrategy::getType,
                        Function.identity()
                ));
    }

    public Result<Movement> execute(Account account, String type, BigDecimal movementValue) {
        MovementType movementType = MovementType.valueOf(type);
        if(movements.containsKey(movementType)) {
            return movements.get(movementType).execute(
                    new CreateMovementProcessorCommand(account, movementValue)
            );
        }

        return new Result.Failure<>(
                new ResultError("2", "Cannot process transaction [%s]".formatted(
                        type
                ))
        );
    }
}
