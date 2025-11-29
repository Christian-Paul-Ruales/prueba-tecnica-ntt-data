package ec.nttdata.person_user_ms.infrastructure.mappers;

import ec.nttdata.person_user_ms.domain.models.Client;
import ec.nttdata.person_user_ms.infrastructure.dtos.ClientResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ResponseMapper {
    Client toDomain(ClientResponseDTO dto);
    List<Client> toDomains(List<ClientResponseDTO> dtos);


    ClientResponseDTO toResponse(Client domain);
    List<ClientResponseDTO> toResponses(List<Client> domain);

}
