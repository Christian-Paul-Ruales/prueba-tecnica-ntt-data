package ec.nttdata.transaction_account_ms.application.strategies.impl;

import ec.nttdata.transaction_account_ms.application.commands.CreateMovementProcessorCommand;
import ec.nttdata.transaction_account_ms.application.strategies.CreateMovementStrategy;
import ec.nttdata.transaction_account_ms.application.port.out.repositories.AccountRepository;
import ec.nttdata.transaction_account_ms.application.port.out.repositories.MovementRepository;
import ec.nttdata.transaction_account_ms.domain.models.Movement;
import ec.nttdata.transaction_account_ms.domain.result.Result;
import ec.nttdata.transaction_account_ms.domain.constants.MovementType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CreateDepositStrategyImpl implements CreateMovementStrategy {

    private final MovementRepository movementRepository;
    private final AccountRepository accountRepository;

    /**
     * Deposit in account
     * */
    @Override
    public Result<Movement> execute(CreateMovementProcessorCommand command) {

        BigDecimal lastBalance = movementRepository
                .findLastMovement(command.account().getId())
                .map(Movement::getBalance)
                .orElseGet(() -> command.account().getOpeningBalance());

        BigDecimal presentBalance = lastBalance.add(command.movementValue());

        Movement present = Movement.builder()
                .type(getType().getCode())
                .value(command.movementValue())
                .balance(presentBalance)
                .account(command.account().getId())
                .status(true)
                .build();

        Movement presentSaved = movementRepository.save(present);

        return new Result.Success<>(presentSaved);
    }

    @Override
    public MovementType getType() {
        return MovementType.DEPOSITO;
    }
}
