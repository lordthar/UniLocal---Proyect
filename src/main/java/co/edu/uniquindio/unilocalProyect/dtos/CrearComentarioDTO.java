package co.edu.uniquindio.unilocalProyect.dtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record CrearComentarioDTO(
        @NotBlank @Length(max=250, min=20) String mensaje,
        @NotBlank @Length(max=250, min =20) String respuesta,
        @NotBlank LocalDateTime fecha,
        @Length(max=5, min =0) int calificacion
        ) {

}
