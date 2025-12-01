package ec.nttdata.transaction_account_ms.domain.models;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Movement {

    private Long id;
    private LocalDateTime dateTime;
    private String type;
    private BigDecimal value;
    private BigDecimal balance;
    private boolean status;
    private Long account;



}
