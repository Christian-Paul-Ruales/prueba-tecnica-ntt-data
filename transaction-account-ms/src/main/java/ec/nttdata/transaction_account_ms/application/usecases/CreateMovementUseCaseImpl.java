package ec.nttdata.transaction_account_ms.application.usecases;

import ec.nttdata.transaction_account_ms.application.commands.CreateMovementCommand;
import ec.nttdata.transaction_account_ms.application.factory.CreateMovementFactory;
import ec.nttdata.transaction_account_ms.application.port.in.usecases.CreateMovementUseCase;
import ec.nttdata.transaction_account_ms.application.port.out.repositories.AccountRepository;
import ec.nttdata.transaction_account_ms.domain.models.Account;
import ec.nttdata.transaction_account_ms.domain.models.Movement;
import ec.nttdata.transaction_account_ms.domain.result.Result;
import ec.nttdata.transaction_account_ms.domain.result.ResultError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class CreateMovementUseCaseImpl implements CreateMovementUseCase {

    private final AccountRepository accountRepository;
    private final CreateMovementFactory factory;


    @Override
    public Result<Movement> execute(CreateMovementCommand command) {
        Optional<Account> optionalAccount = accountRepository.findById(
                command.account());

        if(optionalAccount.isEmpty()) {
            return new Result.Failure<>(
                    new ResultError("2", "Cannot get account [%s]".formatted(
                            command.account()
                    ))
            );
        }
        Account account = optionalAccount.get();

        return factory.execute(account, command.type(), command.movementValue());

    }
}
