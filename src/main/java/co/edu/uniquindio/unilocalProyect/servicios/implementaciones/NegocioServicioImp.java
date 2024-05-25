package co.edu.uniquindio.unilocalProyect.servicios.implementaciones;

import co.edu.uniquindio.unilocalProyect.dtos.*;
import co.edu.uniquindio.unilocalProyect.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Cliente;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Comentario;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Negocio;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.Favorito;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.Imagen;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.HistorialRevision;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_REGISTRO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_CLIENTE;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.repositorios.NegocioRepo;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ClienteServicio;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.EmailServicio;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.NegocioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.aot.generate.InMemoryGeneratedFiles;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDateTime;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class NegocioServicioImp implements NegocioServicio {

    private final NegocioRepo negocioRepo;
    private final ClienteServicio clienteServicio;
    private final EmailServicio emailServicio;

    @Override
    public List<String> listarEstadosNegocio() {
        return Arrays.stream(ESTADO_NEGOCIO.values()).map(estadoNegocio -> estadoNegocio.toString()).collect(Collectors.toList());
    }

    /**
     * Lista los negocios cuyo id se encuentre en la lista de Favoritos del cliente
     */
    @Override
    public List<ItemNegocioDTO> listarNegociosFavoritos(String codigoCliente) throws Exception {
        List<Favorito> favoritos = clienteServicio.listarFavoritos(codigoCliente);
        List<String> codigosNegocios = new ArrayList<>();

        favoritos.forEach(favorito -> codigosNegocios.add(favorito.getCodigoNegocio()));


        List<Negocio> negocios = new ArrayList<>();
        for (String codigo : codigosNegocios) {
            Optional<Negocio> optionalNegocio = negocioRepo.findById(codigo);
            if (optionalNegocio.isPresent()) {
                Negocio negocio = optionalNegocio.get();
                if (negocio.getEstadoNegocio() == ESTADO_NEGOCIO.APROBADO && negocio.getEstadoRegistro() == ESTADO_REGISTRO.ACTIVO) {
                    negocios.add(negocio);
                }
            }
        }
        return getNegociosItemDTO(negocios);
    }

    /**
     * Lista los tipos de negocios que hay en el enum TIPO_NEGOCIO
     */
    @Override
    public List<String> listarTiposNegocio() {
        return Arrays.stream(TIPO_NEGOCIO.values()).map(tipoNegocio -> tipoNegocio.toString()).collect(Collectors.toList());
    }

    /**
     * Lista los negocios que se encuentran activos y aprobados
     */
    @Override
    public List<ItemNegocioDTO> listarNegocios() throws Exception {
        List<Negocio> negocios = negocioRepo.findByEstadoNegocioAndEstadoRegistro(ESTADO_NEGOCIO.APROBADO, ESTADO_REGISTRO.ACTIVO);
        return getNegociosItemDTO(negocios);
    }

    /**
     * Crea un negocio, dependiendo de si es premium o no permitira crear mas de un negocio
     * @param crearNegocioDTO
     * @return
     * @throws Exception
     */
    @Override
    public String crearNegocio(CrearNegocioDTO crearNegocioDTO) throws Exception {

        DetalleClienteDTO cliente = clienteServicio.obtenerCliente(crearNegocioDTO.codigoCliente());
        int cantidadNegociosCliente = cantidadNegociosCliente(crearNegocioDTO.codigoCliente());

        if (cliente.tipoCliente() == TIPO_CLIENTE.NORMAL && cantidadNegociosCliente != 0) {
            throw new Exception("El cliente ya tiene un negocio");
        } else if (cliente.tipoCliente() == TIPO_CLIENTE.PREMIUM && cantidadNegociosCliente == 4) {
            throw new Exception("El cliente ya tiene 4 negocios");
        }

        Negocio negocio = new Negocio();
        negocio.setNombre(crearNegocioDTO.nombre());
        negocio.setDescripcion(crearNegocioDTO.descripcion());
        negocio.setCodigoCliente(crearNegocioDTO.codigoCliente());
        negocio.setImagenes(almacenarImagenes(crearNegocioDTO.imagenes()));
        negocio.setTelefonos(crearNegocioDTO.telefonos());
        negocio.setHorarios(crearNegocioDTO.horarios());
        negocio.setCoordenada(crearNegocioDTO.ubicacion());
        negocio.setEstadoNegocio(ESTADO_NEGOCIO.PENDIENTE);
        negocio.setTipoNegocio(crearNegocioDTO.tipoNegocio());
        negocio.setEstadoRegistro(ESTADO_REGISTRO.ACTIVO);

        Negocio negocioGuardado = negocioRepo.save(negocio);

        return negocioGuardado.getCodigo();
    }

    /**
     * Calcula la cantidad de negocios de un cliente
     */
    private int cantidadNegociosCliente(String codigoClient) {
        return negocioRepo.findByCodigoClienteAndEstadoRegistro(codigoClient, ESTADO_REGISTRO.ACTIVO).size();
    }

    private ArrayList<Imagen> almacenarImagenes(ArrayList<String> Imagen)throws Exception{
        ArrayList<Imagen> listaImagen = new ArrayList<>();

        for(String item: Imagen){
            Imagen imagen = new Imagen(null,item);
            listaImagen.add(imagen);
        }
        return listaImagen;
    }

    /**
     * Busca un negocio en base al id del negocio
     * @return Devuelve el DetalleNegocioDTO del negocio si es encontrado
     */
    @Override
    public DetalleNegocioDTO buscarNegocio(String idNegocio) throws Exception {

        if (idNegocio.isEmpty()) {
            throw new Exception("El id es requerido");
        }

        Optional<Negocio> optionalNegocio = negocioRepo.findById(idNegocio);

        if (optionalNegocio.isEmpty()) {
            throw new ResourceNotFoundException("Negocio no encontrado");
        }

        Negocio negocio = optionalNegocio.get();

        if (negocio.getEstadoNegocio() != ESTADO_NEGOCIO.APROBADO && negocio.getEstadoRegistro() == ESTADO_REGISTRO.INACTIVO) {
            throw new ResourceNotFoundException("Negocio no encontrado");
        }

        return new DetalleNegocioDTO(negocio.getCodigo(), negocio.getNombre(), negocio.getDescripcion(),
                negocio.getCodigoCliente(), negocio.getImagenes(), negocio.getTelefonos(), negocio.getHorarios(),
                negocio.getTipoNegocio(), negocio.getCoordenada(), negocio.getEstadoRegistro(), negocio.getEstadoNegocio(),
                negocio.getHistorialRevisiones());
    }

    @Override
    public List<String> recorrerUrl(List<Imagen> listaUrl) throws Exception{
        List<String> listarImagenes = new ArrayList<>();

        for(Imagen item: listaUrl){
            listarImagenes.add(item.getUrlImagen());
        }
        return listarImagenes;
    }

    /**
     * Actualiza un negocio
     * @param actualizarNegocioDTO Datos nuevos para la actualizacion
     */
    @Override
    public void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception {

        Optional<Negocio> optionalNegocio = negocioRepo.findById(actualizarNegocioDTO.idNegocio());

        if (optionalNegocio.isEmpty() || optionalNegocio.get().getEstadoRegistro() == ESTADO_REGISTRO.INACTIVO) {
            throw new ResourceNotFoundException("Negocio no encontrado");
        }

        Negocio negocio = optionalNegocio.get();
        negocio.setNombre(actualizarNegocioDTO.nombre());
        negocio.setDescripcion(actualizarNegocioDTO.descripcion());
        negocio.setImagenes(actualizarNegocioDTO.imagenes());
        negocio.setTelefonos(actualizarNegocioDTO.telefonos());
        negocio.setHorarios(actualizarNegocioDTO.horarios());
        negocio.setCoordenada(actualizarNegocioDTO.ubicacion());
        negocio.setTipoNegocio(actualizarNegocioDTO.tipoNegocio());

        negocioRepo.save(negocio);
    }

    /**
     * Cambia el estado de un negocio a inactivo
     * @param idNegocio id por el que se va a buscar el negocio
     */
    @Override
    public void eliminarNegocio(String idNegocio) throws Exception {
        Optional<Negocio> optionalNegocio = negocioRepo.findById(idNegocio);

        if (optionalNegocio.isEmpty() || optionalNegocio.get().getEstadoRegistro() == ESTADO_REGISTRO.INACTIVO) {
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
     */
    @Override
    public List<ItemNegocioDTO> filtrarPorNombre(String nombre) throws Exception {
        if (nombre.isEmpty()) {
            throw new Exception("El nombre es requerido");
        }
        List<Negocio> negocios = negocioRepo.findByNombreLikeIgnoreCaseAndEstadoRegistroAndEstadoNegocio(nombre,
                ESTADO_REGISTRO.ACTIVO, ESTADO_NEGOCIO.APROBADO);

        return getNegociosItemDTO(negocios);
    }

    /**
     * Filtra pot el tipo de negocio
     * @param tipoNegocio Tipo de negocio por el que se desea filtrar
     * @return Devuelve una lista de ItemNegocioDto
     */
    @Override
    public List<ItemNegocioDTO> filtrarPorTipoNegocio(TIPO_NEGOCIO tipoNegocio) throws Exception {
        if (tipoNegocio == null) {
            throw new Exception("El campo tipo negocio es necesario");
        }
        List<Negocio> negocios = negocioRepo.findByTipoNegocioAndEstadoRegistro(tipoNegocio, ESTADO_REGISTRO.ACTIVO);

        return getNegociosItemDTO(negocios);
    }

    /**
     * Devuelve los negocios que ha gestionado un moderador
     */
    @Override
    public List<ItemNegocioDTO> negociosEditadosPorModerador(String codigoModerador) throws Exception {

        List<Negocio> negocios = negocioRepo.findByHistorialRevisionesCodigoModerador(codigoModerador);

        return getNegociosItemDTO(negocios);
    }

    /**
     * Cambia el estado del negocio a APROBADO
     */
    @Override
    public void aprobarNegocio(AprobarNegocioDTO aprobarNegocioDTO) throws Exception {

        Optional<Negocio> optionalNegocio = negocioRepo.findById(aprobarNegocioDTO.codigoNegocio());

        if (optionalNegocio.isEmpty() || optionalNegocio.get().getEstadoRegistro() == ESTADO_REGISTRO.INACTIVO) {
            throw new ResourceNotFoundException("El negocio no existe");
        }

        Negocio negocio = optionalNegocio.get();

        if (negocio.getEstadoNegocio() == ESTADO_NEGOCIO.APROBADO) {
            throw new Exception("El negocio ya se encuentra aprobado");
        }

        HistorialRevision historialRevision = new HistorialRevision();
        historialRevision.setEstadoNegocio(ESTADO_NEGOCIO.APROBADO);
        historialRevision.setCodigoModerador(aprobarNegocioDTO.codigoModerador());
        historialRevision.setFecha(LocalDateTime.now());

        negocio.setEstadoNegocio(ESTADO_NEGOCIO.APROBADO);
        negocio.getHistorialRevisiones().add(historialRevision);

        negocioRepo.save(negocio);

        String emailCliente = clienteServicio.obtenerCliente(negocio.getCodigoCliente()).email();
        emailServicio.enviarCorreo(new EmailDTO("Estado de negocio Aprobado",
                "Su negocio fue aprobado exitosamente", emailCliente));
    }

    /**
     * Cambia el estado del negocio a RECHAZADO
     */
    @Override
    public void rechazarNegocio(RechazarNegocioDTO rechazarNegocioDTO) throws Exception {

        Optional<Negocio> optionalNegocio = negocioRepo.findById(rechazarNegocioDTO.idNegocio());

        if (optionalNegocio.isEmpty() || optionalNegocio.get().getEstadoRegistro() == ESTADO_REGISTRO.INACTIVO) {
            throw new ResourceNotFoundException("Negocio no encontrado");
        }

        Negocio negocio = optionalNegocio.get();

        if (negocio.getEstadoNegocio() == ESTADO_NEGOCIO.RECHAZADO) {
            throw new Exception("El negocio ya se encuentra rechazado");
        }

        HistorialRevision historialRevision = new HistorialRevision(
                rechazarNegocioDTO.idModerador(),
                LocalDateTime.now(),
                rechazarNegocioDTO.motivo(),
                ESTADO_NEGOCIO.RECHAZADO
        );
        negocio.setEstadoNegocio(ESTADO_NEGOCIO.RECHAZADO);
        negocio.getHistorialRevisiones().add(historialRevision);

        negocioRepo.save(negocio);

        String emailCliente = clienteServicio.obtenerCliente(negocio.getCodigoCliente()).email();
        emailServicio.enviarCorreo(new EmailDTO("Estado de negocio Rechazado",
                "Su negocio fue rechazado por la/s siguiente/s razón/es:\n\n" + rechazarNegocioDTO.motivo(),
                emailCliente));
    }

    /**
     * Filtra los negocios por el nombre del propietario
     * @return Devuelve un lista de ItemNegocioModeradorDTO
     */
    @Override
    public List<ItemNegocioDTO> buscarPorNombrePropietario(String nombrePersona) throws Exception {
        if (nombrePersona.isEmpty()) {
            throw new Exception("El nombre de la persona es requerido");
        }
        List<Negocio> negocios = negocioRepo.filtrarPorNombrePropietarioYEstadoRegistroActivo(nombrePersona);

        return getNegociosItemDTO(negocios);
    }

    /**
     * Filtra los negocios por el estado de negocio que es pasado por parametro
     * @return Devuelve un lista de ItemNegocioModeradorDTO
     */
    @Override
    public List<ItemNegocioDTO> filtrarPorEstadoNegocio(ESTADO_NEGOCIO estadoNegocio) throws Exception {
        if (estadoNegocio == null) {
            throw new Exception("El estado negocio es requerido");
        }
        List<Negocio> negocios = negocioRepo.findByEstadoNegocioAndEstadoRegistro(estadoNegocio, ESTADO_REGISTRO.ACTIVO);

        return getNegociosItemDTO(negocios);
    }

    /**
     * Convierte una lista de negocios a una lista de ItemNegocioDTO
     * @param negocios Lista de negocios que se desea transformar
     * @return Devuelve una lista de ItemNegocioDTO
     */
    private List<ItemNegocioDTO> getNegociosItemDTO(List<Negocio> negocios) throws Exception {
        List<ItemNegocioDTO> items = new ArrayList<>();

        for (Negocio negocio : negocios) {
            items.add(new ItemNegocioDTO(negocio.getCodigo(), negocio.getNombre(), negocio.getDescripcion(),
                    negocio.getCodigoCliente(), negocio.getImagenes(), negocio.getEstadoNegocio(),
                    negocio.getTipoNegocio(), negocio.getCoordenada()));
        }
        return items;
    }

    @Override
    public List<ItemNegocioDTO> listarNegocioPropietario(String idCliente) throws Exception {
        List<Negocio> negocioList = negocioRepo.findByCodigoClienteAndEstadoNegocioAndEstadoRegistro(idCliente,ESTADO_NEGOCIO.APROBADO, ESTADO_REGISTRO.ACTIVO);
        return getNegociosItemDTO(negocioList);
    }


}
