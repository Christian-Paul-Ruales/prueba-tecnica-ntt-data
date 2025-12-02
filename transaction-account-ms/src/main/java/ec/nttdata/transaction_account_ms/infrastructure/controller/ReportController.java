package ec.nttdata.transaction_account_ms.infrastructure.controller;

import ec.nttdata.transaction_account_ms.application.commands.CreateReportCommand;
import ec.nttdata.transaction_account_ms.application.port.in.usecases.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/report")
public class ReportController {


    private final CreateReportAccountMovementsUseCase createReportAccountMovementsUseCase;


    @Operation(
            summary = "Get account clients",
            description = "Get account clients"
    )
    @ApiResponse(
            responseCode = "200",
            description = "All accounts"
    )
    @ApiResponse(
            responseCode = "500",
            description = "An error occurred"
    )
    @GetMapping("/{clientId}")
    public ResponseEntity<?> getAllByClientId(
            @PathVariable("clientId") Long clientId,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate end

    ) {

        return createReportAccountMovementsUseCase.execute(
                new CreateReportCommand(clientId, start.atStartOfDay(), end.atTime(LocalTime.MAX))
        ).fold(
                ResponseEntity::ok,
                error -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(error)
        );
    }


}
