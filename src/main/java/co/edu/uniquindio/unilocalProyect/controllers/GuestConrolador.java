package co.edu.uniquindio.unilocalProyect.controllers;

import co.edu.uniquindio.unilocalProyect.dtos.ItemComantarioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.ItemNegocioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.MensajeDTO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ComentarioServicio;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ModeradorServicio;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.NegocioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/guests")
@RequiredArgsConstructor
public class GuestConrolador {
    private final ComentarioServicio comentarioServicio;
    private final ModeradorServicio moderadorServicio;
    private final NegocioServicio negocioServicio;

    @GetMapping("/buscarNegocio/{nombreNegocio}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> buscarNegocioPorNombre(@PathVariable String nombreNegocio)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,  negocioServicio.filtrarPorNombre(nombreNegocio))
        );
    }

    @GetMapping("/buscarTipoNegocio/{tipoNegocio}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> buscarNegocioPorTipo(@PathVariable TIPO_NEGOCIO tipoNegocio)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.filtrarPorTipoNegocio(tipoNegocio))
        );
    }

    @GetMapping("/listaComentarios")
    public ResponseEntity<MensajeDTO<List<ItemComantarioDTO>>> listarComentariosGuest()throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.listarComentarios())
        );
    }
    @GetMapping("/filtrar-calificacion/{calificacion}")
    public ResponseEntity<MensajeDTO<List<ItemComantarioDTO>>> filtrarComentarioCalificacion(@PathVariable int calificacion)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.filtrarComentarioPorCalificacion(calificacion))
        );
    }
    @GetMapping("/filtrar-fechaComentario/{fechaComentario}")
    public ResponseEntity<MensajeDTO<List<ItemComantarioDTO>>> filtrarComentarioPorFecha(@PathVariable LocalDateTime fechaComentario)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.filtrarComentarioPorFecha(fechaComentario))
        );
    }
    @GetMapping("/filtrar-comentarioCliente/{idCliente}")
    public ResponseEntity<MensajeDTO<List<ItemComantarioDTO>>> filtrarComentarioPorCliente(@PathVariable String idCliente)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.filtrarComentarioPorCliente(idCliente))
        );
    }

    @PutMapping("/recuperacion-cuenta/{email}")
    public ResponseEntity<MensajeDTO<String>> enviarLinkRecuperacionModerador(@PathVariable String email) throws Exception {
        moderadorServicio.enviarLinkRecuperacion(email);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Se ha enviado el link de recuperacion"));
    }
}
