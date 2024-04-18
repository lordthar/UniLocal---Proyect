package co.edu.uniquindio.unilocalProyect.modelo.documentos;

import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_REGISTRO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_PQRS;
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
    @Id
    @EqualsAndHashCode.Include
    private String codigoPqrs;

    private String titulo;
    private String descripcion;
    private String codigoCliente;
    private String codigoNegocio;
    private ESTADO_REGISTRO estadoPqrs;
    private TIPO_PQRS tipoPqrs;
}
