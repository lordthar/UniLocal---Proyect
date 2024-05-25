package co.edu.uniquindio.unilocalProyect.dtos;

import jakarta.validation.constraints.NotBlank;

public record RechazarNegocioDTO(
        @NotBlank String idNegocio,
        @NotBlank String idModerador,
        @NotBlank String motivo
) {
}
