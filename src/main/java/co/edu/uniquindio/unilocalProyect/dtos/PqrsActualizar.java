package co.edu.uniquindio.unilocalProyect.dtos;

import jakarta.validation.constraints.NotBlank;

public record PqrsActualizar(

        @NotBlank String codigo,
        @NotBlank String descripcion,
        @NotBlank String titulo
) {
}
