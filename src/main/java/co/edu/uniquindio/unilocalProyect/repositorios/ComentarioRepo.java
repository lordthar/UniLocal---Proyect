package co.edu.uniquindio.unilocalProyect.repositorios;

import co.edu.uniquindio.unilocalProyect.modelo.documentos.Comentario;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ComentarioRepo extends MongoRepository<Comentario, String> {

    Optional<Comentario> findByCodigo(String codigo);

    Optional<Comentario>findComentarioByFechaComentario(LocalDateTime fechaComentario);


    //@Aggregation({"{$match:{codigonegocio: ?0}}", "{$lookup:{from:'negocios',localField: 'codigoNegocio', foreignField:'_id', as: 'negocio'}}", "{$unwind:'$negocio'}", "{$group: }", "{$proyect: }")
    Optional<Comentario>findComentarioByCalificacion(List<Integer> calificaciones);

    Optional<Comentario>findComentarioByCodigoCliente(String codigoCliente);

    Optional<Comentario>findComentarioByRespuesta(String respuesta);

}
