package co.edu.uniquindio.unilocalProyect.controllers;

import co.edu.uniquindio.unilocalProyect.dtos.*;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_PQRS;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.PqrsServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pqrs")
@RequiredArgsConstructor
public class PqrsControlador {

    private final PqrsServicio pqrsServicio;

    @PostMapping("/crear-pqrs")
    public ResponseEntity<MensajeDTO<String>> crearPqrs(@Valid @RequestBody CrearPqrsDTO crearPqrsDTO) throws Exception {
        pqrsServicio.crearPqrs(crearPqrsDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Pqrs creado correctamente")
        );
    }

    @PostMapping("/editar-pqrs")
    public ResponseEntity<MensajeDTO<String>> editarPqrs(@Valid @RequestBody EditarPqrsDTO editarPqrsDTO) throws Exception {
        pqrsServicio.editarPqrs(editarPqrsDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Pqrs actualizado correctamente"));
    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarPqrs(@PathVariable String codigo) throws
            Exception {
        pqrsServicio.eliminarPqrs(codigo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Pqrs eliminado correctamente")
        );
    }

    @GetMapping("/obtener/{codigo}")
    public ResponseEntity<MensajeDTO<DetallePqrsDTO>> obtenerPqrs(@PathVariable String codigo) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false,
                pqrsServicio.obtenerPqrs(codigo)));
    }

    @GetMapping("/filtrar-tipo")
    public ResponseEntity<MensajeDTO<List<ItemPqrsDTO>>> filtrarPqrsPorTipo(@PathVariable TIPO_PQRS tipoPqrs) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, pqrsServicio.filtrarPqrsPorTipo(tipoPqrs))
        );
    }
}
