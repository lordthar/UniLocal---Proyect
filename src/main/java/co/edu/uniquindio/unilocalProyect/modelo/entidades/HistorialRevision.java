package co.edu.uniquindio.unilocalProyect.modelo.entidades;

import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_NEGOCIO;
import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HistorialRevision {
    private String codigoModerador;
    private LocalDateTime fecha;
    private String descripcion;
    private ESTADO_NEGOCIO estadoNegocio;
}
