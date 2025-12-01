package ec.nttdata.transaction_account_ms.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import java.math.BigDecimal;


@Builder(toBuilder = true)
public record MovementRequestDTO(
        Long id,
        @NotBlank(message = "Movement type is required")
        @Pattern(regexp = "DEPOSITO|RETIRO"
                , message = "Only types Allowed")
        String type,
        @NotNull(message = "value is required")
        BigDecimal value,
        @NotNull(message = "account is required")
        Long account,
        boolean status
)
{
}
