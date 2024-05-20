package co.edu.uniquindio.unilocalProyect.servicios.interfaces;

import co.edu.uniquindio.unilocalProyect.dtos.ActualizarClienteDTO;
import co.edu.uniquindio.unilocalProyect.dtos.DetalleClienteDTO;
import co.edu.uniquindio.unilocalProyect.dtos.ItemClienteDTO;
import co.edu.uniquindio.unilocalProyect.dtos.RegistroClienteDTO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_CLIENTE;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ClienteServicio extends CuentaServicio{
    String registrarCliente(RegistroClienteDTO registroClienteDTO)throws Exception;

    void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO)throws Exception;

    void anularSubscripcion(String idCliente) throws Exception;

    void pagarSuscripcion(String idCliente) throws Exception;
    DetalleClienteDTO obtenerCliente(String idCuenta)throws Exception;

    void subirFotoCliente(MultipartFile fotoPerfil)throws Exception;

    void eliminarFotoPerfil(String fotoPerfil)throws Exception;

    List<ItemClienteDTO> filtrarClientesPorTipo(TIPO_CLIENTE tipoCliente)throws Exception;

}
