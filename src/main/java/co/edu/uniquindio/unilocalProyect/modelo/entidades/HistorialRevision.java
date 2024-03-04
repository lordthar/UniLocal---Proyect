package co.edu.uniquindio.unilocalProyect.modelo.entidades;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class HistorialRevision {
    private LocalDateTime fecha;
    private String descripcion;
}
