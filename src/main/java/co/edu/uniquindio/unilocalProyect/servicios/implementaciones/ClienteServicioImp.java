package co.edu.uniquindio.unilocalProyect.servicios.implementaciones;

import co.edu.uniquindio.unilocalProyect.dtos.ActualizarClienteDTO;
import co.edu.uniquindio.unilocalProyect.dtos.DetalleClienteDTO;
import co.edu.uniquindio.unilocalProyect.dtos.EliminarClienteDTO;
import co.edu.uniquindio.unilocalProyect.dtos.RegistroClienteDTO;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Cliente;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_REGISTRO;
import co.edu.uniquindio.unilocalProyect.repositorios.ClienteRepo;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ClienteServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor

public class ClienteServicioImp implements ClienteServicio {

    private ClienteRepo clienteRepo;
    @Override
    public String registrarCliente(RegistroClienteDTO registroClienteDTO) throws Exception {
        Cliente cliente = new Cliente();
        cliente.setNombre(registroClienteDTO.nombre());
        cliente.setEmail(registroClienteDTO.Email());
        cliente.setPassword(registroClienteDTO.password());
        cliente.setFotoPerfil(registroClienteDTO.fotoPerfil());
        cliente.setCiudadResidencia(registroClienteDTO.ciudadRecidencia());
        cliente.setEstadoRegistro(ESTADO_REGISTRO.ACTIVO);
        Cliente clienteGuardado = clienteRepo.save(cliente);

        return clienteGuardado.getCodigo();
    }

    private Cliente existeEmail(String email) throws Exception {
        Optional<Cliente> clienteOptional = clienteRepo.findByEmail(email);

        if(clienteOptional.isEmpty()){
            throw new Exception("No existe este email");
        }

        return clienteOptional.get();
    }

    @Override
    public void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO) throws Exception {
        Optional<Cliente> clienteOptional = clienteRepo.findById(actualizarClienteDTO.id());

        if(clienteOptional.isEmpty()){
            throw new Exception("No existe un cliente con el id : " + actualizarClienteDTO.id());
        }
        Cliente cliente = clienteOptional.get();
        cliente.setNombre(actualizarClienteDTO.nombre());
        cliente.setCiudadResidencia(actualizarClienteDTO.ciudadRecidencia());

        clienteRepo.save(cliente);
    }

    public void eliminarCliente (EliminarClienteDTO eliminarClienteDTO) throws Exception{
        Optional<Cliente> optionalCliente = clienteRepo.findById(eliminarClienteDTO.codigo());

        if(optionalCliente.isEmpty()){
            throw new Exception("waos");
        }

        Cliente cliente = optionalCliente.get();
        cliente.setEstadoRegistro(ESTADO_REGISTRO.INACTIVO);

        clienteRepo.save(cliente);
    }


    public Cliente buscarCliente(DetalleClienteDTO detalleClienteDTO) throws Exception {

        return null;
    }

    public DetalleClienteDTO obtenerCliente (String idCuenta) throws Exception{
        Optional<Cliente> optionalCliente = clienteRepo.findById(idCuenta);

        if(optionalCliente.isEmpty()){
            throw new Exception("ok");
        }

        Cliente cliente = optionalCliente.get();
        return new DetalleClienteDTO(
                cliente.getNombre(),
                cliente.getNickname(),
                cliente.getEmail(),
                cliente.getCiudadResidencia(),
                cliente.getFotoPerfil(),
                cliente.getCodigo()
        );
    }
    public void enviarLinkRecuperacion(String email) throws Exception{
        Optional<Cliente> optionalCliente = clienteRepo.findByEmail(email);

        if(optionalCliente == null){
            throw new Exception("Error este email no esta asociado a ningun cliente");
        }
    }
}
