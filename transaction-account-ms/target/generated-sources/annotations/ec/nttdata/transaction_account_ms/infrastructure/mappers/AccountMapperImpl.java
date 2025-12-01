package ec.nttdata.transaction_account_ms.infrastructure.mappers;

import ec.nttdata.transaction_account_ms.domain.models.Account;
import ec.nttdata.transaction_account_ms.infrastructure.persistence.entities.AccountEntity;
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
public class AccountMapperImpl implements AccountMapper {

    @Override
    public Account toDomain(AccountEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Account.AccountBuilder account = Account.builder();

        account.type( typeToString( entity.getType() ) );
        account.id( entity.getId() );
        account.number( entity.getNumber() );
        account.clientId( entity.getClientId() );
        account.clientName( entity.getClientName() );
        account.openingBalance( entity.getOpeningBalance() );
        account.status( entity.isStatus() );

        return account.build();
    }

    @Override
    public List<Account> toDomains(List<AccountEntity> entities) {
        if ( entities == null ) {
            return null;
        }

        List<Account> list = new ArrayList<Account>( entities.size() );
        for ( AccountEntity accountEntity : entities ) {
            list.add( toDomain( accountEntity ) );
        }

        return list;
    }

    @Override
    public AccountEntity toEntity(Account domain) {
        if ( domain == null ) {
            return null;
        }

        AccountEntity accountEntity = new AccountEntity();

        accountEntity.setType( stringToType( domain.getType() ) );
        accountEntity.setId( domain.getId() );
        accountEntity.setNumber( domain.getNumber() );
        accountEntity.setClientId( domain.getClientId() );
        accountEntity.setClientName( domain.getClientName() );
        accountEntity.setOpeningBalance( domain.getOpeningBalance() );
        accountEntity.setStatus( domain.isStatus() );

        return accountEntity;
    }

    @Override
    public List<AccountEntity> toEntities(List<Account> domain) {
        if ( domain == null ) {
            return null;
        }

        List<AccountEntity> list = new ArrayList<AccountEntity>( domain.size() );
        for ( Account account : domain ) {
            list.add( toEntity( account ) );
        }

        return list;
    }
}
