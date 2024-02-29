package co.edu.uniquindio.unilocalProyect.modelo;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("negocios")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Negocio {
    private String nombre;
    private String descripcion;
    private String codigo;
}
