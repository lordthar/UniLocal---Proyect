package co.edu.uniquindio.unilocalProyect.servicios.implementaciones;

import co.edu.uniquindio.unilocalProyect.dtos.ActualizarNegocioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.CrearNegocioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.DetalleNegocioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.ItemNegocioDTO;
import co.edu.uniquindio.unilocalProyect.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Negocio;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_REGISTRO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.repositorios.NegocioRepo;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.NegocioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class NegocioServicioImp implements NegocioServicio {

    private final NegocioRepo negocioRepo;

    /**
     * Crea un negocio, dependiendo de si es premium o no permitira crear mas de un negocio
     * @param crearNegocioDTO
     * @return
     * @throws Exception
     */
    @Override
    public String crearNegocio(CrearNegocioDTO crearNegocioDTO) throws Exception {

        if (clienteTieneNegocio(crearNegocioDTO.codigoClient())) {
            throw new Exception("El cliente ya tiene un negocio");
        }

        Negocio negocio = new Negocio();
        negocio.setCodigo("N"+(negocioRepo.count()+1));
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

        Negocio negocioGuardado = negocioRepo.save(negocio);

        return negocioGuardado.getCodigo();
    }

    /**
     * Verifica si el cliente ya tiene un negocio
     * @param codigoClient
     * @return
     */
    private boolean clienteTieneNegocio(String codigoClient) {
        return negocioRepo.findByCodigoCliente(codigoClient).size() > 1;
    }

    /**
     * Busca un negocio en base al id del negocio
     * @param idNegocio
     * @return Devuelve el DetalleNegocioDTO del negocio si es encontrado
     * @throws Exception
     */
    @Override
    public DetalleNegocioDTO buscarNegocio(String idNegocio) throws Exception {

        if (idNegocio.isEmpty()) {
            throw new Exception("El id es requerido");
        }

        Optional<Negocio> optionalNegocio = negocioRepo.findByCodigoAndEstadoRegistroAndEstadoNegocio(idNegocio,
                ESTADO_REGISTRO.ACTIVO, ESTADO_NEGOCIO.APROBADO);

        if (optionalNegocio.isEmpty()) {
            throw new ResourceNotFoundException("Negocio no encontrado");
        }

        Negocio negocio = optionalNegocio.get();

        return new DetalleNegocioDTO(negocio.getNombre(), negocio.getDescripcion(), negocio.getCodigoCliente(),
                negocio.getImagenes(), negocio.getTelefonos(), negocio.getHorarios(), negocio.getTipoNegocio(),
                negocio.getCoordenada());
    }

    /**
     * Actualiza un negocio
     * @param actualizarNegocioDTO Datos nuevos para la actualizacion
     * @throws Exception
     */
    @Override
    public void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception {

        Optional<Negocio> optionalNegocio = negocioRepo.findByCodigoAndEstadoRegistro(actualizarNegocioDTO.idNegocio()
                , ESTADO_REGISTRO.ACTIVO);

        if (optionalNegocio.isEmpty()) {
            throw new ResourceNotFoundException("Negocio no encontrado");
        }

        Negocio negocio = optionalNegocio.get();
        negocio.setNombre(actualizarNegocioDTO.nombre());
        negocio.setDescripcion(actualizarNegocioDTO.descripcion());
        negocio.setImagenes(actualizarNegocioDTO.imagenes());
        negocio.setTelefonos(actualizarNegocioDTO.telefonos());
        negocio.setHorarios(actualizarNegocioDTO.horarios());
        negocio.setCoordenada(actualizarNegocioDTO.coordenada());
        negocio.setTipoNegocio(actualizarNegocioDTO.tipoNegocio());

        negocioRepo.save(negocio);
    }

    /**
     * Cambia el estado de un negocio a inactivo
     * @param idNegocio id por el que se va a buscar el negocio
     * @throws Exception
     */
    @Override
    public void eliminarNegocio(String idNegocio) throws Exception {
        Optional<Negocio> optionalNegocio = negocioRepo.findByCodigoAndEstadoRegistro(idNegocio, ESTADO_REGISTRO.ACTIVO);

        if (optionalNegocio.isEmpty()) {
            throw new ResourceNotFoundException("Negocio no encontrado");
        }

        Negocio negocio = optionalNegocio.get();
        negocio.setEstadoRegistro(ESTADO_REGISTRO.INACTIVO);

        negocioRepo.save(negocio);
    }

    /**
     * Filtra los negocios por su nombre
     * @param nombre nombre por el que se quiere filtrar
     * @return Devuelve una lista de ItemNegocioDTO
     * @throws Exception
     */
    @Override
    public List<ItemNegocioDTO> filtrarPorNombre(String nombre) throws Exception {
        if (nombre.isEmpty()) {
            throw new Exception("El nombre es requerido");
        }
        List<Negocio> negocios = negocioRepo.findByNombreContainingIgnoreCase(nombre);

        return getNegociosItemDTO(negocios);
    }

    /**
     * Filtra pot el tipo de negocio
     * @param tipoNegocio Tipo de negocio por el que se desea filtrar
     * @return Devuelve una lista de ItemNegocioDto
     * @throws Exception
     */
    @Override
    public List<ItemNegocioDTO> filtrarPorTipoNegocio(TIPO_NEGOCIO tipoNegocio) throws Exception {
        if (tipoNegocio == null) {
            throw new Exception("El campo tipo negocio es necesario");
        }
        List<Negocio> negocios = negocioRepo.findByTipoNegocio(tipoNegocio);

        return getNegociosItemDTO(negocios);
    }

    /**
     * Convierte una lista de negocios a una lista de ItemNegocioDTO
     * @param negocios Lista de negocios que se desea transformar
     * @return Devuelve una lista de ItemNegocioDTO
     */
    private List<ItemNegocioDTO> getNegociosItemDTO(List<Negocio> negocios) {
        List<ItemNegocioDTO> items = new ArrayList<>();

        for (Negocio negocio : negocios) {
            items.add(new ItemNegocioDTO(negocio.getNombre(), negocio.getDescripcion(), negocio.getCodigoCliente(),
                    negocio.getImagenes().get(0), negocio.getTipoNegocio(), negocio.getCoordenada()));
        }
        return items;
    }
}
