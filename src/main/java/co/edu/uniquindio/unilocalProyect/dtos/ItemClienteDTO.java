package co.edu.uniquindio.unilocalProyect.dtos;

import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_CLIENTE;

public record ItemClienteDTO(
        String nickname,
        String fotoPerfil,
        String ciudadResidencia,
        TIPO_CLIENTE tipoCliente
) {
}
