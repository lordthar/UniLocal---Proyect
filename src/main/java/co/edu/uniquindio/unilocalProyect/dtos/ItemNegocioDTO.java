package co.edu.uniquindio.unilocalProyect.dtos;

import co.edu.uniquindio.unilocalProyect.modelo.entidades.Coordenada;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;

public record ItemNegocioDTO(
        String nombre,
        String descripcion,
        String codigoCliente,
        String portada,
        TIPO_NEGOCIO tipoNegocio,
        Coordenada coordenada
) {
}
