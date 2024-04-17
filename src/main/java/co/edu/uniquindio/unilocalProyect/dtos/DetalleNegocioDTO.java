package co.edu.uniquindio.unilocalProyect.dtos;

import co.edu.uniquindio.unilocalProyect.modelo.entidades.Coordenada;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.Horario;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;

import java.util.List;

public record DetalleNegocioDTO(
        String nombre,
        String descripcio,
        String codigoCliente,
        List<String> imagenes,
        List<String> telefonos,
        List<Horario> horarios,
        TIPO_NEGOCIO tipoNegocio,
        Coordenada coordenada
) {

}
