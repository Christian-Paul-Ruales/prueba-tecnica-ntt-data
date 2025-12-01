package ec.nttdata.transaction_account_ms;

import ec.nttdata.transaction_account_ms.application.commands.CreateMovementCommand;
import ec.nttdata.transaction_account_ms.application.commands.CreateReportCommand;
import ec.nttdata.transaction_account_ms.domain.constants.AccountType;
import ec.nttdata.transaction_account_ms.domain.constants.MovementType;
import ec.nttdata.transaction_account_ms.domain.models.Account;
import ec.nttdata.transaction_account_ms.domain.models.Movement;
import ec.nttdata.transaction_account_ms.infrastructure.dto.AccountRequestDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ConstantsTest {
    public static final Long ACCOUNT_ID = 1L;
    public static final Long CLIENT_ID = 1L;
    public static final Long ACCOUNT_NOT_FOUND = 2L;
    public static final CreateMovementCommand COMMAND = new CreateMovementCommand(
            MovementType.DEPOSITO.getCode(),
            ACCOUNT_ID,
            BigDecimal.valueOf(10)
    );

    public static final CreateReportCommand REPORT_COMMAND = new CreateReportCommand(
            CLIENT_ID,
            LocalDateTime.of(2000, 1, 1, 0, 0),
            LocalDateTime.of(2025, 12, 5, 5, 0)
    );

    public static final CreateMovementCommand MOVEMENT_COMMAND_NOT_FOUND = new CreateMovementCommand(
            MovementType.DEPOSITO.getCode(),
            ACCOUNT_NOT_FOUND,
            BigDecimal.valueOf(10)
    );

    public static final Account ACCOUNT_WITHOUT_MOVEMENTS = new Account(
            ACCOUNT_ID,
            "1234567890",
            AccountType.AHORROS.getCode(),
            CLIENT_ID,
            "Chrisitan Ruales",
            BigDecimal.ZERO,
            true,
            List.of()
    );
    public static final Movement MOVEMENT_DEPOSIT = new Movement(
            1L,
            LocalDateTime.now(),
            MovementType.DEPOSITO.getCode(),
            BigDecimal.valueOf(10),
            BigDecimal.valueOf(10),
            true,
            ACCOUNT_ID
    );


    // INTEGRATION TEST
    public static final AccountRequestDTO ACCOUNT_REQUEST = new AccountRequestDTO(
            null,
            AccountType.AHORROS.getCode(),
            CLIENT_ID,
            "Chrisitan Ruales",
            BigDecimal.valueOf(1000),
            true
    );
}
