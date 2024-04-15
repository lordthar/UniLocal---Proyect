package co.edu.uniquindio.unilocalProyect.servicios.implementaciones;

import co.edu.uniquindio.unilocalProyect.dtos.*;
import co.edu.uniquindio.unilocalProyect.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Moderador;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Negocio;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.HistorialRevision;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.repositorios.ModeradorRepo;
import co.edu.uniquindio.unilocalProyect.repositorios.NegocioRepo;
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

    @Override
    public void eliminarCuenta(String idCuenta) throws Exception {

    }

    @Override
    public void enviarLinkRecuperacion(String email) throws Exception {

    }

    @Override
    public String cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {
        Optional<Moderador> moderadorOptional = moderadorRepo.findById(cambioPasswordDTO.id());
        if (moderadorOptional.isEmpty()) {
            throw new Exception("El moderador no se encuentra en la base de datos");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String passwordEncriptada = passwordEncoder.encode(cambioPasswordDTO.newPassword());

        Moderador moderador = moderadorOptional.get();
        moderador.setPassword(passwordEncriptada);

        return null;
    }

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
    }

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
    }

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

    @Override
    public List<ItemNegocioModeradorDTO> filtarPorNombreNegocio(String nombreNegocio) throws Exception {
        if (nombreNegocio.isEmpty()) {
            throw new Exception("El nombre del negocio es requerido");
        }
        List<Negocio> negocios = negocioRepo.findByNombreContainingIgnoreCase(nombreNegocio);

        return getNegociosItemDTO(negocios);
    }

    @Override
    public List<ItemNegocioModeradorDTO> filtarNegociosPorNombrePropietario(String nombrePersona) throws Exception {
        if (nombrePersona.isEmpty()) {
            throw new Exception("El nombre de la persona es requerido");
        }
        List<Negocio> negocios = negocioRepo.filtrarPorNombrePropietario(nombrePersona);

        return getNegociosItemDTO(negocios);
    }

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
     * @return
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
