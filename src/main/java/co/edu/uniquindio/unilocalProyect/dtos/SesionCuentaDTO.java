package co.edu.uniquindio.unilocalProyect.dtos;

import jakarta.validation.constraints.NotBlank;

public record SesionCuentaDTO(

        @NotBlank String fotoPerfil,

        @NotBlank String nickname
) {
}
