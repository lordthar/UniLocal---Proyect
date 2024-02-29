package co.edu.uniquindio.unilocalProyect.modelo;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Cuenta {
    private String nombre;
    private String password;
    private String email;
}
