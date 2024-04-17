package co.edu.uniquindio.unilocalProyect.dtos;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EditarPqrsDTO(

        @NotBlank String codigo,
        @NotBlank @Length(max = 100, min= 50) String descripcion,
        @NotBlank @Length(max= 50, min= 10) String titulo
) {


}
