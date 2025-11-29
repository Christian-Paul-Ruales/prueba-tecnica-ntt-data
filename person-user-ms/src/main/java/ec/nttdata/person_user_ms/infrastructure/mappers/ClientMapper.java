package ec.nttdata.person_user_ms.infrastructure.mappers;

import ec.nttdata.person_user_ms.domain.models.Client;
import ec.nttdata.person_user_ms.infrastructure.dtos.ClientResponseDTO;
import ec.nttdata.person_user_ms.infrastructure.persistence.entities.ClientEntity;
import ec.nttdata.person_user_ms.infrastructure.persistence.types.Gender;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    @Mapping(target = "gender", source = "gender", qualifiedByName = "genderToString")
    Client toDomain(ClientEntity entity);
    List<Client> toDomains(List<ClientEntity> entities);

    @Mapping(target = "gender", source = "gender", qualifiedByName = "stringToGender")
    ClientEntity toEntity(Client domain);
    List<ClientEntity> toEntities(List<Client> domain);

    /**
     * Conversion a ResponseDTO
     * */


    @Named("stringToGender")
    default Gender stringToGender(String gender) {
        return Gender.valueOf(gender);
    }

    @Named("genderToString")
    default String genderToString(Gender gender) {
        return String.valueOf(gender.getCode());
    }

}
