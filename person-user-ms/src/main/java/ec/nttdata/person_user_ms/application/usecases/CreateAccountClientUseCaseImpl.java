package ec.nttdata.person_user_ms.application.usecases;

import ec.nttdata.person_user_ms.application.port.in.usecases.CreateAccountClientUseCase;
import ec.nttdata.person_user_ms.application.port.in.usecases.GetClientUseCase;
import ec.nttdata.person_user_ms.application.port.out.clients.TransactionAccountPort;
import ec.nttdata.person_user_ms.domain.models.Client;
import ec.nttdata.person_user_ms.domain.result.Result;
import ec.nttdata.person_user_ms.infrastructure.dtos.AccountRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class CreateAccountClientUseCaseImpl implements CreateAccountClientUseCase {

    private final GetClientUseCase getClientUseCase;
    private final TransactionAccountPort transactionAccountPort;

    @Override
    public Result<Client> execute(Long clientId, String typeAccount, BigDecimal balance) {
        Result<Client> clientResult = getClientUseCase.execute(clientId);

        if(clientResult.isSuccess()) {
            transactionAccountPort.createAccountPerson(new AccountRequestDTO(
                    null,
                    typeAccount,
                    clientId,
                    clientResult.getOrElse(null).getName(),
                    balance,
                    true
            ));
        }
        return clientResult;
    }

}
