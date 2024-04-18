package co.edu.uniquindio.unilocalProyect.dtos;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_PQRS;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record EditarPqrsDTO(

        @NotBlank String codigoPqrs,
        @NotBlank @Length(max= 50, min= 10) String titulo,
        @NotBlank @Length(max = 100, min= 50) String descripcion,
        @NotBlank TIPO_PQRS tipoPqrs

) {


}
