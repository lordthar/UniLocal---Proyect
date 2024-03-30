package co.edu.uniquindio.unilocalProyect.modelo.documentos;


import co.edu.uniquindio.unilocalProyect.modelo.entidades.Coordenada;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.HistorialRevision;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.Horario;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_REGISTRO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("negocios")
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Negocio {
    @Id
    @EqualsAndHashCode.Include
    private String codigo;

    private String nombre;
    private String descripcion;
    private String codigoCliente;
    private List<String> imagenes;
    private List<String> telefonos;
    private List<Horario> horarios;
    private Coordenada coordenada;
    private ESTADO_NEGOCIO estadoNegocio;
    private String codigoClientePremium;
    private ESTADO_REGISTRO estadoRegistro;
    private List<HistorialRevision> historialRevisiones;
}
