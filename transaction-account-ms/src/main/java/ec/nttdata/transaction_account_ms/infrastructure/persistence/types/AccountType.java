package ec.nttdata.transaction_account_ms.infrastructure.persistence.types;

import lombok.Getter;

@Getter
public enum AccountType {
    AHORROS("AHORROS"),
    CORRIENTE("CORRIENTE");

    private final String code;

    AccountType(String code) {
        this.code = code;
    }
}
