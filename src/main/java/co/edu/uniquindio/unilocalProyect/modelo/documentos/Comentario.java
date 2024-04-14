package co.edu.uniquindio.unilocalProyect.modelo.documentos;

import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_REGISTRO;
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
    @Id
    @EqualsAndHashCode.Include
    private String codigo;
    private int calificacion;
    private LocalDateTime fecha;
    private String codigoCliente;
    private String codigoNegocio;
    private String mensaje;
    private String respuesta;
    private ESTADO_REGISTRO estadoRegistro;
}
