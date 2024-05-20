package co.edu.uniquindio.unilocalProyect.controllers;

import co.edu.uniquindio.unilocalProyect.dtos.LoginDTO;
import co.edu.uniquindio.unilocalProyect.dtos.MensajeDTO;
import co.edu.uniquindio.unilocalProyect.dtos.RegistroClienteDTO;
import co.edu.uniquindio.unilocalProyect.dtos.TokenDTO;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.AutenticacionServicio;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ClienteServicio;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AutenticacionControlador {
    private final AutenticacionServicio autenticacionServicio;
    private final ClienteServicio clienteServicio;
    @PostMapping("/login-cliente")
    public ResponseEntity<MensajeDTO<TokenDTO>> iniciarSesionCliente(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
        TokenDTO tokenDTO = autenticacionServicio.iniciarSesionCliente(loginDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));
    }

    @PostMapping("/registrar-cliente")
    public ResponseEntity<MensajeDTO<String>> registrarCliente(@Valid @RequestBody RegistroClienteDTO registroClienteDTO)throws Exception{
        clienteServicio.registrarCliente(registroClienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Cliente registrado correctamente")
        );
    }

    @PostMapping("/login-Moderador")
    public ResponseEntity<MensajeDTO<TokenDTO>> iniciarSesionModerador(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
        TokenDTO tokenDTO = autenticacionServicio.iniciarSesionModerador(loginDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, tokenDTO));
    }
}
