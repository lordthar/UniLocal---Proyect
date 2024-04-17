package co.edu.uniquindio.unilocalProyect.servicios.implementaciones;

import co.edu.uniquindio.unilocalProyect.dtos.LoginDTO;
import co.edu.uniquindio.unilocalProyect.dtos.TokenDTO;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Cliente;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Moderador;
import co.edu.uniquindio.unilocalProyect.repositorios.ClienteRepo;
import co.edu.uniquindio.unilocalProyect.repositorios.ModeradorRepo;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.AutenticacionServicio;
import co.edu.uniquindio.unilocalProyect.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AutenticacionServicioImp implements AutenticacionServicio {

    private final ClienteRepo clienteRepo;
    private final ModeradorRepo moderadorRepo;
    private final JWTUtils jwtUtils;

    @Override
    public TokenDTO iniciarSesionCliente(LoginDTO loginDTO) throws Exception {
        Optional<Cliente> clienteOptional = clienteRepo.findByEmail(loginDTO.email());

        if(clienteOptional.isEmpty()){
            throw new Exception("El correo proporcionado no se encuentra registrado");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Cliente cliente = clienteOptional.get();

        if(!passwordEncoder.matches(loginDTO.password(), cliente.getPassword())){
            throw new Exception("La contraseña ingresada es incorrecta, intente de nuevo");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("rol", "CLIENTE");
        map.put("nombre", cliente.getNombre());
        map.put("id", cliente.getCodigo());
        return new TokenDTO( jwtUtils.generarToken(cliente.getEmail(), map) );
    }

    @Override
    public TokenDTO iniciarSesionModerador(LoginDTO loginDTO) throws Exception {

        Optional<Moderador> optionalModerador = moderadorRepo.findByEmail(loginDTO.email());

        if (optionalModerador.isEmpty()) {
            throw new Exception("El correo no se encuentra registrado");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Moderador moderador = optionalModerador.get();

        if (!passwordEncoder.matches(loginDTO.password(), moderador.getPassword())) {
            throw new Exception("La contraseña es incorrecta");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("rol", "MODERADOR");
        map.put("nickname", moderador.getNickname());
        map.put("id", moderador.getCodigo());

        return new TokenDTO(jwtUtils.generarToken(moderador.getEmail(), map));
    }
}
