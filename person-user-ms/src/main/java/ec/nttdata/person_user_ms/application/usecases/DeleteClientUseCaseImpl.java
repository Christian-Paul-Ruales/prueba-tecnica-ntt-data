package ec.nttdata.person_user_ms.application.usecases;

import ec.nttdata.person_user_ms.application.port.in.usecases.DeleteClientUseCase;
import ec.nttdata.person_user_ms.application.port.out.repositories.ClientRepository;
import ec.nttdata.person_user_ms.domain.result.Result;
import ec.nttdata.person_user_ms.domain.result.ResultError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DeleteClientUseCaseImpl implements DeleteClientUseCase {

    private final ClientRepository clientRepository;

    public Result<Boolean> execute(Long id) {
        if(clientRepository.existById(id)) {
            clientRepository.deleteById(id);
            return new Result.Success<>(true);
        }
        return new Result.Failure<>(new ResultError("1", "Cannot delete client"));
    }

}
