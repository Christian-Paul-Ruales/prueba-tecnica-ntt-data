package ec.nttdata.transaction_account_ms.infrastructure.dto;

import lombok.Builder;
import java.math.BigDecimal;

@Builder(toBuilder = true)
public record AccountResponseDTO(
        Long id,
        String number,
        String type,
        Long clientId,
        String clientName,
        BigDecimal openingBalance,
        boolean status
)
{
}
