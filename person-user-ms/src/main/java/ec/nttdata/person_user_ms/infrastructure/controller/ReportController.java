package ec.nttdata.person_user_ms.infrastructure.controller;

import ec.nttdata.person_user_ms.application.port.in.usecases.*;
import ec.nttdata.person_user_ms.infrastructure.dtos.ClientRequestDTO;
import ec.nttdata.person_user_ms.infrastructure.mappers.RequestMapper;
import ec.nttdata.person_user_ms.infrastructure.mappers.ResponseMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/report")
public class ReportController {


    private final GetClientUseCase getClientUseCase;
    private final GetAllClientsUseCase getAllClientsUseCase;
    private final CreateClientUseCase createClientUseCase;
    private final UpdateClientUseCase updateClientUseCase;
    private final DeleteClientUseCase deleteClientUseCase;

    private final ResponseMapper responseMapper;
    private final RequestMapper requestMapper;


    @Operation(
            summary = "Get all clients",
            description = "Get all clients"
    )
    @ApiResponse(
            responseCode = "200",
            description = "All clients"
    )
    @ApiResponse(
            responseCode = "500",
            description = "An error occurred"
    )
    @GetMapping()
    public ResponseEntity<?> getAll() {

        return getAllClientsUseCase.execute().fold(
                clients -> ResponseEntity.ok(responseMapper.toResponses(clients)),
                error -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(error)
        );
    }


    @Operation(
            summary = "Get client by id",
            description = "Get client by id"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Get client by id"
    )
    @ApiResponse(
            responseCode = "500",
            description = "An error occurred"
    )
    @GetMapping("{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        return getClientUseCase.execute(id)
                .fold(
                        client -> ResponseEntity.ok(responseMapper.toResponse(client)),
                        error -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(error)

                );
    }


    @Operation(
            summary = "Create client",
            description = "Create client"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Create client"
    )
    @ApiResponse(
            responseCode = "500",
            description = "An error occurred"
    )
    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid ClientRequestDTO clientRequestDTO) {
        return createClientUseCase.execute(requestMapper.toDomain(clientRequestDTO))
                .fold(
                    client -> ResponseEntity.status(HttpStatus.CREATED)
                            .body(responseMapper.toResponse(client)),
                        error -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(error)
                );
    }

    @Operation(
            summary = "Update client",
            description = "Update client"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Update client"
    )
    @ApiResponse(
            responseCode = "500",
            description = "An error occurred"
    )
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable Long id,  @RequestBody @Valid ClientRequestDTO clientRequestDTO) {
        return updateClientUseCase.execute(id, requestMapper.toDomain(clientRequestDTO))
                .fold(
                        client -> ResponseEntity.status(HttpStatus.CREATED)
                                .body(responseMapper.toResponse(client)),
                        error -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(error)
                );
    }


    @Operation(
            summary = "Delete client",
            description = "Delete client"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Delete client"
    )
    @ApiResponse(
            responseCode = "500",
            description = "An error occurred"
    )
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        return deleteClientUseCase.execute(id)
                .fold(
                        ResponseEntity::ok,
                        error -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error)
                );

    }



}
