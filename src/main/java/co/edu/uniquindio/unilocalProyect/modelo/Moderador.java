package co.edu.uniquindio.unilocalProyect.modelo;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document("moderadores")
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
