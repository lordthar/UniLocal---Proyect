package co.edu.uniquindio.unilocalProyect.servicios.implementaciones;

import co.edu.uniquindio.unilocalProyect.dtos.*;
import co.edu.uniquindio.unilocalProyect.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Cliente;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.Imagen;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_REGISTRO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_CLIENTE;
import co.edu.uniquindio.unilocalProyect.repositorios.ClienteRepo;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ImagenesServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor

public class ClienteServicioImp implements ClienteServicio {

    private final ClienteRepo clienteRepo;
    private final EmailServicio emailServicio;
    private final ImagenesServicio imagenesServicio;
    @Override
    public String registrarCliente(RegistroClienteDTO registroClienteDTO) throws Exception {

        if( existeEmail(registroClienteDTO.email()) ){
            throw new Exception("El correo ya se encuentra registrado");
        }

        if( existeNickname(registroClienteDTO.nickname()) ){
            throw new Exception("El nickname ya se encuentra registrado por otro usuario");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode(registroClienteDTO.password());
        Cliente cliente = new Cliente();
        cliente.setNombre(registroClienteDTO.nombre());
        cliente.setNickname(registroClienteDTO.nickname());
        cliente.setFotoPerfil(new Imagen(null,registroClienteDTO.fotoPerfil()));
        cliente.setEmail(registroClienteDTO.email());
        cliente.setPassword(passwordEncriptada);
        cliente.setCiudadResidencia(registroClienteDTO.ciudadRecidencia());
        cliente.setTelefonos(registroClienteDTO.telefonos());
        cliente.setEstadoRegistro(ESTADO_REGISTRO.ACTIVO);
        cliente.setTipoCliente(TIPO_CLIENTE.NORMAL);

        try{
            clienteRepo.save(cliente);
        }catch(Exception e){
            throw new Exception("Algo Salio mal");
        }
        emailServicio.enviarCorreo(new EmailDTO("Bienvenido a Unilocal " + registroClienteDTO.nombre(), "Nos alegra que estes con nosotros", registroClienteDTO.email()));
        return cliente.getCodigo();
    }

    private boolean existeNickname(String nickname) throws Exception {
        return clienteRepo.findByNickname(nickname).isPresent();
    }

    private Boolean existeEmail(String email) throws Exception {
        return clienteRepo.findClienteByEmail(email).isPresent();
    }

    @Override
    public void actualizarCliente(ActualizarClienteDTO actualizarClienteDTO) throws Exception {
        Optional<Cliente> clienteOptional = clienteRepo.findById(actualizarClienteDTO.id());

        if(clienteOptional.isEmpty()){
            throw new Exception("No existe un cliente con el id : " + actualizarClienteDTO.id());
        }
        Cliente cliente = clienteOptional.get();
        cliente.setNombre( actualizarClienteDTO.nombre() );
        cliente.setFotoPerfil(new Imagen(actualizarClienteDTO.fotoPerfil(), null)  );
        cliente.setCiudadResidencia( actualizarClienteDTO.ciudadRecidencia());
        cliente.setTelefonos((actualizarClienteDTO.telefonos()));

        clienteRepo.save(cliente);
    }

    @Override
    public void anularSubscripcion(String idCliente) throws Exception {
        Optional<Cliente> optionalCliente = clienteRepo.findById(idCliente);

        if (optionalCliente.isEmpty()) {
            throw new Exception("No se encontró al cliente con el ID: " + idCliente);
        }
        Cliente cliente = optionalCliente.get();
        cliente.setTipoCliente(TIPO_CLIENTE.NORMAL);
        clienteRepo.save(cliente);
    }

    @Override
    public void pagarSuscripcion(String idCliente) throws Exception {
        Optional<Cliente> optionalCliente = clienteRepo.findById(idCliente);

        if (optionalCliente.isEmpty()) {
            throw new Exception("No se encontró al cliente con el ID: " + idCliente);
        }
        Cliente cliente = optionalCliente.get();
        cliente.setTipoCliente(TIPO_CLIENTE.PREMIUM);
        clienteRepo.save(cliente);
    }


    public DetalleClienteDTO obtenerCliente (String idCuenta) throws Exception {
        Optional<Cliente> optionalCliente = clienteRepo.findById(idCuenta);
        if (optionalCliente.isEmpty()) {
            throw new Exception("No se encontró el cliente a con el id " + idCuenta);
        }
        Cliente cliente = optionalCliente.get();
        if(cliente.getEstadoRegistro().equals(ESTADO_REGISTRO.INACTIVO)){
            throw new Exception("El cliente al que intenta buscar no esta activo");
        }
        return new DetalleClienteDTO(cliente.getCodigo(), cliente.getNombre(),
        cliente.getFotoPerfil().getUrlImagen(), cliente.getNickname(), cliente.getEmail(), cliente.getCiudadResidencia(), cliente.getTelefonos());
    }


    private List<ItemClienteDTO> listarClientes(List<Cliente> clientes){
        List<ItemClienteDTO> items = new ArrayList<>();

        for (Cliente cliente : clientes) {
            items.add(new ItemClienteDTO(cliente.getNickname(),
                   cliente.getFotoPerfil().getUrlImagen(), cliente.getCiudadResidencia(), cliente.getTipoCliente()));
        }
        return items;
    }

    @Override
    public List<ItemClienteDTO> filtrarClientesPorTipo(TIPO_CLIENTE tipoCliente) throws Exception {
        List<Cliente> clienteList = clienteRepo.findClienteByTipoCliente(tipoCliente);
        return listarClientes(clienteList);
    }

    @Override
    public void eliminarCuenta(String idCuenta) throws Exception {
        Optional<Cliente> optionalCliente = clienteRepo.findById(idCuenta);

        if(optionalCliente.isEmpty()){
            throw new Exception("waos");
        }

        Cliente cliente = optionalCliente.get();
        cliente.setEstadoRegistro(ESTADO_REGISTRO.INACTIVO);

        clienteRepo.save(cliente);
    }

    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {
        Optional<Cliente> clienteOptional = clienteRepo.findClienteByEmail(email);
        if(clienteOptional.isEmpty()){
            throw new ResourceNotFoundException("El email que ingreso no existe");
        }
        emailServicio.enviarCorreo(new EmailDTO("Hola, Aqui esta tu link para recuperar tu contraseña","https://localhostejemplo",email));
    }

    @Override
    public void cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {
        Optional<Cliente> optionalCliente = clienteRepo.findById(cambioPasswordDTO.id());
        if (optionalCliente.isEmpty()) {
            throw new Exception("No se encontró al cliente con el ID: " + cambioPasswordDTO.id());
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode(cambioPasswordDTO.newPassword());
        Cliente cliente = optionalCliente.get();
        cliente.setPassword(passwordEncriptada);

        clienteRepo.save(cliente);
    }


    @Override
    public void subirFotoCliente(MultipartFile fotoPerfil) throws Exception {
        imagenesServicio.subirImagen(fotoPerfil);
    }

    @Override
    public void eliminarFotoPerfil(String fotoPerfilId) throws Exception {
        imagenesServicio.eliminarImagen(fotoPerfilId);
    }


}
