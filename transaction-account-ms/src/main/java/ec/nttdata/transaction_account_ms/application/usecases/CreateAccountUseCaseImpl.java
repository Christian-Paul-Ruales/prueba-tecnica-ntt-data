package ec.nttdata.transaction_account_ms.application.usecases;

import ec.nttdata.transaction_account_ms.application.port.in.usecases.CreateAccountUseCase;
import ec.nttdata.transaction_account_ms.application.port.out.repositories.AccountRepository;
import ec.nttdata.transaction_account_ms.domain.models.Account;
import ec.nttdata.transaction_account_ms.domain.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateAccountUseCaseImpl implements CreateAccountUseCase {

    private final AccountRepository accountRepository;

    @Override
    public Result<Account> execute(Account account) {
        Account save = accountRepository.save(account);

        return new Result.Success<>(save);
    }
}
