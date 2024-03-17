package co.edu.uniquindio.unilocalProyect.servicios.interfaces;

import co.edu.uniquindio.unilocalProyect.dtos.ActualizarClienteDTO;
import co.edu.uniquindio.unilocalProyect.dtos.RegistroClienteDTO;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Cliente;

import java.util.Optional;

public interface ClienteServicio {
    String registrarCliente(RegistroClienteDTO registroClienteDTO)throws Exception;

    void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO)throws Exception;



}
