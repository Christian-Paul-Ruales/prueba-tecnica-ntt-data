package ec.nttdata.transaction_account_ms.application.strategies.impl;

import ec.nttdata.transaction_account_ms.application.commands.CreateMovementProcessorCommand;
import ec.nttdata.transaction_account_ms.application.strategies.CreateMovementStrategy;
import ec.nttdata.transaction_account_ms.application.port.out.repositories.MovementRepository;
import ec.nttdata.transaction_account_ms.domain.constants.MovementType;
import ec.nttdata.transaction_account_ms.domain.models.Movement;
import ec.nttdata.transaction_account_ms.domain.result.Result;
import ec.nttdata.transaction_account_ms.domain.result.ResultError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class CreateTransferStrategyImpl implements CreateMovementStrategy {

    private final MovementRepository movementRepository;

    @Override
    public Result<Movement> execute(CreateMovementProcessorCommand command) {

        BigDecimal lastBalance = movementRepository
                .findLastMovement(command.account().getId())
                .map(Movement::getBalance)
                .orElse(command.account().getOpeningBalance());

        if(command.movementValue().compareTo(lastBalance) > 0) {
            return new Result.Failure<>(
                    new ResultError("2", "Balance not available"));
        }

        BigDecimal presentBalance = lastBalance.subtract(command.movementValue());

        Movement present = Movement.builder()
                .type(getType().getCode())
                .value(command.movementValue())
                .balance(presentBalance)
                .account(command.account())
                .status(true)
                .build();

        Movement presentSaved = movementRepository.save(present);

        return new Result.Success<>(presentSaved);
    }

    @Override
    public MovementType getType() {
        return MovementType.TRANSFERENCIA;
    }
}
