package co.edu.uniquindio.unilocalProyect.repositorios;

import co.edu.uniquindio.unilocalProyect.dtos.DetalleNegocioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.DetalleNegocioModeradorDTO;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Negocio;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NegocioRepo extends MongoRepository<Negocio, String> {

    @Query("{nombre: { $regex: ?0, $options: i}}")
    List<DetalleNegocioModeradorDTO> filtrarPorNombreNegociosModerador(String nombre);

    Optional<Negocio> findByCodigo(String codigoNegocio);

    Optional<Negocio> findByCodigoCliente(String codigoPropietario);

    List<DetalleNegocioModeradorDTO> findByEstadoNegocio(ESTADO_NEGOCIO estadoNegocio);

    List<DetalleNegocioDTO> findByNombreContainingIgnoreCase(String nombre);

    List<DetalleNegocioDTO> findByTipoNegocio(TIPO_NEGOCIO tipoNegocio);

    List<Negocio> findByNombreAndHistorialRevisionesCodigoModerador(String nombreNegocio, String codigoModerador);

    List<Negocio> findByEstadoNegocioAndHistorialRevisionesCodigoModerador(ESTADO_NEGOCIO estadoNegocio, String historialRevisiones_codigoModerador);

    @Aggregation(pipeline = {
            "{$lookup: { from: 'clientes', localField: 'codigoCliente', foreignField: '_id', as: 'cliente' }}",
            "{$match: { 'cliente.nombre': { $regex: ?0, $options: i} } }" })
    List<DetalleNegocioModeradorDTO> filtrarPorNombrePropietario(String nombre);
}
