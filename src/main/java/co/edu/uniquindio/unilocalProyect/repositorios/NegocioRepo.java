package co.edu.uniquindio.unilocalProyect.repositorios;

import co.edu.uniquindio.unilocalProyect.modelo.documentos.Negocio;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_REGISTRO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NegocioRepo extends MongoRepository<Negocio, String> {

    Optional<Negocio> findByCodigoAndEstadoNegocio(String codigoNegocio, ESTADO_NEGOCIO estadoNegocio);

    Optional<Negocio> findByCodigoAndEstadoRegistro(String codigoNegocio, ESTADO_REGISTRO estadoRegistro);

    Optional<Negocio> findByCodigoAndEstadoNegocio(String codigoNegocio, ESTADO_REGISTRO estadoRegistro);

    Optional<Negocio> findByCodigoAndEstadoRegistroAndEstadoNegocio(String codigoNegocio,
                                                                    ESTADO_REGISTRO estadoRegistro,
                                                                    ESTADO_NEGOCIO estadoNegocio);

    List<Negocio> findByCodigoCliente(String codigoPropietario);

    List<Negocio> findByEstadoNegocioAndEstadoRegistro(ESTADO_NEGOCIO estadoNegocio, ESTADO_REGISTRO estadoRegistro);

    List<Negocio> findByNombreContainingIgnoreCaseAndEstadoRegistro(String nombre, ESTADO_REGISTRO estadoRegistro);

    List<Negocio> findByTipoNegocio(TIPO_NEGOCIO tipoNegocio);

    List<Negocio> findByNombreAndHistorialRevisionesCodigoModerador(String nombreNegocio, String codigoModerador);

    List<Negocio> findByEstadoNegocioAndHistorialRevisionesCodigoModerador(ESTADO_NEGOCIO estadoNegocio, String historialRevisiones_codigoModerador);

    @Aggregation(pipeline = {
            "{$lookup: { from: 'clientes', localField: 'codigoCliente', foreignField: '_id', as: 'cliente' }}",
            "{$match: { 'cliente.nombre': { $regex: ?0, $options: i}, estadoRegistro: 'ACTIVO' } }" })
    List<Negocio> filtrarPorNombrePropietarioYEstadoRegistroActivo(String nombre);
}