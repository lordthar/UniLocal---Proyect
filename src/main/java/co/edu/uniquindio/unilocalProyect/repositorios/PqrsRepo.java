package co.edu.uniquindio.unilocalProyect.repositorios;

import co.edu.uniquindio.unilocalProyect.modelo.documentos.Pqrs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PqrsRepo extends MongoRepository<Pqrs, String> {

    Optional<Pqrs> findByCodigo(String codigo);
}
