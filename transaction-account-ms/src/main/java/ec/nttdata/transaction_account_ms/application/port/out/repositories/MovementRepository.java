package ec.nttdata.transaction_account_ms.application.port.out.repositories;

import ec.nttdata.transaction_account_ms.domain.models.Movement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovementRepository {

    Movement save(Movement account);
    List<Movement> findAll();
    Optional<Movement> findById(Long id);
    List<Movement> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);
    List<Movement> findByAccountDateTimeBetween(Long accountId,LocalDateTime start, LocalDateTime end);
    boolean existById(Long id);
    void deleteById(Long id);

    Optional<Movement> findLastMovement(Long accountId);
}
