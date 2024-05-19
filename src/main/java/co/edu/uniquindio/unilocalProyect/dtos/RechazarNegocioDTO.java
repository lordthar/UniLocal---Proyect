package co.edu.uniquindio.unilocalProyect.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record RechazarNegocioDTO(
        @NotBlank String idNegocio,
        @NotBlank String idModerador,
        @NotBlank @Min(10) @Max(200) String motivo
) {
}
