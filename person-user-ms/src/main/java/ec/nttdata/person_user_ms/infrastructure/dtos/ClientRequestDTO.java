package ec.nttdata.person_user_ms.infrastructure.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder(toBuilder = true)
public record ClientRequestDTO(
        Long id,

        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 100, message = "Name must have a minimum of 2 characters and a maximum of 100 characters.")
        String name,

        @NotBlank(message = "Gender is required")
        @Pattern(regexp = "[MF]", message = "Gender must be M or F")
        String gender,

        @NotBlank(message = "Identification number is required")
        @Size(min = 10, max = 13, message = "The identification number must have a minimum of 10 numbers for CI or a maximum of 13 numbers for RUC.")
        String identification,

        @NotBlank(message = "Address  is required")
        @Size(min = 5, max = 150, message = "The Address must have a minimum of 5 characters and a maximum of 150 characters")
        String address,

        @NotBlank(message = "Phone number is required")
        @Size(min = 10, max = 10, message = "Phone number must be 10 digits long.")
        String phone,

        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 30, message = "Password must have a minimum of 8 characters and a maximum of 30 characters.")
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
                message = "The password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character.")
        String password,

        @NotNull(message = "Status is required")
        boolean status
) {
}
