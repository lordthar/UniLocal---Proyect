package co.edu.uniquindio.unilocalProyect.repositorios;

import co.edu.uniquindio.unilocalProyect.modelo.documentos.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepo extends MongoRepository<Cliente, String > {
    
}
