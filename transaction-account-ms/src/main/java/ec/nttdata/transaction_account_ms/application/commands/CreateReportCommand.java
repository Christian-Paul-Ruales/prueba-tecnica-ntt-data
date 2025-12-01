package ec.nttdata.transaction_account_ms.application.commands;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CreateReportCommand(
        Long clientId,
        LocalDateTime start,
        LocalDateTime end
) {

}
