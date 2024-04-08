package co.edu.uniquindio.unilocalProyect.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginCuentaDTO(
       @NotBlank String nickname,
       @NotBlank String password

) {
}
