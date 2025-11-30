package ec.nttdata.transaction_account_ms.infrastructure.persistence.types;

import lombok.Getter;

@Getter
public enum TransactionType {
    DEPOSITO("DEPOSITO"),
    RETIRO("RETIRO");

    private final String code;

    TransactionType(String code) {
        this.code = code;
    }
}
