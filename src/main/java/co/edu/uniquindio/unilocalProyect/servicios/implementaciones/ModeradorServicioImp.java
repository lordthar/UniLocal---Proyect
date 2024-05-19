package co.edu.uniquindio.unilocalProyect.servicios.implementaciones;

import co.edu.uniquindio.unilocalProyect.dtos.CambioPasswordDTO;
import co.edu.uniquindio.unilocalProyect.dtos.DetalleModeradorDTO;
import co.edu.uniquindio.unilocalProyect.dtos.EmailDTO;
import co.edu.uniquindio.unilocalProyect.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Moderador;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_REGISTRO;
import co.edu.uniquindio.unilocalProyect.repositorios.ModeradorRepo;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ModeradorServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ModeradorServicioImp implements ModeradorServicio {

    private final ModeradorRepo moderadorRepo;
    private final EmailServicio emailServicio;


    /**
     * Cambia el estado de la cuenta de moderador a inactiva
     * @param idCuenta id de la cuenta del moderador
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
     */
    @Override
    public void cambiarPassword(CambioPasswordDTO cambioPasswordDTO) throws Exception {
        Optional<Moderador> moderadorOptional = moderadorRepo.findById(cambioPasswordDTO.id());
        if (moderadorOptional.isEmpty()) {
            throw new Exception("El moderador no se encuentra en la base de datos");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Moderador moderador = moderadorOptional.get();

        if (!passwordEncoder.matches(cambioPasswordDTO.actualPassword(), moderador.getPassword())) {
            throw new Exception("La contraseña no coincide");
        }
        if (passwordEncoder.matches(cambioPasswordDTO.newPassword(), moderador.getPassword())) {
            throw new Exception("La contraseña no puede ser igual a la anterior");
        }

        String passwordEncriptada = passwordEncoder.encode(cambioPasswordDTO.newPassword());
        moderador.setPassword(passwordEncriptada);

        moderadorRepo.save(moderador);
    }

    /**
     * Obtiene un moderador con base a su codigo y si este se encuentra activo
     */
    @Override
    public DetalleModeradorDTO obtenerModerador(String codigoModerador) throws ResourceNotFoundException {
        Optional<Moderador> optionalModerador = moderadorRepo.findById(codigoModerador);

        if (optionalModerador.isEmpty() || optionalModerador.get().getEstadoRegistro() == ESTADO_REGISTRO.INACTIVO) {
            throw new ResourceNotFoundException("Moderador no encontrado");
        }

        Moderador moderador = optionalModerador.get();

        return new DetalleModeradorDTO(moderador.getCodigo(), moderador.getNickname());
    }
}
