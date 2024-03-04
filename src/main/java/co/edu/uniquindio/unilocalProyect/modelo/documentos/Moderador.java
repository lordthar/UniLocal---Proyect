package co.edu.uniquindio.unilocalProyect.modelo.documentos;

import co.edu.uniquindio.unilocalProyect.modelo.entidades.Cuenta;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Setter
@Getter
@AllArgsConstructor
public class Moderador extends Cuenta implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    private String codigo;
}
