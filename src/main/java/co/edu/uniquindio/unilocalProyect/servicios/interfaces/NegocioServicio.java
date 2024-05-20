package co.edu.uniquindio.unilocalProyect.servicios.interfaces;

import co.edu.uniquindio.unilocalProyect.dtos.*;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.Imagen;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;

import java.util.List;

public interface NegocioServicio {

    String crearNegocio(CrearNegocioDTO crearNegocioDTO) throws Exception;

    DetalleNegocioDTO buscarNegocio(String idNegocio) throws Exception;

    void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTo) throws Exception;

    void eliminarNegocio(String idNegocio) throws Exception;

    List<ItemNegocioDTO> filtrarPorNombre(String nombre) throws Exception;

    List<ItemNegocioDTO> filtrarPorTipoNegocio(TIPO_NEGOCIO tipoNegocio) throws Exception;
    public List<ItemNegocioDTO> listarNegocios() throws Exception;

    public List<ItemNegocioDTO> listarNegocioPropietario(String idCliente) throws Exception;

    public List<String> recorrerUrl(List<Imagen> listaUrl) throws Exception;
}
