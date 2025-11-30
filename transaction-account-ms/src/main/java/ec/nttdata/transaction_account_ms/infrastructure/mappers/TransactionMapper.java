package ec.nttdata.transaction_account_ms.infrastructure.mappers;

import ec.nttdata.transaction_account_ms.domain.models.Transaction;
import ec.nttdata.transaction_account_ms.infrastructure.persistence.entities.TransactionEntity;
import ec.nttdata.transaction_account_ms.infrastructure.persistence.types.TransactionType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(target = "type", source = "type", qualifiedByName = "typeToString")
    Transaction toDomain(TransactionEntity entity);
    List<Transaction> toDomains(List<TransactionEntity> entities);

    @Mapping(target = "type", source = "type", qualifiedByName = "stringToType")
    TransactionEntity toEntity(Transaction domain);
    List<TransactionEntity> toEntities(List<Transaction> domain);

    /**
     * Conversion a ResponseDTO
     * */


    @Named("stringToType")
    default TransactionType stringToType(String type) {
        return TransactionType.valueOf(type);
    }

    @Named("typeToString")
    default String typeToString(TransactionType type) {
        return String.valueOf(type.getCode());
    }

}
