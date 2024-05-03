package co.edu.uniquindio.unilocalProyect.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record ActualizarClienteDTO(

        @NotBlank String id, @NotBlank @Length(max = 100) String nombre,
        @NotBlank @Length(min = 5) String nickname,
        @NotBlank String fotoPerfil,
        @NotBlank String ciudadRecidencia,
        @NotEmpty List<String> telefonos
        ) {
}
