package co.edu.uniquindio.unilocalProyect.servicios.implementaciones;

import co.edu.uniquindio.unilocalProyect.dtos.*;
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
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ModeradorServicioImp implements ModeradorServicio {

    private final NegocioRepo negocioRepo;
    private final ModeradorRepo moderadorRepo;
    @Override
    public void loginCuenta(LoginCuentaDTO loginCuentaDTO) throws Exception {

    }

    @Override
    public void sesionCuenta(SesionCuentaDTO sesionCuentaDTO) throws Exception {

    }

    @Override
    public String cambioContraseña(CambioPasswordDTO cambioPasswordDTO) throws Exception {
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
    public List<DetalleNegocioModeradorDTO> filtarPorNombreNegocio(String nombreNegocio) throws Exception {
        if (nombreNegocio.isEmpty()) {
            throw new Exception("El nombre del negocio es requerido");
        }
        List<DetalleNegocioModeradorDTO> negociosFiltradosPorNombre = negocioRepo.filtrarPorNombreNegociosModerador(nombreNegocio);
        return negociosFiltradosPorNombre;
    }

    @Override
    public List<DetalleNegocioModeradorDTO> filtarNegociosPorNombrePropietario(String nombrePersona) throws Exception {
        if (nombrePersona.isEmpty()) {
            throw new Exception("El nombre de la persona es requerido");
        }
        List<DetalleNegocioModeradorDTO> negociosFiltradosPorNombrePropietario = negocioRepo.filtrarPorNombrePropietario(nombrePersona);
        return negociosFiltradosPorNombrePropietario;
    }

    @Override
    public List<DetalleNegocioModeradorDTO> filtrarPorEstadoNegocio(ESTADO_NEGOCIO estadoNegocio) throws Exception {
        if (estadoNegocio != null) {
            throw new Exception("El estado negocio es requerido");
        }
        List<DetalleNegocioModeradorDTO> negociosEstado = negocioRepo.findByEstadoNegocio(estadoNegocio);
        return negociosEstado;
    }
}
