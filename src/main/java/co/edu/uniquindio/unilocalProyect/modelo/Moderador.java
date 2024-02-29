package co.edu.uniquindio.unilocalProyect.modelo;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Setter
@Getter
@AllArgsConstructor
public class Moderador extends Cuenta implements Serializable {
    private String codigo;
}
