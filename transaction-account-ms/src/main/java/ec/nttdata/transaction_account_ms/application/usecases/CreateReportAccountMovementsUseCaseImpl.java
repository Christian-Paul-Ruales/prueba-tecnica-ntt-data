package ec.nttdata.transaction_account_ms.application.usecases;

import ec.nttdata.transaction_account_ms.application.commands.CreateReportCommand;
import ec.nttdata.transaction_account_ms.application.port.in.usecases.CreateReportAccountMovementsUseCase;
import ec.nttdata.transaction_account_ms.application.port.out.repositories.AccountRepository;
import ec.nttdata.transaction_account_ms.application.port.out.repositories.MovementRepository;
import ec.nttdata.transaction_account_ms.domain.models.Account;
import ec.nttdata.transaction_account_ms.domain.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CreateReportAccountMovementsUseCaseImpl
        implements CreateReportAccountMovementsUseCase {

    private final AccountRepository accountRepository;
    private final MovementRepository movementRepository;

    @Override
    public Result<List<Account>> execute(CreateReportCommand command) {
        List<Account> accountList = accountRepository.findByClientIdAndStatusTrue(
                command.clientId()
        );

        List<Account> accountsWithMovements = accountList.stream().map(
                account -> account.toBuilder()
                        .movements(movementRepository.findByAccountDateTimeBetween(account.getId(), command.start(), command.end()))
                        .build()
        )
        .toList();

        return new Result.Success<>(accountsWithMovements);

    }
}
