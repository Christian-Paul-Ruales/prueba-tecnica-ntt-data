package ec.nttdata.person_user_ms.application.usecases;

import ec.nttdata.person_user_ms.application.port.in.usecases.GetAllClientsUseCase;
import ec.nttdata.person_user_ms.application.port.in.usecases.GetClientUseCase;
import ec.nttdata.person_user_ms.application.port.out.ClientRepository;
import ec.nttdata.person_user_ms.domain.models.Client;
import ec.nttdata.person_user_ms.domain.result.Result;
import ec.nttdata.person_user_ms.domain.result.ResultError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class GetAllClientsUseCaseImpl implements GetAllClientsUseCase {

    private final ClientRepository clientRepository;

    public Result<List<Client>> execute() {
        return new Result.Success<>(clientRepository.findAll());
    }

}
