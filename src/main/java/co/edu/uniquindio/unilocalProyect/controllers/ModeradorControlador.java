package co.edu.uniquindio.unilocalProyect.controllers;

import co.edu.uniquindio.unilocalProyect.dtos.*;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ModeradorServicio;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.NegocioServicio;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moderadores")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class ModeradorControlador {

    private final ModeradorServicio moderadorServicio;
    private final NegocioServicio negocioServicio;

    @GetMapping("/listar-estados-negocio")
    public ResponseEntity<MensajeDTO<List<String>>> listarEstadosNegocio() throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.listarEstadosNegocio()));
    }

    @DeleteMapping("/eliminar-cuenta/{idCuenta}")
    public ResponseEntity<MensajeDTO<String>> eliminarCuenta(@PathVariable String idCuenta) throws Exception {
        moderadorServicio.eliminarCuenta(idCuenta);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Moderador eliminado correctamente"));
    }

    @PutMapping("/cambiar-password")
    public ResponseEntity<MensajeDTO<String>> cambiarPassword(@Valid @RequestBody CambioPasswordDTO cambioPasswordDTO) throws Exception {
        moderadorServicio.cambiarPassword(cambioPasswordDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se cambio la password correctamente"));
    }

    @PutMapping("/aprobar-negocio")
    public ResponseEntity<MensajeDTO<String>> aprobarNegocio(@Valid @RequestBody AprobarNegocioDTO aprobarNegocioDTO)
            throws Exception {
        negocioServicio.aprobarNegocio(aprobarNegocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Negocio aprobado correctamente"));
    }

    @PutMapping("/rechazar-negocio")
    public ResponseEntity<MensajeDTO<String>> rechazarNegocio(@Valid @RequestBody RechazarNegocioDTO rechazarNegocioDTO)
            throws Exception {
        negocioServicio.rechazarNegocio(rechazarNegocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Negocio denegado correctamente"));

    }

    @GetMapping("/obtener-moderador/{idModerador}")
    public ResponseEntity<MensajeDTO<DetalleModeradorDTO>> obtenerModerador(@PathVariable String idModerador)
            throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, moderadorServicio.obtenerModerador(idModerador)));
    }

    @GetMapping("/buscar-negocio/{idNegocio}")
    public ResponseEntity<MensajeDTO<DetalleNegocioDTO>> buscarNegocioPorId(@PathVariable String idNegocio)
            throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.buscarNegocio(idNegocio)));
    }

    @GetMapping("/filtrar-negocio-nombre/{nombreNegocio}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> filtrarPorNombreNegocio(@PathVariable String nombreNegocio)
            throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.filtrarPorNombre(nombreNegocio)));
    }

    @GetMapping("/filtrar-negocio-nombre-propietario/{nombrePropietario}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> filtrarNegociosPorNombrePropietario(@PathVariable String nombrePropietario)
            throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.buscarPorNombrePropietario(nombrePropietario)));
    }

    @GetMapping("/filtrar-negocio-estado-negocio/{estadoNegocio}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> filtrarPorEstadoNegocio(@PathVariable ESTADO_NEGOCIO estadoNegocio) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.filtrarPorEstadoNegocio(estadoNegocio)));
    }
}
