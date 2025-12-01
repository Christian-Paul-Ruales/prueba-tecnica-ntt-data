package ec.nttdata.transaction_account_ms.infrastructure.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder(toBuilder = true)
public record MovementResponseDTO(
        Long id,
        LocalDateTime dateTime,
        String type,
        BigDecimal value,
        BigDecimal balance,
        Long account,
        boolean status
)
{
}
