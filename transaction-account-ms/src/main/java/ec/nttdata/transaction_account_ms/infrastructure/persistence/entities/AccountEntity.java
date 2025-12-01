package ec.nttdata.transaction_account_ms.infrastructure.persistence.entities;

import ec.nttdata.transaction_account_ms.domain.constants.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(length = 10, nullable = false, unique = true)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private AccountType type;

    @Column(name = "client_id", nullable = false)
    private Long clientId;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(nullable = false)
    private BigDecimal openingBalance;

    @Column(nullable = false)
    private boolean status;


}
