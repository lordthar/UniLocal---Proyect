package co.edu.uniquindio.unilocalProyect.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record LoginDTO(
        @NotBlank @Email String email,
        @NotBlank @Length(max = 20, min = 8) String password
) {
}
