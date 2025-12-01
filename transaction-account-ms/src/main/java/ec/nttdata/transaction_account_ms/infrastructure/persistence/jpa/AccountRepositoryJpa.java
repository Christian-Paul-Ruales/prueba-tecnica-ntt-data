package ec.nttdata.transaction_account_ms.infrastructure.persistence.jpa;

import ec.nttdata.transaction_account_ms.infrastructure.persistence.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface AccountRepositoryJpa extends JpaRepository<AccountEntity, Long> {

    @Query("""
            SELECT p
                FROM AccountEntity p
                LEFT JOIN FETCH p.movements h
                WHERE p.clientId = :clientId AND p.status = true
                AND h.dateTime BETWEEN :start AND :end
            """)
    List<AccountEntity> findByClientIdAndStatusTrue(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end,
            @Param("clientId") Long clientId
    );

}
