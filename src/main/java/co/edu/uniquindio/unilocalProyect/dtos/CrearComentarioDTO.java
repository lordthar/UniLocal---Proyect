package co.edu.uniquindio.unilocalProyect.dtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record CrearComentarioDTO(

        @NotBlank String codigoCliente,
        @NotBlank String codigoNegocio,
        @NotBlank @Length(max=250, min=20) String mensaje,
        @Length(max=5, min =0) int calificacion,
        @NotBlank LocalDateTime fechaComentario

        ) {

}
