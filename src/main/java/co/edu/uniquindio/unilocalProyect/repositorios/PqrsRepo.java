package co.edu.uniquindio.unilocalProyect.repositorios;

import co.edu.uniquindio.unilocalProyect.dtos.ItemPqrsDTO;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Cliente;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Negocio;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Pqrs;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_PQRS;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PqrsRepo extends MongoRepository<Pqrs, String> {

    Optional<Pqrs> findByCodigoPqrs(String codigoPqrs);
    Optional<Cliente> findByCodigoCliente(String codigoCliente);
    Optional<Negocio> findByCodigoNegocio(String codigoNegocio);
    List<Pqrs> findPqrsByTipoPqrs(TIPO_PQRS tipoPqrs);

}
