package co.edu.uniquindio.unilocalProyect.servicios.interfaces;

import co.edu.uniquindio.unilocalProyect.dtos.ActualizarClienteDTO;
import co.edu.uniquindio.unilocalProyect.dtos.DetalleClienteDTO;
import co.edu.uniquindio.unilocalProyect.dtos.RegistroClienteDTO;

public interface ClienteServicio {
    String registrarCliente(RegistroClienteDTO registroClienteDTO)throws Exception;

    void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO)throws Exception;

    void eliminarCliente(String idCuenta)throws Exception;

    DetalleClienteDTO obtenerCliente(String idCuenta)throws Exception;

}
