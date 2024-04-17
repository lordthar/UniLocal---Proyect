package co.edu.uniquindio.unilocalProyect.servicios.interfaces;

import co.edu.uniquindio.unilocalProyect.dtos.ActualizarNegocioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.CrearNegocioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.DetalleNegocioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.ItemNegocioDTO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;

import java.util.List;

public interface NegocioServicio {

    String crearNegocio(CrearNegocioDTO crearNegocioDTO) throws Exception;

    DetalleNegocioDTO buscarNegocio(String idNegocio) throws Exception;

    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTo) throws Exception;

    void eliminarNegocio(String idNegocio) throws Exception;

    List<ItemNegocioDTO> filtrarPorNombre(String nombre) throws Exception;

    List<ItemNegocioDTO> filtrarPorTipoNegocio(TIPO_NEGOCIO tipoNegocio) throws Exception;
}
