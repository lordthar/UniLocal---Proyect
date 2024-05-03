package co.edu.uniquindio.unilocalProyect.controllers;

import co.edu.uniquindio.unilocalProyect.dtos.*;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_CLIENTE;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_PQRS;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ComentarioServicio;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.NegocioServicio;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.PqrsServicio;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class ClienteControlador {
    private final ClienteServicio clienteServicio;
    private final ComentarioServicio comentarioServicio;
    private final PqrsServicio pqrsServicio;
    private final NegocioServicio negocioServicio;
    @PostMapping("/registrar-cliente")
    public ResponseEntity<MensajeDTO<String>> registrarCliente(@Valid @RequestBody RegistroClienteDTO registroClienteDTO)throws Exception{
        clienteServicio.registrarCliente(registroClienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Cliente registrado correctamente")
        );
    }
    @PutMapping("/editar-perfil")
    public ResponseEntity<MensajeDTO<String>> actualizarCliente(@Valid @RequestBody ActualizarClienteDTO actualizarClienteDTO)throws Exception{
        clienteServicio.actualizarCliente(actualizarClienteDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Cliente actualizado correctamente"));
    }
    @DeleteMapping("/eliminarCuentaCliente/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarCuenta(@PathVariable String codigo)throws
            Exception{
        clienteServicio.eliminarCuenta(codigo);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Cliente eliminado correctamente")
        );
    }
    @GetMapping("/obtener-Cliente/{codigo}")
    public ResponseEntity<MensajeDTO<DetalleClienteDTO>> obtenerCliente(@PathVariable String codigo) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                clienteServicio.obtenerCliente(codigo) ) );
    }
    @GetMapping("/filtrar-tipoCliente/{tipoCliente}")
    public ResponseEntity<MensajeDTO<List<ItemClienteDTO>>> filtrarClientesPorTipo(@PathVariable TIPO_CLIENTE tipoCliente) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clienteServicio.filtrarClientesPorTipo(tipoCliente))
        );
    }

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

    @DeleteMapping("/eliminarComentario/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarComentario(@PathVariable String codigo)throws Exception{
        comentarioServicio.eliminarComentario(codigo);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Comentario eliminado correctamente")
        );
    }
    @GetMapping("/obtener-Comentario/{codigo}")
    public ResponseEntity<MensajeDTO<DetalleComentarioDTO>> obtenerComentario(@PathVariable String codigo) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, comentarioServicio.obtenerComentario(codigo) ) );
    }

    @PostMapping("/responder-comentario")
    public ResponseEntity<MensajeDTO<String>> respoderComentario(@Valid @RequestBody ResponderComentarioDTO responderComentarioDTO)throws Exception{
        comentarioServicio.responderComentario(responderComentarioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Comentario Respondido")
        );
    }
    @PostMapping("/crear-pqrs")
    public ResponseEntity<MensajeDTO<String>> crearPqrs(@Valid @RequestBody CrearPqrsDTO crearPqrsDTO) throws Exception {
        pqrsServicio.crearPqrs(crearPqrsDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Pqrs creado correctamente")
        );
    }

    @PutMapping("/editar-pqrs")
    public ResponseEntity<MensajeDTO<String>> editarPqrs(@Valid @RequestBody EditarPqrsDTO editarPqrsDTO) throws Exception {
        pqrsServicio.editarPqrs(editarPqrsDTO);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Pqrs actualizado correctamente"));
    }

    @DeleteMapping("/eliminarPqrs/{codigo}")
    public ResponseEntity<MensajeDTO<String>> eliminarPqrs(@PathVariable String codigo) throws
            Exception {
        pqrsServicio.eliminarPqrs(codigo);
        return ResponseEntity.ok().body(new MensajeDTO<>(false, "Pqrs eliminado correctamente")
        );
    }

    @GetMapping("/obtener-Pqrs/{codigo}")
    public ResponseEntity<MensajeDTO<DetallePqrsDTO>> obtenerPqrs(@PathVariable String codigo) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false,
                pqrsServicio.obtenerPqrs(codigo)));
    }

    @GetMapping("/filtrar-tipoPqrs/{tipoPqrs}")
    public ResponseEntity<MensajeDTO<List<ItemPqrsDTO>>> filtrarPqrsPorTipo(@PathVariable TIPO_PQRS tipoPqrs) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, pqrsServicio.filtrarPqrsPorTipo(tipoPqrs))
        );
    }

    @PostMapping("/crear-negocio")
    public ResponseEntity<MensajeDTO<String>> crearNegocio(@Valid @RequestBody CrearNegocioDTO crearNegocioDTO)throws Exception{
        negocioServicio.crearNegocio(crearNegocioDTO);
        return ResponseEntity.ok().body( new MensajeDTO<>(false, "Negocio Creado Correctamente")
        );
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
}
