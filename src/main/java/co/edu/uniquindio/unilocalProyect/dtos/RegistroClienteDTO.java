package co.edu.uniquindio.unilocalProyect.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record RegistroClienteDTO(
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank @Length(min = 10) String nickname,
        String fotoPerfil,
        @NotBlank String ciudadRecidencia,
        @NotBlank @Email String email,
        @NotBlank @Length(max = 10, min = 8) String password,
         List<String> telefonos
) {

}
