package co.edu.uniquindio.unilocalProyect.dtos;

import co.edu.uniquindio.unilocalProyect.modelo.entidades.Coordenada;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.Horario;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record CrearNegocioDTO(

        @NotBlank @Length(max = 50) String nombre,
        @NotBlank String descipcion,
        @NotBlank String codigoClient,
        @NotEmpty List<String> imagenes,
        List<String> telefonos,
        @NotEmpty List<Horario> horarios,
        @NotNull Coordenada coordenada,
        @NotNull TIPO_NEGOCIO tipoNegocio
        ) {
}
