package co.edu.uniquindio.unilocalProyect.repositorios;

import ch.qos.logback.core.net.server.Client;
import co.edu.uniquindio.unilocalProyect.dtos.ItemClienteDTO;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Cliente;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.Favorito;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_CLIENTE;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepo extends MongoRepository<Cliente, String > {

    Optional<Cliente> findByNickname(String nickname);
    Optional<Cliente> findClienteByEmail(String email);

    List<Cliente> findClienteByCodigosFavoritoIn(int codigoFavorito);
    List<Cliente> findClienteByTipoCliente(TIPO_CLIENTE tipoCliente);




}
