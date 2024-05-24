package co.edu.uniquindio.unilocalProyect.controllers;

import co.edu.uniquindio.unilocalProyect.dtos.*;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ComentarioServicio;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.NegocioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/guests")
@RequiredArgsConstructor
public class GuestControlador {
    private final NegocioServicio negocioServicio;
    private final ComentarioServicio comentarioServicio;

    @GetMapping("/buscarNegocio/{nombreNegocio}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> buscarNegocioPorNombre(@PathVariable String nombreNegocio)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,  negocioServicio.filtrarPorNombre(nombreNegocio))
        );
    }

    @PostMapping("/crear-negocio")
    public ResponseEntity<MensajeDTO<String>> crearNegocio(@Valid @RequestBody CrearNegocioDTO crearNegocioDTO)throws Exception{
        negocioServicio.crearNegocio(crearNegocioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Negocio Creado Correctamente")
        );
    }

    @GetMapping("/listar-negocios-propietario/{idCliente}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> listarNegocioPropietario(@PathVariable String idCliente) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocioServicio.listarNegocioPropietario(idCliente)));
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

    @GetMapping("/filtrar-por-nombre/{nombre}")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> filtrarPorNombre(@PathVariable String nombre) throws Exception {
            List<ItemNegocioDTO> negocios = negocioServicio.filtrarPorNombre(nombre);
            return ResponseEntity.ok().body(new MensajeDTO<>(false, negocios));
    }

    @GetMapping("/listar-negocios")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> listarNegocios() throws Exception {
        List<ItemNegocioDTO> negocios = negocioServicio.listarNegocios();
        return ResponseEntity.ok().body(new MensajeDTO<>(false, negocios));
    }

    @PutMapping("/editar-negocio")
    public ResponseEntity<MensajeDTO<String>> editarNegocio(@Valid @RequestBody ActualizarNegocioDTO actualizarNegocioDTO)throws Exception{
        negocioServicio.actualizarNegocio(actualizarNegocioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Negocio Editado Con Exito")
        );
    }

    @DeleteMapping("/eliminar-negocio/{idNegocio}")
    public ResponseEntity<MensajeDTO<String>> eliminarNegocio(@PathVariable String idNegocio)throws Exception{
        negocioServicio.eliminarNegocio(idNegocio);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Negocio eliminado exitosamente")
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






}
