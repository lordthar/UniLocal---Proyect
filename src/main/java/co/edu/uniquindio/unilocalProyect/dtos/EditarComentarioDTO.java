package co.edu.uniquindio.unilocalProyect.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EditarComentarioDTO(
        @NotBlank String codigo,
        @NotBlank String mensaje,
        int calificacion,
        @NotNull LocalDateTime fechaComentario
        ) {



}
