package co.edu.uniquindio.unilocalProyect.dtos;

import jakarta.validation.constraints.NotBlank;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public record ItemComantarioDTO(
        String mensaje,
        int Calificacion,
        LocalDateTime fechaComentario
) {
}
