package co.edu.uniquindio.unilocalProyect.dtos;

import co.edu.uniquindio.unilocalProyect.modelo.entidades.Imagen;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_NEGOCIO;

import java.util.Map;

public record ItemNegocioModeradorDTO(
        String nombre,
        String descripcion,
        String codigoCliente,
        String portada,
        ESTADO_NEGOCIO estadoNegocio
) {
}
