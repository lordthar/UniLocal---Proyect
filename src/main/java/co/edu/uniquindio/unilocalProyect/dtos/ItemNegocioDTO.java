package co.edu.uniquindio.unilocalProyect.dtos;

import co.edu.uniquindio.unilocalProyect.modelo.entidades.Coordenada;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;

public record ItemNegocioDTO(
        String codigoNegocio,
        String nombre,
        String descripcion,
        String codigoCliente,
        String portada,
        ESTADO_NEGOCIO estadoNegocio,
        TIPO_NEGOCIO tipoNegocio,
        Coordenada coordenada
) {
}
