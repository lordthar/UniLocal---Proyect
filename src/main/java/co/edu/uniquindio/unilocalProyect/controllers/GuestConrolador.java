package co.edu.uniquindio.unilocalProyect.controllers;

import co.edu.uniquindio.unilocalProyect.dtos.DetalleNegocioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.ItemNegocioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.MensajeDTO;
import co.edu.uniquindio.unilocalProyect.dtos.RegistroClienteDTO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ComentarioServicio;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.NegocioServicio;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guests")
@RequiredArgsConstructor
public class GuestConrolador {
    private final NegocioServicio negocioServicio;
    private final ComentarioServicio comentarioServicio;

    @GetMapping("/guest-buscarNegocio")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> buscarNegocioPorNombre(@Valid @RequestBody String nombreNegocio)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.filtrarPorNombre(nombreNegocio))
        );
    }

    @GetMapping("/guest-buscarTipoNegocio")
    public ResponseEntity<MensajeDTO<List<ItemNegocioDTO>>> buscarNegocioPorTipo(@Valid @RequestBody TIPO_NEGOCIO tipoNegocio)throws Exception{
        return ResponseEntity.ok().body( new MensajeDTO<>(false, negocioServicio.filtrarPorTipoNegocio(tipoNegocio))
        );
    }




}
