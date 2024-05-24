package co.edu.uniquindio.unilocalProyect.servicios.interfaces;

import co.edu.uniquindio.unilocalProyect.dtos.*;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.Favorito;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_CLIENTE;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClienteServicio extends CuentaServicio{
    void agregarFavorito(FavoritoDTO favoritoDTO) throws Exception;

    void eliminarFavorito(FavoritoDTO favoritoDTO) throws Exception;

    List<Favorito> listarFavoritos(String codigoCliente) throws Exception;

    String registrarCliente(RegistroClienteDTO registroClienteDTO)throws Exception;

    void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO)throws Exception;

    void anularSubscripcion(String idCliente) throws Exception;

    void pagarSuscripcion(String idCliente) throws Exception;
    DetalleClienteDTO obtenerCliente(String idCuenta)throws Exception;

    void subirFotoCliente(MultipartFile fotoPerfil)throws Exception;

    void eliminarFotoPerfil(String fotoPerfil)throws Exception;

    List<ItemClienteDTO> filtrarClientesPorTipo(TIPO_CLIENTE tipoCliente)throws Exception;

}
