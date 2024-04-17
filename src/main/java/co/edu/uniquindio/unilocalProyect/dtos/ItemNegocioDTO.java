package co.edu.uniquindio.unilocalProyect.dtos;

import co.edu.uniquindio.unilocalProyect.modelo.entidades.Coordenada;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;

import java.util.Map;

public record ItemNegocioDTO(
        String nombre,
        String descripcion,
        String codigoCliente,
        Map portada,
        TIPO_NEGOCIO tipoNegocio,
        Coordenada coordenada
) {
}