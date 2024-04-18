package co.edu.uniquindio.unilocalProyect.dtos;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record DetallePqrsDTO(

        String codigo,
        String descripcion,
        String titulo
) {


}
