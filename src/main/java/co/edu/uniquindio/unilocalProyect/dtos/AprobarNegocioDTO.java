package co.edu.uniquindio.unilocalProyect.dtos;

import jakarta.validation.constraints.NotBlank;

public record AprobarNegocioDTO(
        @NotBlank String codigoNegocio,
        @NotBlank String codigoModerador
) {
}
