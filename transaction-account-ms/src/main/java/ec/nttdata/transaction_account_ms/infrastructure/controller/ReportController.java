package ec.nttdata.transaction_account_ms.infrastructure.controller;

import ec.nttdata.transaction_account_ms.application.commands.CreateMovementCommand;
import ec.nttdata.transaction_account_ms.application.port.in.usecases.*;
import ec.nttdata.transaction_account_ms.infrastructure.dto.MovementRequestDTO;
import ec.nttdata.transaction_account_ms.infrastructure.mappers.MovementRequestMapper;
import ec.nttdata.transaction_account_ms.infrastructure.mappers.MovementResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/movements")
public class ReportController {


    private final GetMovementUseCase getMovementUseCase;
    private final GetAllMovementsUseCase getAllMovementsUseCase;
    private final CreateMovementUseCase createMovementUseCase;
    private final UpdateMovementUseCase updateMovementUseCase;
    private final DeleteMovementUseCase deleteMovementUseCase;

    private final MovementResponseMapper responseMapper;
    private final MovementRequestMapper requestMapper;


    @Operation(
            summary = "Get all movements",
            description = "Get all movements"
    )
    @ApiResponse(
            responseCode = "200",
            description = "All movements"
    )
    @ApiResponse(
            responseCode = "500",
            description = "An error occurred"
    )
    @GetMapping()
    public ResponseEntity<?> getAll() {

        return getAllMovementsUseCase.execute().fold(
                accounts -> ResponseEntity.ok(responseMapper.toResponses(accounts)),
                error -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(error)
        );
    }


    @Operation(
            summary = "Get movement by id",
            description = "Get movement by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Get movement by id"
    )
    @ApiResponse(
            responseCode = "500",
            description = "An error occurred"
    )
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {

        return getMovementUseCase.execute(id)
                .fold(
                        client -> ResponseEntity.ok(responseMapper.toResponse(client)),
                        error -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(error)

                );
    }


    @Operation(
            summary = "Create movement",
            description = "Create movement"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Create movement"
    )
    @ApiResponse(
            responseCode = "500",
            description = "An error occurred"
    )
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid MovementRequestDTO movementRequestDTO) {

        CreateMovementCommand command = new CreateMovementCommand(
                movementRequestDTO.type(),
                movementRequestDTO.account(),
                movementRequestDTO.value()
        );

        return createMovementUseCase.execute(command)
                .fold(
                    account -> ResponseEntity.status(HttpStatus.CREATED)
                            .body(responseMapper.toResponse(account)),
                        error -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(error)
                );
    }

    @Operation(
            summary = "Update movement",
            description = "Update movement"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Update movement"
    )
    @ApiResponse(
            responseCode = "500",
            description = "An error occurred"
    )
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id,  @RequestBody @Valid MovementRequestDTO movementRequestDTO) {
        return updateMovementUseCase.execute(id, requestMapper.toDomain(movementRequestDTO))
                .fold(
                        account -> ResponseEntity.status(HttpStatus.CREATED)
                                .body(responseMapper.toResponse(account)),
                        error -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(error)
                );
    }


    @Operation(
            summary = "Delete movement",
            description = "Delete movement"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Delete movement"
    )
    @ApiResponse(
            responseCode = "500",
            description = "An error occurred"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        return deleteMovementUseCase.execute(id)
                .fold(
                        ResponseEntity::ok,
                        error -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(error)
                );

    }



}
