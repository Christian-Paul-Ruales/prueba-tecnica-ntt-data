package ec.nttdata.person_user_ms.application.usecases;

import ec.nttdata.person_user_ms.application.port.in.usecases.GetClientUseCase;
import ec.nttdata.person_user_ms.application.port.out.ClientRepository;
import ec.nttdata.person_user_ms.domain.models.Client;
import ec.nttdata.person_user_ms.domain.result.Result;
import ec.nttdata.person_user_ms.domain.result.ResultError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class GetClientUseCaseImpl implements GetClientUseCase {

    private final ClientRepository clientRepository;

    public Result<Client> execute(Long id) {
        Optional<Client> optional = clientRepository.findById(id);

        return optional.isPresent() ?
                new Result.Success<>(optional.get())
                : new Result.Failure<>(new ResultError("1", "Cannot get client [%s]".formatted(id)));
    }

}
