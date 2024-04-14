package co.edu.uniquindio.unilocalProyect.dtos;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record EditarComentarioDTO(
        @NotBlank String codigo,
        @NotBlank String mensaje,
        @NotBlank String respuesta,
        @NotBlank int calificacion,
        @NotBlank LocalDateTime fecha
        ) {



}
