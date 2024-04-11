package co.edu.uniquindio.unilocalProyect.servicios.implementaciones;

import co.edu.uniquindio.unilocalProyect.dtos.ActualizarNegocioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.CrearNegocioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.DetalleNegocioDTO;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Negocio;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_REGISTRO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.repositorios.NegocioRepo;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.NegocioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class NegocioServicioImp implements NegocioServicio {

    private final NegocioRepo negocioRepo;

    @Override
    public void crearNegocio(CrearNegocioDTO crearNegocioDTO) throws Exception {

        if (clienteTieneNegocio(crearNegocioDTO.codigoClient())) {
            throw new Exception("El cliente ya tiene un negocio");
        }

        Negocio negocio = new Negocio();
        negocio.setNombre(crearNegocioDTO.nombre());
        negocio.setDescripcion(crearNegocioDTO.descipcion());
        negocio.setCodigoCliente(crearNegocioDTO.codigoClient());
        negocio.setImagenes(crearNegocioDTO.imagenes());
        negocio.setTelefonos(crearNegocioDTO.telefonos());
        negocio.setHorarios(crearNegocioDTO.horarios());
        negocio.setCoordenada(crearNegocioDTO.coordenada());
        negocio.setEstadoNegocio(ESTADO_NEGOCIO.PENDIENTE);
        negocio.setTipoNegocio(crearNegocioDTO.tipoNegocio());
        negocio.setEstadoRegistro(ESTADO_REGISTRO.ACTIVO);
    }

    private boolean clienteTieneNegocio(String codigoClient) {
        return negocioRepo.findByCodigoCliente(codigoClient).isPresent();
    }

    @Override
    public void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTo) throws Exception {

        Optional<Negocio> optionalNegocio = negocioRepo.findByCodigo(actualizarNegocioDTo.idNegocio());

        if (optionalNegocio.isEmpty()) {
            throw new Exception("El negocio no existe");
        }

        Negocio negocio = optionalNegocio.get();
        negocio.setNombre(actualizarNegocioDTo.nombre());
        negocio.setDescripcion(actualizarNegocioDTo.descripcion());
        negocio.setImagenes(actualizarNegocioDTo.imagenes());
        negocio.setTelefonos(actualizarNegocioDTo.telefonos());
        negocio.setHorarios(actualizarNegocioDTo.horarios());
        negocio.setCoordenada(actualizarNegocioDTo.coordenada());
        negocio.setTipoNegocio(actualizarNegocioDTo.tipoNegocio());
    }

    @Override
    public void eliminarNegocio(String idNegocio) throws Exception {
        Optional<Negocio> optionalNegocio = negocioRepo.findByCodigo(idNegocio);

        if (optionalNegocio.isEmpty()) {
            throw new Exception("El negocio no existe");
        }

        Negocio negocio = optionalNegocio.get();
        negocio.setEstadoRegistro(ESTADO_REGISTRO.INACTIVO);
    }

    @Override
    public List<DetalleNegocioDTO> filtrarPorNombre(String nombre) throws Exception {
        if (nombre.isEmpty()) {
            throw new Exception("El nombre es requerido");
        }
        return negocioRepo.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<DetalleNegocioDTO> filtrarPorTipoNegocio(TIPO_NEGOCIO tipoNegocio) throws Exception {
        if (tipoNegocio == null) {
            throw new Exception("El campo tipo negocio es necesario");
        }
        return negocioRepo.findByTipoNegocio(tipoNegocio);
    }
}
