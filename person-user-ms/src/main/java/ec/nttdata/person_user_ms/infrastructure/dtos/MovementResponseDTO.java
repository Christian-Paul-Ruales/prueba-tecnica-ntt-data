package ec.nttdata.person_user_ms.infrastructure.dtos;

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
