package co.edu.uniquindio.unilocalProyect.controllers;

import co.edu.uniquindio.unilocalProyect.dtos.*;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_CLIENTE;
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
    @GetMapping("/obtener/{codigo}")
    public ResponseEntity<MensajeDTO<DetalleClienteDTO>> obtenerCliente(@PathVariable String codigo) throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false,
                clienteServicio.obtenerCliente(codigo) ) );
    }
    @GetMapping("/filtrar-tipoCliente")
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

    @DeleteMapping("/eliminarPqrs/{codigo}")
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

    @GetMapping("/filtrar-tipoPqrs")
    public ResponseEntity<MensajeDTO<List<ItemPqrsDTO>>> filtrarPqrsPorTipo(@PathVariable TIPO_PQRS tipoPqrs) throws Exception {
        return ResponseEntity.ok().body(new MensajeDTO<>(false, pqrsServicio.filtrarPqrsPorTipo(tipoPqrs))
        );
    }

}
