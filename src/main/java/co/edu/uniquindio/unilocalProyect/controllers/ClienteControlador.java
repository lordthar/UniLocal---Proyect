package co.edu.uniquindio.unilocalProyect.controllers;

import co.edu.uniquindio.unilocalProyect.dtos.*;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_CLIENTE;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ClienteServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteControlador {

    private final ClienteServicio clienteServicio;
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
    @DeleteMapping("/eliminar/{codigo}")
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
    @GetMapping("/filtrar-tipo")
    public ResponseEntity<MensajeDTO<List<ItemClienteDTO>>> filtrarClientesPorTipo(@PathVariable TIPO_CLIENTE tipoCliente) throws Exception {
        return ResponseEntity.ok().body( new MensajeDTO<>(false, clienteServicio.filtrarClientesPorTipo(tipoCliente))
        );
    }

}
