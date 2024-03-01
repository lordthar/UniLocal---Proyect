package co.edu.uniquindio.unilocalProyect.modelo;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

public class Cliente extends Cuenta implements Serializable {

    private String nickname;
    private String fotoPerfil;
    private String ciudad;
    private String codigo;

}
