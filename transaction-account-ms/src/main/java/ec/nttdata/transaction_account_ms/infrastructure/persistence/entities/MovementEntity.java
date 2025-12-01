package ec.nttdata.transaction_account_ms.infrastructure.persistence.entities;

import ec.nttdata.transaction_account_ms.domain.constants.MovementType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "movement")
public class MovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "date_time", nullable = false)
    private LocalDateTime dateTime;

    @Enumerated(EnumType.STRING)
    @Column(length = 15, nullable = false)
    private MovementType type;

    @Column(nullable = false)
    private BigDecimal value;

    @Column(nullable = false)
    private BigDecimal balance;
    @Column(nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "account", referencedColumnName = "id", nullable = false)
    private AccountEntity account;

    @PrePersist
    private void onSave() {
        dateTime = LocalDateTime.now();
    }




}
