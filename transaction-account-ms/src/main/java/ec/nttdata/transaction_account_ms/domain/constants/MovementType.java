package ec.nttdata.transaction_account_ms.domain.constants;

import lombok.Getter;

@Getter
public enum MovementType {
    DEPOSITO("DEPOSITO"),
    RETIRO("RETIRO");

    private final String code;

    MovementType(String code) {
        this.code = code;
    }
}
