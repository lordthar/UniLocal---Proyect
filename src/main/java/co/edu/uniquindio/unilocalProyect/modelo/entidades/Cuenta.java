package co.edu.uniquindio.unilocalProyect.modelo.entidades;

import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_REGISTRO;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Cuenta {
    private String nickname;
    private String password;
    private String email;
    private ESTADO_REGISTRO estadoRegistro;
}
