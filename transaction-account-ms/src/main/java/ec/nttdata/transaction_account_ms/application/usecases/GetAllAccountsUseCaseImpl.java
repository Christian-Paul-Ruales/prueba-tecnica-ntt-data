package ec.nttdata.transaction_account_ms.application.usecases;

import ec.nttdata.transaction_account_ms.application.port.in.usecases.GetAllAccountsUseCase;
import ec.nttdata.transaction_account_ms.application.port.out.repositories.AccountRepository;
import ec.nttdata.transaction_account_ms.domain.models.Account;
import ec.nttdata.transaction_account_ms.domain.result.Result;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class GetAllAccountsUseCaseImpl implements GetAllAccountsUseCase {

    private final AccountRepository accountRepository;

    @Override
    public Result<List<Account>> execute(Long id) {

        return new Result.Success<>(accountRepository.findAll());
    }
}
