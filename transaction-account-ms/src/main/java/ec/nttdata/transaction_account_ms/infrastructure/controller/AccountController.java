package ec.nttdata.transaction_account_ms.infrastructure.controller;

import ec.nttdata.transaction_account_ms.application.commands.CreateReportCommand;
import ec.nttdata.transaction_account_ms.application.port.in.usecases.*;
import ec.nttdata.transaction_account_ms.infrastructure.dto.AccountRequestDTO;
import ec.nttdata.transaction_account_ms.infrastructure.mappers.AccountRequestMapper;
import ec.nttdata.transaction_account_ms.infrastructure.mappers.AccountResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/accounts")
public class AccountController {


    private final GetAccountUseCase getAccountUseCase;
    private final GetAllAccountsUseCase getAllClientsUseCase;
    private final CreateReportAccountMovementsUseCase createReport;
    private final CreateAccountUseCase createAccountUseCase;
    private final UpdateAccountUseCase updateAccountUseCase;
    private final DeleteAccountUseCase deleteAccountUseCase;

    private final AccountResponseMapper responseMapper;
    private final AccountRequestMapper requestMapper;


    @Operation(
            summary = "Get all accounts",
            description = "Get all accounts"
    )
    @ApiResponse(
            responseCode = "200",
            description = "All accounts"
    )
    @ApiResponse(
            responseCode = "500",
            description = "An error occurred"
    )
    @GetMapping()
    public ResponseEntity<?> getAll() {

        return getAllClientsUseCase.execute().fold(
                accounts -> ResponseEntity.ok(responseMapper.toResponses(accounts)),
                error -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(error)
        );
    }

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
    @GetMapping("/customer/{clientId}")
    public ResponseEntity<?> getAllByClientId(
            @PathVariable("clientId") Long clientId,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDateTime start,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDateTime end

            ) {

        return createReport.execute(
                new CreateReportCommand(clientId, start, end)
        ).fold(
                ResponseEntity::ok,
                error -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(error)
        );
    }


    @Operation(
            summary = "Get account by id",
            description = "Get account by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Get account by id"
    )
    @ApiResponse(
            responseCode = "500",
            description = "An error occurred"
    )
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {

        return getAccountUseCase.execute(id)
                .fold(
                        client -> ResponseEntity.ok(responseMapper.toResponse(client)),
                        error -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(error)

                );
    }


    @Operation(
            summary = "Create account",
            description = "Create account"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Create account"
    )
    @ApiResponse(
            responseCode = "500",
            description = "An error occurred"
    )
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid AccountRequestDTO accountRequestDTO) {
        return createAccountUseCase.execute(requestMapper.toDomain(accountRequestDTO))
                .fold(
                    client -> ResponseEntity.status(HttpStatus.CREATED)
                            .body(responseMapper.toResponse(client)),
                        error -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(error)
                );
    }

    @Operation(
            summary = "Update Account",
            description = "Update Account"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Update Account"
    )
    @ApiResponse(
            responseCode = "500",
            description = "An error occurred"
    )
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id,  @RequestBody @Valid AccountRequestDTO accountRequestDTO) {
        return updateAccountUseCase.execute(id, requestMapper.toDomain(accountRequestDTO))
                .fold(
                        account -> ResponseEntity.status(HttpStatus.CREATED)
                                .body(responseMapper.toResponse(account)),
                        error -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(error)
                );
    }


    @Operation(
            summary = "Delete account",
            description = "Delete account"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Delete account"
    )
    @ApiResponse(
            responseCode = "500",
            description = "An error occurred"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        return deleteAccountUseCase.execute(id)
                .fold(
                        ResponseEntity::ok,
                        error -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(error)
                );

    }



}
