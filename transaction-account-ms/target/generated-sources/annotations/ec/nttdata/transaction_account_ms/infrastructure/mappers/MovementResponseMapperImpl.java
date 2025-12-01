package ec.nttdata.transaction_account_ms.infrastructure.mappers;

import ec.nttdata.transaction_account_ms.domain.models.Movement;
import ec.nttdata.transaction_account_ms.infrastructure.dto.MovementResponseDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-30T18:16:45-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class MovementResponseMapperImpl implements MovementResponseMapper {

    @Override
    public Movement toDomain(MovementResponseDTO responseDTO) {
        if ( responseDTO == null ) {
            return null;
        }

        Movement.MovementBuilder movement = Movement.builder();

        movement.id( responseDTO.id() );
        movement.dateTime( responseDTO.dateTime() );
        movement.type( responseDTO.type() );
        movement.value( responseDTO.value() );
        movement.balance( responseDTO.balance() );
        movement.status( responseDTO.status() );
        movement.account( responseDTO.account() );

        return movement.build();
    }

    @Override
    public List<Movement> toDomains(List<MovementResponseDTO> responseDTOS) {
        if ( responseDTOS == null ) {
            return null;
        }

        List<Movement> list = new ArrayList<Movement>( responseDTOS.size() );
        for ( MovementResponseDTO movementResponseDTO : responseDTOS ) {
            list.add( toDomain( movementResponseDTO ) );
        }

        return list;
    }

    @Override
    public MovementResponseDTO toResponse(Movement domain) {
        if ( domain == null ) {
            return null;
        }

        MovementResponseDTO.MovementResponseDTOBuilder movementResponseDTO = MovementResponseDTO.builder();

        movementResponseDTO.id( domain.getId() );
        movementResponseDTO.dateTime( domain.getDateTime() );
        movementResponseDTO.type( domain.getType() );
        movementResponseDTO.value( domain.getValue() );
        movementResponseDTO.balance( domain.getBalance() );
        movementResponseDTO.account( domain.getAccount() );
        movementResponseDTO.status( domain.isStatus() );

        return movementResponseDTO.build();
    }

    @Override
    public List<MovementResponseDTO> toResponses(List<Movement> domain) {
        if ( domain == null ) {
            return null;
        }

        List<MovementResponseDTO> list = new ArrayList<MovementResponseDTO>( domain.size() );
        for ( Movement movement : domain ) {
            list.add( toResponse( movement ) );
        }

        return list;
    }
}
