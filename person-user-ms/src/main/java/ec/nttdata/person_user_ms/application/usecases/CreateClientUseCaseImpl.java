package ec.nttdata.person_user_ms.application.usecases;

import ec.nttdata.person_user_ms.application.port.in.usecases.CreateClientUseCase;
import ec.nttdata.person_user_ms.application.port.out.ClientRepository;
import ec.nttdata.person_user_ms.domain.models.Client;
import ec.nttdata.person_user_ms.domain.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class CreateClientUseCaseImpl implements CreateClientUseCase {

    private final ClientRepository clientRepository;
    private final PasswordEncoder encoder;

    @Override
    public Result<Client> execute(Client client) {
        String passwordEncrypted = encoder.encode(client.getPassword());

        Client clientEncrypted = client.toBuilder()
                .password(passwordEncrypted)
                .build();

        return new Result.Success<>(
                clientRepository.save(clientEncrypted)
        );
    }

}
