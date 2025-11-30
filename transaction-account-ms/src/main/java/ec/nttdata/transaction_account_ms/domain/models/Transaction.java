package ec.nttdata.transaction_account_ms.domain.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {

    private Long id;
    private LocalDateTime dateTime;
    private String type;
    private BigDecimal value;
    private BigDecimal balance;
    private boolean status;
    private Account account;



}
