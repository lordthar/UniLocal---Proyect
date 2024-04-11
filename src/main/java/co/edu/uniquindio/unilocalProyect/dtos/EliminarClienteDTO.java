package co.edu.uniquindio.unilocalProyect.dtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EliminarClienteDTO(
        @NotBlank @Length(max = 10, min=5) String nombre,
        @NotBlank String codigo,
        @NotBlank String nickname
) {

}
