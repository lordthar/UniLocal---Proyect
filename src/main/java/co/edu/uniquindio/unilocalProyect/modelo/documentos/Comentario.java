package co.edu.uniquindio.unilocalProyect.modelo.documentos;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("comentarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Comentario {
    private int calificacion;
    private LocalDateTime fecha;
    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private String mensaje;
    private String respuesta;
}
