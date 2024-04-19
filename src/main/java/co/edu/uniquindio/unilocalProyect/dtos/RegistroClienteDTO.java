package co.edu.uniquindio.unilocalProyect.dtos;

import co.edu.uniquindio.unilocalProyect.modelo.documentos.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record RegistroClienteDTO(
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank @Length(min = 10) String nickname,
        String fotoPerfil,
        @NotBlank String ciudadRecidencia,
        @NotBlank @Email String Email,
        @NotBlank @Length(max = 10, min = 8) String password,
        @NotBlank List<String> telefonos
) {

}
