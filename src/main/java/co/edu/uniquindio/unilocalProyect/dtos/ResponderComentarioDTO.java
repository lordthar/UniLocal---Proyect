package co.edu.uniquindio.unilocalProyect.dtos;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

public record ResponderComentarioDTO(

        @NotBlank String idComentario,
        @NotBlank @Length(min=20, max=100) String respuesta,
        @NotBlank LocalDateTime fechaRespuesta
        ) {
}
