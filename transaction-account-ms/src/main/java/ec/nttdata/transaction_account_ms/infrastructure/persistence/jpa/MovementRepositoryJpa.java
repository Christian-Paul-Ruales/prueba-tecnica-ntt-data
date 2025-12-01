package ec.nttdata.transaction_account_ms.infrastructure.persistence.jpa;

import ec.nttdata.transaction_account_ms.infrastructure.persistence.entities.MovementEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface MovementRepositoryJpa extends JpaRepository<MovementEntity, Long> {

    List<MovementEntity> findByDateTimeBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT t FROM MovementEntity t WHERE t.account.id = :id AND t.status = true LIMIT 1")
    Optional<MovementEntity> findByAccountIdAndStatusTrue(@Param("id") Long id, Sort sort);

    @Query("""
            SELECT t FROM MovementEntity t
                WHERE t.account.id = :accountId AND t.status = true
                AND t.dateTime BETWEEN :start AND :end
            """)
    List<MovementEntity> findMovementsDateTimeBetween(@Param("accountId") Long accountId, @Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

}
