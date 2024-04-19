package co.edu.uniquindio.unilocalProyect.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record CrearComentarioDTO(

        @NotBlank String codigoCliente,
        @NotBlank String codigoNegocio,
        @NotBlank @Length(max=250, min=20) String mensaje,
        int calificacion,
        @NotNull LocalDateTime fechaComentario

        ) {

}
