package co.edu.uniquindio.unilocalProyect.dtos;

import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_CLIENTE;

import java.util.List;

public record DetalleClienteDTO(
        String codigo,
        String nombre,
        String fotoPerfil,
        String nickname,
        String email,
        String ciudadResidencia,
        List<String> telefonos,
        TIPO_CLIENTE tipoCliente
) {
}
