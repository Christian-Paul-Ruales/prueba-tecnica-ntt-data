package ec.nttdata.transaction_account_ms.application.port.in.usecases;

import ec.nttdata.transaction_account_ms.application.commands.CreateReportCommand;
import ec.nttdata.transaction_account_ms.domain.models.Account;
import ec.nttdata.transaction_account_ms.domain.result.Result;

import java.util.List;

public interface CreateReportAccountMovementsUseCase {
    Result<List<Account>> execute(CreateReportCommand command);
}
