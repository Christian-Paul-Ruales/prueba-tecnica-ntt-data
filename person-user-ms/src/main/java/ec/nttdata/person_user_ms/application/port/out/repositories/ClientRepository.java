package ec.nttdata.person_user_ms.application.port.out.repositories;

import ec.nttdata.person_user_ms.domain.models.Client;

import java.util.List;
import java.util.Optional;

public interface ClientRepository {

    List<Client> saveAll(List<Client> clients);
    Client save(Client client);
    List<Client> findAll();
    Optional<Client> findById(Long id);
    boolean existById(Long id);
    void deleteById(Long id);

}
