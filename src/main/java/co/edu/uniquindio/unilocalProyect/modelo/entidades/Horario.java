package co.edu.uniquindio.unilocalProyect.modelo.entidades;

import lombok.*;

import java.time.LocalTime;

@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Horario {
    private String dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;

}
