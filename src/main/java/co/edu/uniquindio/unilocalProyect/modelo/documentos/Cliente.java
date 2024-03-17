package co.edu.uniquindio.unilocalProyect.modelo.documentos;

import co.edu.uniquindio.unilocalProyect.modelo.entidades.Cuenta;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_REGISTRO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document("clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

public class Cliente extends Cuenta implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private String nickname;
    private String fotoPerfil;
    private String ciudadResidencia;
    private String password;
    private String usuario;
    private List<String> telefonos;
    private ESTADO_REGISTRO estadoRegistro;


}
