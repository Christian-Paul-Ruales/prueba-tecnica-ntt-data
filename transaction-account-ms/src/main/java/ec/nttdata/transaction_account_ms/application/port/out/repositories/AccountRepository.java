package ec.nttdata.transaction_account_ms.application.port.out.repositories;

import ec.nttdata.transaction_account_ms.domain.models.Account;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AccountRepository {

    Account save(Account account);
    List<Account> findAll();
    Optional<Account> findById(Long id);
    boolean existById(Long id);
    void deleteById(Long id);
    List<Account> findByClientIdAndStatusTrue(
            Long clientId,
            LocalDateTime start,
            LocalDateTime end
    );
}
