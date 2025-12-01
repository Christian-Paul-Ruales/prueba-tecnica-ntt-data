package ec.nttdata.transaction_account_ms.infrastructure.mappers;

import ec.nttdata.transaction_account_ms.domain.models.Account;
import ec.nttdata.transaction_account_ms.infrastructure.dto.AccountRequestDTO;
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
public class AccountRequestMapperImpl implements AccountRequestMapper {

    @Override
    public Account toDomain(AccountRequestDTO responseDTO) {
        if ( responseDTO == null ) {
            return null;
        }

        Account.AccountBuilder account = Account.builder();

        account.id( responseDTO.id() );
        account.type( responseDTO.type() );
        account.clientId( responseDTO.clientId() );
        account.clientName( responseDTO.clientName() );
        account.openingBalance( responseDTO.openingBalance() );
        account.status( responseDTO.status() );

        return account.build();
    }

    @Override
    public List<Account> toDomains(List<AccountRequestDTO> responseDTOS) {
        if ( responseDTOS == null ) {
            return null;
        }

        List<Account> list = new ArrayList<Account>( responseDTOS.size() );
        for ( AccountRequestDTO accountRequestDTO : responseDTOS ) {
            list.add( toDomain( accountRequestDTO ) );
        }

        return list;
    }

    @Override
    public AccountRequestDTO toEntity(Account domain) {
        if ( domain == null ) {
            return null;
        }

        AccountRequestDTO.AccountRequestDTOBuilder accountRequestDTO = AccountRequestDTO.builder();

        accountRequestDTO.id( domain.getId() );
        accountRequestDTO.type( domain.getType() );
        accountRequestDTO.clientId( domain.getClientId() );
        accountRequestDTO.clientName( domain.getClientName() );
        accountRequestDTO.openingBalance( domain.getOpeningBalance() );
        accountRequestDTO.status( domain.isStatus() );

        return accountRequestDTO.build();
    }

    @Override
    public List<AccountRequestDTO> toEntities(List<Account> domain) {
        if ( domain == null ) {
            return null;
        }

        List<AccountRequestDTO> list = new ArrayList<AccountRequestDTO>( domain.size() );
        for ( Account account : domain ) {
            list.add( toEntity( account ) );
        }

        return list;
    }
}
