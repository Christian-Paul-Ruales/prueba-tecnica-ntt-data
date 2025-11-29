package ec.nttdata.person_user_ms.infrastructure.dtos;

import lombok.Builder;

@Builder(toBuilder = true)
public record ClientResponseDTO (
        Long id,
        String name,
        String gender,
        String identification,
        String address,
        String phone,
        String password,
        boolean status
) {
}
