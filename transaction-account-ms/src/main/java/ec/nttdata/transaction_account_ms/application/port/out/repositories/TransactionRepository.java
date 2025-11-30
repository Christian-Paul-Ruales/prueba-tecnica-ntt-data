package ec.nttdata.transaction_account_ms.application.port.out.repositories;

import ec.nttdata.transaction_account_ms.domain.models.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionRepository {

    Transaction save(Transaction account);
    List<Transaction> findAll();
    Optional<Transaction> findById(Long id);
    List<Transaction> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);
    boolean existById(Long id);
    void deleteById(Long id);

}
