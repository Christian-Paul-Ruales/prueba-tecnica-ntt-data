package ec.nttdata.transaction_account_ms;

import ec.nttdata.transaction_account_ms.application.port.out.repositories.AccountRepository;
import ec.nttdata.transaction_account_ms.application.port.out.repositories.MovementRepository;
import ec.nttdata.transaction_account_ms.application.usecases.CreateReportAccountMovementsUseCaseImpl;
import ec.nttdata.transaction_account_ms.domain.models.Account;
import ec.nttdata.transaction_account_ms.domain.result.Result;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit test with stategy and method factory
 * */
@ExtendWith(MockitoExtension.class)
class CreateReportUnitTest {

    @Mock
    private AccountRepository accountRepository;
    @Mock
    private MovementRepository movementRepository;

    @InjectMocks
    private CreateReportAccountMovementsUseCaseImpl useCase;

    @Test
    @DisplayName("Create report should return success with accounts by user")
    void createReport_thenReturnResultSuccessAccount_shouldSuccess() {

        when(accountRepository.findByClientIdAndStatusTrue(ConstantsTest.CLIENT_ID))
                .thenReturn(List.of(ConstantsTest.ACCOUNT_WITHOUT_MOVEMENTS));

        when(movementRepository.findByAccountDateTimeBetween(
                ConstantsTest.REPORT_COMMAND.clientId(),
                ConstantsTest.REPORT_COMMAND.start(),
                ConstantsTest.REPORT_COMMAND.end()
        )).thenReturn(List.of(ConstantsTest.MOVEMENT_DEPOSIT));

        Result<List<Account>> result = useCase.execute(ConstantsTest.REPORT_COMMAND);

        verify(accountRepository, Mockito.times(1))
                .findByClientIdAndStatusTrue(any());

        verify(movementRepository,
                Mockito.times(1))
                .findByAccountDateTimeBetween(any(), any(), any()
                );

        Assertions.assertThat(result).isInstanceOf(Result.Success.class);
        Assertions.assertThat(result.getOrElse(null))
                .isNotNull()
                .isInstanceOf(List.class)
                .isNotEmpty();

        Assertions.assertThat(result.getOrElse(null).getFirst())
                .isInstanceOf(Account.class);

        Assertions.assertThat(result.getOrElse(null)
                        .getFirst()
                        .getMovements()
                )
                .isNotNull()
                .isNotEmpty();
    }


}
