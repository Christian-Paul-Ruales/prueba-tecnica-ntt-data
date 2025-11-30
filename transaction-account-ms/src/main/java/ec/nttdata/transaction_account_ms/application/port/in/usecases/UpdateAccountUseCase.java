package ec.nttdata.transaction_account_ms.application.port.in.usecases;

import ec.nttdata.transaction_account_ms.domain.models.Account;
import ec.nttdata.transaction_account_ms.domain.result.Result;

public interface UpdateAccountUseCase {
    Result<Account> execute(Long id, Account account);
}
