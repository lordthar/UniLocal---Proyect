package co.edu.uniquindio.unilocalProyect.modelo.entidades;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Document("Pqrs")
public class Pqrs {
    private String titulo;
    private String descripcion;
    @Id
    @EqualsAndHashCode.Include
    private String codigo;
}
