package co.edu.uniquindio.unilocalProyect.dtos;

import co.edu.uniquindio.unilocalProyect.modelo.entidades.Coordenada;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.Horario;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;

import java.util.List;
import java.util.Map;

public record DetalleNegocioDTO(
        String nombre,
        String descripcio,
        String codigoCliente,
        List<Map> imagenes,
        List<String> telefonos,
        List<Horario> horarios,
        TIPO_NEGOCIO tipoNegocio,
        Coordenada coordenada
) {

}
