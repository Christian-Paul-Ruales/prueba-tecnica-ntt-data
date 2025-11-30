package ec.nttdata.transaction_account_ms.infrastructure.persistence.jpa;

import ec.nttdata.transaction_account_ms.infrastructure.persistence.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepositoryJpa extends JpaRepository<TransactionEntity, Long> {

    List<TransactionEntity> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);
}
