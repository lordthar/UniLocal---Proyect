package co.edu.uniquindio.unilocalProyect.servicios.interfaces;

import co.edu.uniquindio.unilocalProyect.dtos.*;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;

import java.util.List;

public interface NegocioServicio {

    List<ItemNegocioDTO> listarNegociosFavoritos(String codigoCliente) throws Exception;

    List<TIPO_NEGOCIO> listarTiposNegocio();

    List<ItemNegocioDTO> listarNegocios();

    String crearNegocio(CrearNegocioDTO crearNegocioDTO) throws Exception;

    DetalleNegocioDTO buscarNegocio(String idNegocio) throws Exception;

    List<ItemNegocioDTO> buscarPorNombrePropietario(String nombrePropietario) throws Exception;

    List<ItemNegocioDTO> filtrarPorEstadoNegocio(ESTADO_NEGOCIO estadoNegocio) throws Exception;

    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTo) throws Exception;

    void eliminarNegocio(String idNegocio) throws Exception;

    List<ItemNegocioDTO> filtrarPorNombre(String nombre) throws Exception;

    List<ItemNegocioDTO> filtrarPorTipoNegocio(TIPO_NEGOCIO tipoNegocio) throws Exception;

    List<ItemNegocioDTO> negociosEditadosPorModerador(String codigoModerador);

    void aprobarNegocio(AprobarNegocioDTO aprobarNegocioDTO) throws Exception;

    void rechazarNegocio(RechazarNegocioDTO rechazarNegocioDTO) throws Exception;
}
