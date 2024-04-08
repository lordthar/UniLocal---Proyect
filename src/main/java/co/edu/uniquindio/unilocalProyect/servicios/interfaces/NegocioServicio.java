package co.edu.uniquindio.unilocalProyect.servicios.interfaces;

import co.edu.uniquindio.unilocalProyect.dtos.ActualizarNegocioDTo;
import co.edu.uniquindio.unilocalProyect.dtos.DetalleNegocioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.CrearNegocioDTO;

import java.util.List;

public interface NegocioServicio {

    void crearNegocio(CrearNegocioDTO crearNegocioDTO) throws Exception;

    void actualizarNegocio(ActualizarNegocioDTo actualizarNegocioDTo) throws Exception;

    void eliminarNegocio(String idNegocio) throws Exception;

    List<DetalleNegocioDTO> filtrarPorNombre(String nombre) throws Exception;

    List<DetalleNegocioDTO> filtrarPorTipoNegocio(String tipoNegocio) throws Exception;

    List<DetalleNegocioDTO> negociosCercanos(String coordenada) throws Exception;
}
