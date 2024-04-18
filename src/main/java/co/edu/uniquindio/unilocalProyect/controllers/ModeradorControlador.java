package co.edu.uniquindio.unilocalProyect.controllers;

import co.edu.uniquindio.unilocalProyect.dtos.*;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ModeradorServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/moderadores")
@RequiredArgsConstructor
public class ModeradorControlador {

    private final ModeradorServicio moderadorServicio;

    @PutMapping("/aprobar-negocio")
    public ResponseEntity<MensajeDTO<String>> aprobarNegocio(@Valid @RequestBody AprobarNegocioDTO aprobarNegocioDTO)
            throws Exception {
        moderadorServicio.aprobarNegocio(aprobarNegocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Negocio aprobado correctamente"));
    }

    @PutMapping("/rechazar-negocio")
    public ResponseEntity<MensajeDTO<String>> rechazarNegocio(@Valid @RequestBody RechazarNegocioDTO rechazarNegocioDTO)
            throws Exception {
        moderadorServicio.rechazarNegocio(rechazarNegocioDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Negocio denegado correctamente"));

    }

    @GetMapping("/buscar-negocio/{idNegocio}")
    public ResponseEntity<MensajeDTO<DetalleNegocioModeradorDTO>> buscarNegocioPorId(@PathVariable String idNegocio)
            throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, moderadorServicio.buscarNegocioPorId(idNegocio)));

    }

    @GetMapping("/filtrar-negocio-nombre/{nombreNegocio}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioModeradorDTO>>> filtrarPorNombreNegocio(@PathVariable String nombreNegocio)
            throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, moderadorServicio.filtrarPorNombreNegocio(nombreNegocio)));
    }

    @GetMapping("/filtrar-negocio-nombre-propietario/{nombrePropietario}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioModeradorDTO>>> filtrarNegociosPorNombrePropietario(@PathVariable String nombrePropietario)
            throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, moderadorServicio.filtrarNegociosPorNombrePropietario(nombrePropietario)));
    }

    @GetMapping("/filtrar-negocio-estado-negocio/{estadoNegocio}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioModeradorDTO>>> filtrarPorEstadoNegocio(@PathVariable ESTADO_NEGOCIO estadoNegocio) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, moderadorServicio.filtrarPorEstadoNegocio(estadoNegocio)));
    }

}
