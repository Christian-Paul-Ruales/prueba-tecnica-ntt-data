package ec.nttdata.transaction_account_ms.application.usecases;

import ec.nttdata.transaction_account_ms.application.port.in.usecases.CreateAccountUseCase;
import ec.nttdata.transaction_account_ms.application.port.out.repositories.AccountRepository;
import ec.nttdata.transaction_account_ms.domain.models.Account;
import ec.nttdata.transaction_account_ms.domain.result.Result;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CreateAccountUseCaseImpl implements CreateAccountUseCase {

    private final AccountRepository accountRepository;

    private Random random;

    @PostConstruct
    private void init() {
        this.random = new Random();
    }

    @Override
    public Result<Account> execute(Account account) {
        String clientIdString = account
                .getClientId()
                .toString();

        String accountNumber = clientIdString.concat(
                generateAccount(10 - clientIdString.length())
        );
        Account modified = account.toBuilder()
                .number(accountNumber)
                .status(true)
                .build();

        Account save = accountRepository.save(modified);

        return new Result.Success<>(save);
    }

    private String generateAccount(int length) {
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < length; i++) {
            code.append(random.nextInt(10));
        }

        return code.toString();
    }
}
