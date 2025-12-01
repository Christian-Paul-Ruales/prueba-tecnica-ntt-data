package ec.nttdata.transaction_account_ms.application.commands;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CreateMovementCommand(
        String type,
        Long account,
        BigDecimal movementValue
) {

}
