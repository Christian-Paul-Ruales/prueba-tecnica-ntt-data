package ec.nttdata.transaction_account_ms.infrastructure.mappers;

import ec.nttdata.transaction_account_ms.domain.models.Account;
import ec.nttdata.transaction_account_ms.infrastructure.dto.AccountRequestDTO;
import ec.nttdata.transaction_account_ms.infrastructure.dto.AccountResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountRequestMapper {
    Account toDomain(AccountRequestDTO responseDTO);
    List<Account> toDomains(List<AccountRequestDTO> responseDTOS);

    AccountRequestDTO toEntity(Account domain);
    List<AccountRequestDTO> toEntities(List<Account> domain);

}
