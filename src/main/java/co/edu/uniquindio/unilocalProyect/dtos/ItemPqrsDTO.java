package co.edu.uniquindio.unilocalProyect.dtos;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_PQRS;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record ItemPqrsDTO(
        String titulo,
        String descripcion,
        TIPO_PQRS tipoPqrs
) {
}
