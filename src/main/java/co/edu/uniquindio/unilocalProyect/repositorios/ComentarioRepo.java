package co.edu.uniquindio.unilocalProyect.repositorios;

import co.edu.uniquindio.unilocalProyect.modelo.documentos.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ComentarioRepo extends MongoRepository<Comentario, String> {

    Optional<Comentario> findByCodigo(String codigo);

    Optional<Comentario>findComentarioByFecha(LocalDateTime fecha);

    Optional<Comentario>findComentarioByCalificacion(String calificacion);
}
