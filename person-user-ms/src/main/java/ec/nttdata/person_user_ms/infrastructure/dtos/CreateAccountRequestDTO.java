package ec.nttdata.person_user_ms.infrastructure.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;

import java.math.BigDecimal;

@Builder(toBuilder = true)
public record CreateAccountRequestDTO(
        @Pattern(regexp = "AHORROS|CORRIENTE"
                        , message = "Only types Allowed")
        String type,


        @NotNull(message = "Oppening value is required")
        BigDecimal openingBalance

)
{ }
