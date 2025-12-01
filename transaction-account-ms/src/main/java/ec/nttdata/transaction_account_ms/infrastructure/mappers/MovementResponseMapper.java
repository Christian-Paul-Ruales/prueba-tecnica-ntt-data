package ec.nttdata.transaction_account_ms.infrastructure.mappers;

import ec.nttdata.transaction_account_ms.domain.models.Movement;
import ec.nttdata.transaction_account_ms.infrastructure.dto.MovementResponseDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovementResponseMapper {
    Movement toDomain(MovementResponseDTO responseDTO);
    List<Movement> toDomains(List<MovementResponseDTO> responseDTOS);

    @InheritInverseConfiguration
    MovementResponseDTO toResponse(Movement domain);
    List<MovementResponseDTO> toResponses(List<Movement> domain);

}
