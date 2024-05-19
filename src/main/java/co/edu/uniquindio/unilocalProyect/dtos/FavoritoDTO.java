package co.edu.uniquindio.unilocalProyect.dtos;

import jakarta.validation.constraints.NotBlank;

public record FavoritoDTO(
        @NotBlank String codigoCliente,
        @NotBlank String codigoNegocio
) {
}
