package ec.nttdata.transaction_account_ms.infrastructure.mappers;

import ec.nttdata.transaction_account_ms.domain.models.Account;
import ec.nttdata.transaction_account_ms.infrastructure.dto.AccountResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountResponseMapper {
    Account toDomain(AccountResponseDTO responseDTO);
    List<Account> toDomains(List<AccountResponseDTO> responseDTOS);

    AccountResponseDTO toResponse(Account domain);
    List<AccountResponseDTO> toResponses(List<Account> domain);

}
