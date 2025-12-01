package ec.nttdata.transaction_account_ms.infrastructure.mappers;

import ec.nttdata.transaction_account_ms.domain.models.Movement;
import ec.nttdata.transaction_account_ms.infrastructure.dto.MovementRequestDTO;
import ec.nttdata.transaction_account_ms.infrastructure.dto.MovementResponseDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-11-30T18:16:46-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 25 (Oracle Corporation)"
)
@Component
public class MovementRequestMapperImpl implements MovementRequestMapper {

    @Override
    public Movement toDomain(MovementRequestDTO requestDTO) {
        if ( requestDTO == null ) {
            return null;
        }

        Movement.MovementBuilder movement = Movement.builder();

        movement.id( requestDTO.id() );
        movement.type( requestDTO.type() );
        movement.value( requestDTO.value() );
        movement.status( requestDTO.status() );
        movement.account( requestDTO.account() );

        return movement.build();
    }

    @Override
    public List<Movement> toDomains(List<MovementRequestDTO> requestDTOS) {
        if ( requestDTOS == null ) {
            return null;
        }

        List<Movement> list = new ArrayList<Movement>( requestDTOS.size() );
        for ( MovementRequestDTO movementRequestDTO : requestDTOS ) {
            list.add( toDomain( movementRequestDTO ) );
        }

        return list;
    }

    @Override
    public MovementResponseDTO toRequest(Movement domain) {
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
    public List<MovementResponseDTO> toRequests(List<Movement> domain) {
        if ( domain == null ) {
            return null;
        }

        List<MovementResponseDTO> list = new ArrayList<MovementResponseDTO>( domain.size() );
        for ( Movement movement : domain ) {
            list.add( toRequest( movement ) );
        }

        return list;
    }
}
