package co.edu.uniquindio.unilocalProyect.controllers;

import co.edu.uniquindio.unilocalProyect.dtos.*;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ComentarioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comentarios")
@RequiredArgsConstructor
public class ComentarioControlador {

    private final ComentarioServicio comentarioServicio;

    @PostMapping("/crear-comentario")
    public ResponseEntity<MensajeDTO<String>> crearComentario(@Valid @RequestBody CrearComentarioDTO crearComentarioDTO)throws Exception{
        comentarioServicio.crearComentario(crearComentarioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Comentario Creado Con Exito")
        );
    }
    @PutMapping("/editar-comentario")
    public ResponseEntity<MensajeDTO<String>> editarComentario(@Valid @RequestBody EditarComentarioDTO editarComentarioDTO)throws Exception{
        comentarioServicio.editarComentario(editarComentarioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Comentario actualizado correctamente"));
    }

    @DeleteMapping("/eliminar/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarComentario(@PathVariable String codigo)throws Exception{
        comentarioServicio.eliminarComentario(codigo);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Comentario eliminado correctamente")
        );
    }
    @GetMapping("/obtener/{codigo}")
    public ResponseEntity<MensajeDTO<DetalleComentarioDTO>> obtenerComentario(@PathVariable String codigo) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.obtenerComentario(codigo) ) );
    }

    @PostMapping("/responder-comentario")
    public ResponseEntity<MensajeDTO<String>> respoderComentario(@Valid @RequestBody ResponderComentarioDTO responderComentarioDTO)throws Exception{
        comentarioServicio.responderComentario(responderComentarioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Comentario Respondido")
        );
    }


}
