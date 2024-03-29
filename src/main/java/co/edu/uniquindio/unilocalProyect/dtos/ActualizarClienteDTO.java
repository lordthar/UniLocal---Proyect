package co.edu.uniquindio.unilocalProyect.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ActualizarClienteDTO(

        @NotBlank String id,
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank @Length(min = 10) String nickname,
        @NotBlank String fotoPerfil,
        @NotBlank String ciudadRecidencia,
        @NotBlank @Email String Email,
        @NotBlank String password
) {

}
