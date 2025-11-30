package ec.nttdata.transaction_account_ms.application.usecases;

import ec.nttdata.transaction_account_ms.application.port.in.usecases.CreateAccountUseCase;
import ec.nttdata.transaction_account_ms.application.port.in.usecases.GetAccountUseCase;
import ec.nttdata.transaction_account_ms.application.port.out.repositories.AccountRepository;
import ec.nttdata.transaction_account_ms.domain.models.Account;
import ec.nttdata.transaction_account_ms.domain.result.Result;
import ec.nttdata.transaction_account_ms.domain.result.ResultError;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class GetAccountsUseCaseImpl implements GetAccountUseCase {

    private final AccountRepository accountRepository;

    @Override
    public Result<Account> execute(Long id) {
        Optional<Account> optional = accountRepository.findById(id);

        return optional.isPresent() ?
                new Result.Success<>(optional.get())
                : new Result.Failure<>(new ResultError("1", "Cannot get client [%s]".formatted(id)));
    }
}
