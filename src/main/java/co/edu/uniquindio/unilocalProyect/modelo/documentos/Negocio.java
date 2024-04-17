package co.edu.uniquindio.unilocalProyect.modelo.documentos;


import co.edu.uniquindio.unilocalProyect.modelo.entidades.Coordenada;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.HistorialRevision;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.Horario;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_REGISTRO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

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
    private List<Map> imagenes;
    private List<String> telefonos;
    private List<Horario> horarios;
    private Coordenada coordenada;
    private ESTADO_NEGOCIO estadoNegocio;
    private TIPO_NEGOCIO tipoNegocio;
    private ESTADO_REGISTRO estadoRegistro;
    private List<HistorialRevision> historialRevisiones;
}
