package co.edu.uniquindio.unilocalProyect.modelo.entidades;

import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Coordenada {
    private Double lantencia;
    private Double longitud;
}
