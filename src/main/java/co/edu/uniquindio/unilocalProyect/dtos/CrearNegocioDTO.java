package co.edu.uniquindio.unilocalProyect.dtos;

import co.edu.uniquindio.unilocalProyect.modelo.entidades.Coordenada;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.Horario;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

public record CrearNegocioDTO(

        @NotBlank @Length(max = 50) String nombre,
        @NotBlank String descripcion,
        @NotBlank String codigoCliente,
        @NotNull Coordenada ubicacion,
        ArrayList<String> imagenes,
        @NotEmpty List<Horario> horarios,
        @NotNull TIPO_NEGOCIO tipoNegocio,
        List<String> telefonos
) {
}
