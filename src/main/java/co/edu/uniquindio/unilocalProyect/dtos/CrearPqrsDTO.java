package co.edu.uniquindio.unilocalProyect.dtos;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_PQRS;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CrearPqrsDTO(

        @NotBlank String codigoCliente,
        @NotBlank String codigoNegocio,
        @NotBlank @Length(max= 50, min= 10) String titulo,
        @NotBlank @Length(max = 100, min= 50) String descripcion,
        @NotNull TIPO_PQRS tipoPqrs
        ) {
}
