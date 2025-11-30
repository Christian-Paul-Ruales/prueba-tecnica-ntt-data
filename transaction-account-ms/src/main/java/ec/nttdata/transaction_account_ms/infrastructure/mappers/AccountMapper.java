package ec.nttdata.transaction_account_ms.infrastructure.mappers;

import ec.nttdata.transaction_account_ms.domain.models.Account;
import ec.nttdata.transaction_account_ms.infrastructure.persistence.entities.AccountEntity;
import ec.nttdata.transaction_account_ms.infrastructure.persistence.types.AccountType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(target = "type", source = "type", qualifiedByName = "typeToString")
    Account toDomain(AccountEntity entity);
    List<Account> toDomains(List<AccountEntity> entities);

    @Mapping(target = "type", source = "type", qualifiedByName = "stringToType")
    AccountEntity toEntity(Account domain);
    List<AccountEntity> toEntities(List<Account> domain);

    /**
     * Conversion a ResponseDTO
     * */


    @Named("stringToType")
    default AccountType stringToType(String type) {
        return AccountType.valueOf(type);
    }

    @Named("typeToString")
    default String typeToString(AccountType type) {
        return String.valueOf(type.getCode());
    }

}
