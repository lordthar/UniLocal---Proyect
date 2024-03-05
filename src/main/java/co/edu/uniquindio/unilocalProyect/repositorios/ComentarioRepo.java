package co.edu.uniquindio.unilocalProyect.repositorios;

import co.edu.uniquindio.unilocalProyect.modelo.documentos.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepo extends MongoRepository<Comentario, String> {
}
