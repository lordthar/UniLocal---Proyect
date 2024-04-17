package co.edu.uniquindio.unilocalProyect.dtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CambioPasswordDTO(
        @NotBlank @Length(min = 8, max= 16) String newPassword,
        @NotBlank @Length(min=8,max=16) String actualPassword,
        @NotBlank String id,
        @NotBlank String token
        ) {
}
