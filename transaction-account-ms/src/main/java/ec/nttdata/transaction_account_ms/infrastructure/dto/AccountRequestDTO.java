package ec.nttdata.transaction_account_ms.infrastructure.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.math.BigDecimal;

@Builder(toBuilder = true)
public record AccountRequestDTO (
        Long id,
        @NotBlank(message = "type is required")
        @Pattern(regexp = "AHORROS|CORRIENTE"
                        , message = "Only types Allowed")
        String type,
        @NotNull(message = "client identificator is required")
        Long clientId,
        @NotBlank(message = "Client name is required")
        String clientName,

        @NotNull(message = "Oppening value is required")
        BigDecimal openingBalance,

        boolean status
)
{
}
