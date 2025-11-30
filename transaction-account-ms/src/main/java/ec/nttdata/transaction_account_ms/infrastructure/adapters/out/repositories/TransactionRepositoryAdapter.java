package ec.nttdata.transaction_account_ms.infrastructure.adapters.out.repositories;

import ec.nttdata.transaction_account_ms.application.port.out.repositories.AccountRepository;
import ec.nttdata.transaction_account_ms.application.port.out.repositories.TransactionRepository;
import ec.nttdata.transaction_account_ms.domain.models.Account;
import ec.nttdata.transaction_account_ms.domain.models.Transaction;
import ec.nttdata.transaction_account_ms.infrastructure.mappers.AccountMapper;
import ec.nttdata.transaction_account_ms.infrastructure.mappers.TransactionMapper;
import ec.nttdata.transaction_account_ms.infrastructure.persistence.entities.AccountEntity;
import ec.nttdata.transaction_account_ms.infrastructure.persistence.entities.TransactionEntity;
import ec.nttdata.transaction_account_ms.infrastructure.persistence.jpa.AccountRepositoryJpa;
import ec.nttdata.transaction_account_ms.infrastructure.persistence.jpa.TransactionRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class TransactionRepositoryAdapter implements TransactionRepository {

    private final TransactionRepositoryJpa repositoryJpa;
    private final TransactionMapper mapper;

    @Override
    public Transaction save(Transaction account) {
        TransactionEntity entity = repositoryJpa.save(
                mapper.toEntity(account)
        );

        return mapper.toDomain(entity);
    }

    @Override
    public List<Transaction> findAll() {
        return repositoryJpa.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        return repositoryJpa.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Transaction> findByDateTimeBetween(LocalDateTime start, LocalDateTime end) {

        return repositoryJpa.findByDateTimeBetween(start, end)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public boolean existById(Long id) {
        return repositoryJpa.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        repositoryJpa.deleteById(id);
    }


}
