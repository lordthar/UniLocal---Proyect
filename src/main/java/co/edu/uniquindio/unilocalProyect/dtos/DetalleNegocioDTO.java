package co.edu.uniquindio.unilocalProyect.dtos;

import co.edu.uniquindio.unilocalProyect.modelo.entidades.Coordenada;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.HistorialRevision;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.Horario;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_REGISTRO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;

import java.util.List;

public record DetalleNegocioDTO(
        String codigo,
        String nombre,
        String descripcion,
        String codigoCliente,
        List<String> imagenes,
        List<String> telefonos,
        List<Horario> horarios,
        TIPO_NEGOCIO tipoNegocio,
        Coordenada coordenada,
        ESTADO_REGISTRO estadoRegistro,
        List<HistorialRevision> historialRevisiones
) {

}
