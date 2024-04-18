package co.edu.uniquindio.unilocalProyect.dtos;

import co.edu.uniquindio.unilocalProyect.modelo.documentos.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record ActualizarClienteDTO(

        @NotBlank String id,
        @NotBlank @Length(max = 100) String nombre,
        @NotBlank @Length(min = 10) String nickname,
        @NotBlank String fotoPerfil,
        @NotBlank String ciudadRecidencia,
        @NotBlank List<String> telefonos


        ) {

}
