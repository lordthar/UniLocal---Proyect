package co.edu.uniquindio.unilocalProyect.dtos;

import co.edu.uniquindio.unilocalProyect.modelo.entidades.Coordenada;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.Horario;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CrearNegocioDTO(

        @NotBlank @Length(max = 50) String nombre,
        @NotBlank String descipcion,
        @NotBlank String codigoClient,
        @NotEmpty List<String> imagenes,
        List<String> telefonos,
        @NotEmpty List<Horario> horarios,
        @NotBlank Coordenada coordenada,
        @NotBlank TIPO_NEGOCIO tipoNegocio
        ) {
}
