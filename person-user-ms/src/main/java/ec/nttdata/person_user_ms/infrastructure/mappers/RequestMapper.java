package ec.nttdata.person_user_ms.infrastructure.mappers;

import ec.nttdata.person_user_ms.domain.models.Client;
import ec.nttdata.person_user_ms.infrastructure.dtos.ClientRequestDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RequestMapper {
    Client toDomain(ClientRequestDTO dto);
    List<Client> toDomains(List<ClientRequestDTO> dtos);


    ClientRequestDTO toResponse(Client domain);
    List<ClientRequestDTO> toResponses(List<Client> domain);

}
