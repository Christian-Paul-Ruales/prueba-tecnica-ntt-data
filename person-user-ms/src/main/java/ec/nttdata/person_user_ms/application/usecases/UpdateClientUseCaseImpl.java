package ec.nttdata.person_user_ms.application.usecases;

import ec.nttdata.person_user_ms.application.port.in.usecases.CreateClientUseCase;
import ec.nttdata.person_user_ms.application.port.in.usecases.UpdateClientUseCase;
import ec.nttdata.person_user_ms.application.port.out.ClientRepository;
import ec.nttdata.person_user_ms.domain.models.Client;
import ec.nttdata.person_user_ms.domain.result.Result;
import ec.nttdata.person_user_ms.domain.result.ResultError;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class UpdateClientUseCaseImpl implements UpdateClientUseCase {

    private final ClientRepository clientRepository;
    private final PasswordEncoder encoder;

    @Override
    public Result<Client> execute(Long id, Client client) {
        Optional<Client> process = clientRepository.findById(id)
                .map(
                        found -> build(client, found)
                );

        if(process.isPresent()) {
            return new Result.Success<>(process.get());
        }

        return new Result.Failure<>(new ResultError("1", "Client not found"));

    }

    /**
     * Build new Client with the old one
     * @Param client: Client to create
     * @Param found: Old Client
     * */
    private Client build(Client client, Client found) {
        String password = client.getPassword() != null
                ? encoder.encode(client.getPassword())
                : found.getPassword();

        Long id = client.getId() != null ? client.getId() : found.getId();
        return client.toBuilder()
                .id(id)
                .password(password)
                .build();
    }

}
