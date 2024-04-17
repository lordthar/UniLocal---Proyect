package co.edu.uniquindio.unilocalProyect.servicios.interfaces;

import co.edu.uniquindio.unilocalProyect.dtos.ActualizarClienteDTO;
import co.edu.uniquindio.unilocalProyect.dtos.DetalleClienteDTO;
import co.edu.uniquindio.unilocalProyect.dtos.ItemClienteDTO;
import co.edu.uniquindio.unilocalProyect.dtos.RegistroClienteDTO;

import java.util.List;

public interface ClienteServicio extends CuentaServicio{
    String registrarCliente(RegistroClienteDTO registroClienteDTO)throws Exception;

    void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO)throws Exception;

    void anularSubscripcion(String idCliente) throws Exception;

    void pagarSuscripcion(String idCliente) throws Exception;
    DetalleClienteDTO obtenerCliente(String idCuenta)throws Exception;

    List<ItemClienteDTO> listarClientes();

}
