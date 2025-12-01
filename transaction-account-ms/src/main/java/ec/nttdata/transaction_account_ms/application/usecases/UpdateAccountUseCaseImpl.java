package ec.nttdata.transaction_account_ms.application.usecases;

import ec.nttdata.transaction_account_ms.application.port.in.usecases.UpdateAccountUseCase;
import ec.nttdata.transaction_account_ms.application.port.out.repositories.AccountRepository;
import ec.nttdata.transaction_account_ms.domain.models.Account;
import ec.nttdata.transaction_account_ms.domain.result.Result;
import ec.nttdata.transaction_account_ms.domain.result.ResultError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UpdateAccountUseCaseImpl implements UpdateAccountUseCase {

    private final AccountRepository accountRepository;

    @Override
    public Result<Account> execute(Long id, Account account) {
        Optional<Account> oldOptional = accountRepository.findById(id);

        if(oldOptional.isPresent()) {
            Account old = oldOptional.get();

            Account save = accountRepository.save(build(id, account, old));
            return new Result.Success<>(save);
        }

        return new Result.Failure<>(new ResultError("1", "Account not found"));
    }

    private Account build(Long id, Account account, Account old) {

        Long setId = account.getId() != null ? account.getId() : id;

        return account.toBuilder()
                .number(old.getNumber())
                .id(setId)
                .build();
    }
}
