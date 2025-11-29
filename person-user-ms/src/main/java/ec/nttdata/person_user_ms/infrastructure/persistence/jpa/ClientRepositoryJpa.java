package ec.nttdata.person_user_ms.infrastructure.persistence.jpa;

import ec.nttdata.person_user_ms.infrastructure.persistence.entities.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepositoryJpa extends JpaRepository<ClientEntity, Long> {
}
