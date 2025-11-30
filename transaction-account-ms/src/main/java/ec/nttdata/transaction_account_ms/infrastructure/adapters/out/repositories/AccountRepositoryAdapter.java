package ec.nttdata.transaction_account_ms.infrastructure.adapters.out.repositories;

import ec.nttdata.transaction_account_ms.application.port.out.repositories.AccountRepository;
import ec.nttdata.transaction_account_ms.domain.models.Account;
import ec.nttdata.transaction_account_ms.infrastructure.mappers.AccountMapper;
import ec.nttdata.transaction_account_ms.infrastructure.persistence.entities.AccountEntity;
import ec.nttdata.transaction_account_ms.infrastructure.persistence.jpa.AccountRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class AccountRepositoryAdapter implements AccountRepository {

    private final AccountRepositoryJpa repositoryJpa;
    private final AccountMapper mapper;

    @Override
    public Account save(Account account) {
        AccountEntity entity = repositoryJpa.save(
                mapper.toEntity(account)
        );

        return mapper.toDomain(entity);
    }

    @Override
    public List<Account> findAll() {
        return repositoryJpa.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Account> findById(Long id) {
        return repositoryJpa.findById(id)
                .map(mapper::toDomain);
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
