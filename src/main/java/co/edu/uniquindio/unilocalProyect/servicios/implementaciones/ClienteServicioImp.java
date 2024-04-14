package co.edu.uniquindio.unilocalProyect.servicios.implementaciones;

import co.edu.uniquindio.unilocalProyect.dtos.ActualizarClienteDTO;
import co.edu.uniquindio.unilocalProyect.dtos.DetalleClienteDTO;
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

    private final ClienteRepo clienteRepo;
    @Override
    public String registrarCliente(RegistroClienteDTO registroClienteDTO) throws Exception {

        if( existeEmail(registroClienteDTO.Email()) ){
            throw new Exception("El correo ya se encuentra registrado");
        }

        if( existeNickname(registroClienteDTO.nickname()) ){
            throw new Exception("El nickname ya se encuentra registrado por otro usuario");
        }

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

    private boolean existeNickname(String nickname) throws Exception {
        return clienteRepo.findByNickname(nickname).isPresent();
    }

    private Boolean existeEmail(String email) throws Exception {
        return clienteRepo.findByEmail(email).isPresent();
    }

    @Override
    public void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO) throws Exception {
        Optional<Cliente> clienteOptional = clienteRepo.findById(actualizarClienteDTO.id());

        if(clienteOptional.isEmpty()){
            throw new Exception("No existe un cliente con el id : " + actualizarClienteDTO.id());
        }
        Cliente cliente = clienteOptional.get();
        cliente.setNombre( actualizarClienteDTO.nombre() );
        cliente.setFotoPerfil( actualizarClienteDTO.fotoPerfil() );
        cliente.setCiudadResidencia( actualizarClienteDTO.ciudadRecidencia());
        cliente.setEmail( actualizarClienteDTO.Email());

        clienteRepo.save(cliente);
    }

    @Override
    public void eliminarCliente (String idCuenta) throws Exception{
        Optional<Cliente> optionalCliente = clienteRepo.findById(idCuenta);

        if(optionalCliente.isEmpty()){
            throw new Exception("waos");
        }

        Cliente cliente = optionalCliente.get();
        cliente.setEstadoRegistro(ESTADO_REGISTRO.INACTIVO);

        clienteRepo.save(cliente);
    }


    public DetalleClienteDTO obtenerCliente (String idCuenta) throws Exception {
        Optional<Cliente> optionalCliente = clienteRepo.findById(idCuenta);
        if (optionalCliente.isEmpty()) {
            throw new Exception("No se encontró el cliente a con el id " + idCuenta);
        }
        Cliente cliente = optionalCliente.get();
        return new DetalleClienteDTO(cliente.getCodigo(), cliente.getNombre(),
        cliente.getFotoPerfil(), cliente.getNickname(), cliente.getEmail(), cliente.getCiudadResidencia());
    }

}
