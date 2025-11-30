package ec.nttdata.transaction_account_ms.infrastructure.persistence.jpa;

import ec.nttdata.transaction_account_ms.infrastructure.persistence.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepositoryJpa extends JpaRepository<AccountEntity, Long> {
}
