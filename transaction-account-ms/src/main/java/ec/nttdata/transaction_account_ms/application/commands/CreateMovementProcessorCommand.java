package ec.nttdata.transaction_account_ms.application.commands;

import ec.nttdata.transaction_account_ms.domain.models.Account;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record CreateMovementProcessorCommand(Account account, BigDecimal movementValue) {
}
