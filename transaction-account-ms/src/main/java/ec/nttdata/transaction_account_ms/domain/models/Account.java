package ec.nttdata.transaction_account_ms.domain.models;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder(toBuilder = true)
public class Account {

    private Long id;
    private String number;
    private String type;
    private Long clientId;
    private String clientName;
    private BigDecimal openingBalance;
    private boolean status;

}
