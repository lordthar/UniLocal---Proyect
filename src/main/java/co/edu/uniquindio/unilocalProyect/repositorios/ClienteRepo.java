package co.edu.uniquindio.unilocalProyect.repositorios;

import ch.qos.logback.core.net.server.Client;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Cliente;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.Favorito;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_CLIENTE;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepo extends MongoRepository<Cliente, String > {
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByNickname(String nickname);
    List<Cliente> findClienteByCodigosFavorito(List<Favorito> codigosFavorito);
    Optional<Cliente> findClienteByTipoClienteAndCodigosFavorito(TIPO_CLIENTE tipoCliente, List<Favorito> codigosFavorito);
    Optional<Cliente> findClienteByTipoCliente(TIPO_CLIENTE tipoCliente);




}
