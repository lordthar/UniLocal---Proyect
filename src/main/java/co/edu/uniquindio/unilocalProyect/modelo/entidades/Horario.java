package co.edu.uniquindio.unilocalProyect.modelo.entidades;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Horario {
    private String dia;
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;

}
