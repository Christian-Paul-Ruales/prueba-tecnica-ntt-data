package ec.nttdata.transaction_account_ms.infrastructure.mappers;

import ec.nttdata.transaction_account_ms.domain.models.Movement;
import ec.nttdata.transaction_account_ms.infrastructure.persistence.entities.MovementEntity;
import ec.nttdata.transaction_account_ms.domain.constants.MovementType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovementMapper {
    @Mapping(target = "type", source = "type", qualifiedByName = "typeToString")
    Movement toDomain(MovementEntity entity);
    List<Movement> toDomains(List<MovementEntity> entities);

    @Mapping(target = "type", source = "type", qualifiedByName = "stringToType")
    MovementEntity toEntity(Movement domain);
    List<MovementEntity> toEntities(List<Movement> domain);

    /**
     * Conversion a ResponseDTO
     * */


    @Named("stringToType")
    default MovementType stringToType(String type) {
        return MovementType.valueOf(type);
    }

    @Named("typeToString")
    default String typeToString(MovementType type) {
        return String.valueOf(type.getCode());
    }

}
