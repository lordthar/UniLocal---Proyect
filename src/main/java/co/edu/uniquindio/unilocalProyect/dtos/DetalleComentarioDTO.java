package co.edu.uniquindio.unilocalProyect.dtos;

import java.time.LocalDateTime;

public record DetalleComentarioDTO(
        String codigo,
        String mensaje,
        String respuesta,
        int calificacion,
        LocalDateTime fecha
) {

}
