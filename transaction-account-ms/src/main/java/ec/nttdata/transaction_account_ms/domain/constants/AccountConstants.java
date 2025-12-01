package ec.nttdata.transaction_account_ms.domain.constants;

public class AccountConstants {
    public static final String REGEX_TYPES = AccountType.AHORROS
            .name()
            .concat("|")
            .concat(AccountType.CORRIENTE.name());
}
