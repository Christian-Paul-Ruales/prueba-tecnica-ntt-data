package ec.nttdata.person_user_ms.infrastructure.persistence.types;

import lombok.Getter;

@Getter
public enum Gender {
    M('M'),
    F('F');

    private final char code;

    Gender(char code) {
        this.code = code;
    }
}
