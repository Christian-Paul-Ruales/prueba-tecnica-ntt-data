package ec.nttdata.transaction_account_ms.infrastructure.mappers;

import ec.nttdata.transaction_account_ms.domain.models.Account;
import ec.nttdata.transaction_account_ms.infrastructure.dto.AccountResponseDTO;
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
public class AccountResponseMapperImpl implements AccountResponseMapper {

    @Override
    public Account toDomain(AccountResponseDTO responseDTO) {
        if ( responseDTO == null ) {
            return null;
        }

        Account.AccountBuilder account = Account.builder();

        account.id( responseDTO.id() );
        account.number( responseDTO.number() );
        account.type( responseDTO.type() );
        account.clientId( responseDTO.clientId() );
        account.clientName( responseDTO.clientName() );
        account.openingBalance( responseDTO.openingBalance() );
        account.status( responseDTO.status() );

        return account.build();
    }

    @Override
    public List<Account> toDomains(List<AccountResponseDTO> responseDTOS) {
        if ( responseDTOS == null ) {
            return null;
        }

        List<Account> list = new ArrayList<Account>( responseDTOS.size() );
        for ( AccountResponseDTO accountResponseDTO : responseDTOS ) {
            list.add( toDomain( accountResponseDTO ) );
        }

        return list;
    }

    @Override
    public AccountResponseDTO toResponse(Account domain) {
        if ( domain == null ) {
            return null;
        }

        AccountResponseDTO.AccountResponseDTOBuilder accountResponseDTO = AccountResponseDTO.builder();

        accountResponseDTO.id( domain.getId() );
        accountResponseDTO.number( domain.getNumber() );
        accountResponseDTO.type( domain.getType() );
        accountResponseDTO.clientId( domain.getClientId() );
        accountResponseDTO.clientName( domain.getClientName() );
        accountResponseDTO.openingBalance( domain.getOpeningBalance() );
        accountResponseDTO.status( domain.isStatus() );

        return accountResponseDTO.build();
    }

    @Override
    public List<AccountResponseDTO> toResponses(List<Account> domain) {
        if ( domain == null ) {
            return null;
        }

        List<AccountResponseDTO> list = new ArrayList<AccountResponseDTO>( domain.size() );
        for ( Account account : domain ) {
            list.add( toResponse( account ) );
        }

        return list;
    }
}
