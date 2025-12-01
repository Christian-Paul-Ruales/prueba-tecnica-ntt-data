package ec.nttdata.transaction_account_ms.infrastructure.mappers;

import ec.nttdata.transaction_account_ms.domain.models.Movement;
import ec.nttdata.transaction_account_ms.infrastructure.persistence.entities.AccountEntity;
import ec.nttdata.transaction_account_ms.infrastructure.persistence.entities.MovementEntity;
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
public class MovementMapperImpl implements MovementMapper {

    @Override
    public Movement toDomain(MovementEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Movement.MovementBuilder movement = Movement.builder();

        movement.account( entityAccountId( entity ) );
        movement.type( typeToString( entity.getType() ) );
        movement.id( entity.getId() );
        movement.dateTime( entity.getDateTime() );
        movement.value( entity.getValue() );
        movement.balance( entity.getBalance() );
        movement.status( entity.isStatus() );

        return movement.build();
    }

    @Override
    public List<Movement> toDomains(List<MovementEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<Movement> list = new ArrayList<Movement>( entities.size() );
        for ( MovementEntity movementEntity : entities ) {
            list.add( toDomain( movementEntity ) );
        }

        return list;
    }

    @Override
    public MovementEntity toEntity(Movement domain) {
        if ( domain == null ) {
            return null;
        }

        MovementEntity movementEntity = new MovementEntity();

        movementEntity.setAccount( movementToAccountEntity( domain ) );
        movementEntity.setType( stringToType( domain.getType() ) );
        movementEntity.setId( domain.getId() );
        movementEntity.setDateTime( domain.getDateTime() );
        movementEntity.setValue( domain.getValue() );
        movementEntity.setBalance( domain.getBalance() );
        movementEntity.setStatus( domain.isStatus() );

        return movementEntity;
    }

    @Override
    public List<MovementEntity> toEntities(List<Movement> domain) {
        if ( domain == null ) {
            return null;
        }

        List<MovementEntity> list = new ArrayList<MovementEntity>( domain.size() );
        for ( Movement movement : domain ) {
            list.add( toEntity( movement ) );
        }

        return list;
    }

    private Long entityAccountId(MovementEntity movementEntity) {
        AccountEntity account = movementEntity.getAccount();
        if ( account == null ) {
            return null;
        }
        return account.getId();
    }

    protected AccountEntity movementToAccountEntity(Movement movement) {
        if ( movement == null ) {
            return null;
        }

        AccountEntity accountEntity = new AccountEntity();

        accountEntity.setId( movement.getAccount() );

        return accountEntity;
    }
}
