package co.edu.uniquindio.unilocalProyect.servicios.implementaciones;

import co.edu.uniquindio.unilocalProyect.dtos.*;
import co.edu.uniquindio.unilocalProyect.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Moderador;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Negocio;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.HistorialRevision;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_REGISTRO;
import co.edu.uniquindio.unilocalProyect.repositorios.ModeradorRepo;
import co.edu.uniquindio.unilocalProyect.repositorios.NegocioRepo;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ModeradorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ModeradorServicioImp implements ModeradorServicio {

    private final NegocioRepo negocioRepo;
    private final ModeradorRepo moderadorRepo;
    private final EmailServicio emailServicio;


    /**
     * Cambia el estado de la cuenta de moderador a inactiva
     * @param idCuenta id de la cuenta del moderador
     * @throws Exception
     */
    @Override
    public void eliminarCuenta(String idCuenta) throws Exception {

        if (idCuenta.isEmpty()) {
            throw new Exception("El campo id no puede estar vacio");
        }

        Optional<Moderador> optionalModerador = moderadorRepo.findById(idCuenta);

        System.out.println(optionalModerador);

        if (optionalModerador.isEmpty()) {
            throw new ResourceNotFoundException("Moderador no encontrado");
        }

        Moderador moderador = optionalModerador.get();
        moderador.setEstadoRegistro(ESTADO_REGISTRO.INACTIVO);

        moderadorRepo.save(moderador);
    }

    /**
     * Envia un link para recuerar la cuenta del moderador
     * @param email
     * @throws Exception
     */
    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {

        if (email.isEmpty()) {
            throw new Exception("El campo email no debe estar vacio");
        }

        Optional<Moderador> optionalModerador = moderadorRepo.findByEmail(email);

        if (optionalModerador.isEmpty()) {
            throw new ResourceNotFoundException("Moderador no encontrado");
        }

        emailServicio.enviarCorreo(new EmailDTO("Hola, Aqui esta tu link para recuperar tu contraseña"
                ,"https://localhostejemplo",email));
    }

    /**
     * Cambia la contraseña del moderador por la una que se le pasa
     * @param cambioPasswordDTO
     * @return
     * @throws Exception
     */
    @Override
    public void cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {
        Optional<Moderador> moderadorOptional = moderadorRepo.findById(cambioPasswordDTO.id());
        if (moderadorOptional.isEmpty()) {
            throw new Exception("El moderador no se encuentra en la base de datos");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode(cambioPasswordDTO.newPassword());

        Moderador moderador = moderadorOptional.get();
        moderador.setPassword(passwordEncriptada);
    }

    /**
     * Cambia e estado del negocio a APROBADO
     * @param aprobarNegocioDTO
     * @throws Exception
     */
    @Override
    public void aprobarNegocio(AprobarNegocioDTO aprobarNegocioDTO) throws Exception {

        Optional<Negocio> optionalNegocio = negocioRepo.findById(aprobarNegocioDTO.idLugar());

        if (optionalNegocio.isEmpty()) {
            throw new Exception("El negocio no existe");
        }

        HistorialRevision historialRevision = new HistorialRevision();
        historialRevision.setEstadoNegocio(ESTADO_NEGOCIO.APROBADO);
        historialRevision.setCodigoModerador(aprobarNegocioDTO.codigoModerador());
        historialRevision.setFecha(LocalDateTime.now());

        Negocio negocio = optionalNegocio.get();
        negocio.setEstadoNegocio(ESTADO_NEGOCIO.APROBADO);
        negocio.getHistorialRevisiones().add(historialRevision);

        negocioRepo.save(negocio);
    }

    /**
     * Cambia el estado del negocio a RECHAZADO
     * @param rechazarNegocioDTO
     * @throws Exception
     */
    @Override
    public void rechazarNegocio(RechazarNegocioDTO rechazarNegocioDTO) throws Exception {

        Optional<Negocio> optionalNegocio = negocioRepo.findById(rechazarNegocioDTO.idNegocio());

        if (optionalNegocio.isEmpty()) {
            throw new Exception("El negocio no existe");
        }

        Negocio negocio = optionalNegocio.get();

        HistorialRevision historialRevision = new HistorialRevision(
                rechazarNegocioDTO.idModerador(),
                LocalDateTime.now(),
                rechazarNegocioDTO.motivo(),
                ESTADO_NEGOCIO.RECHAZADO
        );
        negocio.setEstadoNegocio(ESTADO_NEGOCIO.RECHAZADO);
        negocio.setEstadoNegocio(ESTADO_NEGOCIO.RECHAZADO);
        negocio.getHistorialRevisiones().add(historialRevision);

        negocioRepo.save(negocio);
    }

    /**
     * Busca un negocio por id
     * @param idNegocio id por el cual se desea buscar el negocio
     * @return Retorna un DetalleNegocioModeradorDTO
     * @throws Exception
     */
    @Override
    public DetalleNegocioModeradorDTO buscarNegocioPorId(String idNegocio) throws Exception {

        if (idNegocio.isEmpty()) {
            throw new Exception("El id del negocio es necesario");
        }

        Optional<Negocio> optionalNegocio = negocioRepo.findById(idNegocio);

        if (optionalNegocio.isEmpty()) {
            throw new ResourceNotFoundException("Negocio no encontrado");
        }

        Negocio negocio = optionalNegocio.get();

        return new DetalleNegocioModeradorDTO(negocio.getNombre(), negocio.getDescripcion(),
                negocio.getCodigoCliente(), negocio.getImagenes(), negocio.getTelefonos(), negocio.getHorarios(),
                negocio.getTipoNegocio(), negocio.getCoordenada(), negocio.getEstadoRegistro(),
                negocio.getHistorialRevisiones());
    }

    /**
     * Filtra todos los negocios que coincidan con el nombre pasado
     * @param nombreNegocio
     * @return Retorna una lista de ItemNegocioModeradorDTO
     * @throws Exception
     */
    @Override
    public List<ItemNegocioModeradorDTO> filtarPorNombreNegocio(String nombreNegocio) throws Exception {
        if (nombreNegocio.isEmpty()) {
            throw new Exception("El nombre del negocio es requerido");
        }
        List<Negocio> negocios = negocioRepo.findByNombreContainingIgnoreCase(nombreNegocio);

        return getNegociosItemDTO(negocios);
    }

    /**
     * Filtra los negocios por el nombre del propietario
     * @param nombrePersona
     * @return Devuelve un lista de ItemNegocioModeradorDTO
     * @throws Exception
     */
    @Override
    public List<ItemNegocioModeradorDTO> filtarNegociosPorNombrePropietario(String nombrePersona) throws Exception {
        if (nombrePersona.isEmpty()) {
            throw new Exception("El nombre de la persona es requerido");
        }
        List<Negocio> negocios = negocioRepo.filtrarPorNombrePropietario(nombrePersona);

        return getNegociosItemDTO(negocios);
    }

    /**
     * Filtra los negocios por el estado de negocio que es pasado por parametro
     * @param estadoNegocio
     * @return Devuelve un lista de ItemNegocioModeradorDTO
     * @throws Exception
     */
    @Override
    public List<ItemNegocioModeradorDTO> filtrarPorEstadoNegocio(ESTADO_NEGOCIO estadoNegocio) throws Exception {
        if (estadoNegocio == null) {
            throw new Exception("El estado negocio es requerido");
        }
        List<Negocio> negocios = negocioRepo.findByEstadoNegocio(estadoNegocio);

        return getNegociosItemDTO(negocios);
    }

    /**
     * Convierte una lista de negocios a una lista de ItemNegocioDTO
     * @param negocios
     * @return Devuelve una lista de ItemNegocioDTO
     */
    private List<ItemNegocioModeradorDTO> getNegociosItemDTO(List<Negocio> negocios) {
        List<ItemNegocioModeradorDTO> items = new ArrayList<>();

        for (Negocio negocio : negocios) {
            items.add(new ItemNegocioModeradorDTO(negocio.getNombre(), negocio.getDescripcion(),
                    negocio.getCodigoCliente(), negocio.getImagenes().get(0), negocio.getEstadoNegocio()));
        }
        return items;
    }
}
