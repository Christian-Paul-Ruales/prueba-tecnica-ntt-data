package ec.nttdata.transaction_account_ms.infrastructure.mappers;

import ec.nttdata.transaction_account_ms.domain.models.Movement;
import ec.nttdata.transaction_account_ms.infrastructure.dto.MovementRequestDTO;
import ec.nttdata.transaction_account_ms.infrastructure.dto.MovementResponseDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovementRequestMapper {
    Movement toDomain(MovementRequestDTO requestDTO);
    List<Movement> toDomains(List<MovementRequestDTO> requestDTOS);

    @InheritInverseConfiguration
    MovementResponseDTO toRequest(Movement domain);
    List<MovementResponseDTO> toRequests(List<Movement> domain);

}
