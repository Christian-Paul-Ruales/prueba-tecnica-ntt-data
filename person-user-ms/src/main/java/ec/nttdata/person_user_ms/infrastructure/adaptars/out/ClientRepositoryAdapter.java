package ec.nttdata.person_user_ms.infrastructure.adaptars.out;

import ec.nttdata.person_user_ms.application.port.out.repositories.ClientRepository;
import ec.nttdata.person_user_ms.domain.models.Client;
import ec.nttdata.person_user_ms.infrastructure.mappers.ClientMapper;
import ec.nttdata.person_user_ms.infrastructure.persistence.entities.ClientEntity;
import ec.nttdata.person_user_ms.infrastructure.persistence.jpa.ClientRepositoryJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class ClientRepositoryAdapter implements ClientRepository {
    private final ClientRepositoryJpa repositoryJpa;
    private final ClientMapper mapper;

    @Override
    public List<Client> saveAll(List<Client> clients) {
        List<ClientEntity> entities = mapper.toEntities(clients);

        return repositoryJpa.saveAll(entities)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Client save(Client client) {

        ClientEntity entity = repositoryJpa.save(
                mapper.toEntity(client)
        );

        return mapper.toDomain(entity);
    }

    @Override
    public List<Client> findAll() {

        return repositoryJpa.findAll()
                .stream()
                .map(mapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Client> findById(Long id) {

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
