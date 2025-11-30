package ec.nttdata.transaction_account_ms.infrastructure.adapters.out.repositories;

import ec.nttdata.transaction_account_ms.application.port.out.repositories.MovementRepository;
import ec.nttdata.transaction_account_ms.domain.models.Movement;
import ec.nttdata.transaction_account_ms.infrastructure.mappers.MovementMapper;
import ec.nttdata.transaction_account_ms.infrastructure.persistence.entities.MovementEntity;
import ec.nttdata.transaction_account_ms.infrastructure.persistence.jpa.MovementRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MovementRepositoryAdapter implements MovementRepository {

    private final MovementRepositoryJpa repositoryJpa;
    private final MovementMapper mapper;

    @Override
    public Movement save(Movement account) {
        MovementEntity entity = repositoryJpa.save(
                mapper.toEntity(account)
        );

        return mapper.toDomain(entity);
    }

    @Override
    public List<Movement> findAll() {
        return repositoryJpa.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Movement> findById(Long id) {
        return repositoryJpa.findById(id)
                .map(mapper::toDomain);
    }

    @Override
    public List<Movement> findByDateTimeBetween(LocalDateTime start, LocalDateTime end) {

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

    /**
     * Find last transaction (movement) in account
     * */
    @Override
    public Optional<Movement> findLastMovement(Long accountId) {

        return repositoryJpa.findByAccountIdAndStatusTrue(
                accountId,
                Sort.by(Sort.Direction.DESC, "dateTime")
        ).map(mapper::toDomain);
    }

}
