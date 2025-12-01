package ec.nttdata.transaction_account_ms;

import ec.nttdata.transaction_account_ms.application.factory.CreateMovementFactory;
import ec.nttdata.transaction_account_ms.application.port.out.repositories.AccountRepository;
import ec.nttdata.transaction_account_ms.application.usecases.CreateMovementUseCaseImpl;
import ec.nttdata.transaction_account_ms.domain.models.Movement;
import ec.nttdata.transaction_account_ms.domain.result.Result;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test with stategy and method factory
 * */
@ExtendWith(MockitoExtension.class)
class CreateMovementUnitTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private CreateMovementFactory factory;

    @InjectMocks
    private CreateMovementUseCaseImpl createMovementUseCase;

    @Test
    @DisplayName("Create movement Should fail, account not found")
    void createMovement_shouldFailure() {

        when(accountRepository.findById(ConstantsTest.ACCOUNT_NOT_FOUND))
                .thenReturn(Optional.empty());

        Result<Movement> result = createMovementUseCase.execute(ConstantsTest.MOVEMENT_COMMAND_NOT_FOUND);

        verify(accountRepository, Mockito.times(1)).findById(any());
        verify(factory, Mockito.times(0)).execute(any(), any(), any());

        Assertions.assertThat(result).isInstanceOf(Result.Failure.class);

    }

    @Test
    @DisplayName("Create movement Should Success")
    void createMovement_shouldSuccess() {

        when(accountRepository.findById(ConstantsTest.ACCOUNT_ID))
                .thenReturn(Optional.of(ConstantsTest.ACCOUNT_WITHOUT_MOVEMENTS));
        when(factory.execute(ConstantsTest.ACCOUNT_WITHOUT_MOVEMENTS, ConstantsTest.COMMAND.type(), ConstantsTest.COMMAND.movementValue()))
                .thenReturn(new Result.Success<>(ConstantsTest.MOVEMENT_DEPOSIT));

        Result<Movement> result = createMovementUseCase.execute(ConstantsTest.COMMAND);

        verify(accountRepository, Mockito.times(1)).findById(any());
        verify(factory, Mockito.times(1)).execute(any(), any(), any());

        Assertions.assertThat(result).isInstanceOf(Result.Success.class);
        Assertions.assertThat(result.getOrElse(null))
                .isNotNull()
                .isInstanceOf(Movement.class);

        Assertions.assertThat(result.getOrElse(null).getId())
                .isEqualTo(1L);


    }
}
