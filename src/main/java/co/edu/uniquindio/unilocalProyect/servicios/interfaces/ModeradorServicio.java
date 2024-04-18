package co.edu.uniquindio.unilocalProyect.servicios.interfaces;

import co.edu.uniquindio.unilocalProyect.dtos.AprobarNegocioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.DetalleNegocioModeradorDTO;
import co.edu.uniquindio.unilocalProyect.dtos.ItemNegocioModeradorDTO;
import co.edu.uniquindio.unilocalProyect.dtos.RechazarNegocioDTO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_NEGOCIO;

import java.util.List;

public interface ModeradorServicio extends CuentaServicio {

    void aprobarNegocio(AprobarNegocioDTO aprobarNegocioDTO) throws Exception;

    void rechazarNegocio(RechazarNegocioDTO rechazarNegocioDTO) throws Exception;

    DetalleNegocioModeradorDTO buscarNegocioPorId(String idNegocio) throws Exception;

    List<ItemNegocioModeradorDTO> filtrarPorNombreNegocio(String nombreNegocio) throws Exception;

    List<ItemNegocioModeradorDTO> filtrarNegociosPorNombrePropietario(String nombrePersona) throws Exception;

    List<ItemNegocioModeradorDTO> filtrarPorEstadoNegocio(ESTADO_NEGOCIO estadoNegocio) throws Exception;
}
