package ec.nttdata.person_user_ms.infrastructure.persistence.entities;

import ec.nttdata.person_user_ms.infrastructure.persistence.types.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@MappedSuperclass
public abstract class PersonEntity {
    @Column(length = 100, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false)
    private Gender gender;

    @Column(length = 20, nullable = false)
    private String identification;

    @Column(length = 150, nullable = false)
    private String address;

    @Column(length = 10, nullable = false)
    private String phone;

}
