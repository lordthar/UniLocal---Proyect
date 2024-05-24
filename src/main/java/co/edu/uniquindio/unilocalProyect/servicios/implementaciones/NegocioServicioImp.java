package co.edu.uniquindio.unilocalProyect.servicios.implementaciones;

import co.edu.uniquindio.unilocalProyect.dtos.*;
import co.edu.uniquindio.unilocalProyect.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Cliente;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Comentario;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Negocio;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.Imagen;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_REGISTRO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_CLIENTE;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.repositorios.ClienteRepo;
import co.edu.uniquindio.unilocalProyect.repositorios.NegocioRepo;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ImagenesServicio;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.NegocioServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.aot.generate.InMemoryGeneratedFiles;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class NegocioServicioImp implements NegocioServicio {

    private final NegocioRepo negocioRepo;
    private final ClienteRepo clienteRepo;
    private final ImagenesServicio imagenesServicio;

    /**
     * Crea un negocio, dependiendo de si es premium o no permitira crear mas de un negocio
     * @param crearNegocioDTO
     * @return
     * @throws Exception
     */
    @Override
    public String crearNegocio(CrearNegocioDTO crearNegocioDTO) throws Exception {

        Optional<Cliente> optionalCliente = clienteRepo.findById(crearNegocioDTO.codigoCliente());

        if (optionalCliente.isEmpty()) {
            throw new ResourceNotFoundException("Cliente no encontrado");
        }

        Cliente cliente = optionalCliente.get();

        if (cliente.getTipoCliente() == TIPO_CLIENTE.NORMAL &&
                cantidadNegociosCliente(crearNegocioDTO.codigoCliente()) != 0) {
            throw new Exception("El cliente ya tiene un negocio");
        } else if (cliente.getTipoCliente() == TIPO_CLIENTE.PREMIUM &&
        cantidadNegociosCliente(crearNegocioDTO.codigoCliente()) == 4) {
            throw new Exception("El cliente ya tiene 4 negocios");
        }

        Negocio negocio = new Negocio();
        negocio.setCodigo("N"+(negocioRepo.count()+1));
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
     * Sube una lista de imagenes al servicio externo
     * @param imagenesRaw Lista de imagenes en crudo
     * @return Devuelve una lista de tipo Imagen, que contiene los datos de las imagenes subidas
     * @throws Exception
     */
    private List<Imagen> subirImagenes(List<MultipartFile> imagenesRaw) throws Exception {
        List<Imagen> imagenes = new ArrayList<>();

        for (MultipartFile imagen : imagenesRaw) {
            Map<String, String> map = imagenesServicio.subirImagen(imagen);
            imagenes.add(new Imagen(
                    map.get("public_id"),
                    map.get("secure_url"))
            );

        }
        return imagenes;
    }

    /**
     * Calcula la cantidad de negocios de un cliente
     * @param codigoClient
     * @return
     */
    private int cantidadNegociosCliente(String codigoClient) {
        return negocioRepo.findByCodigoCliente(codigoClient).size();
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

        return new DetalleNegocioDTO(negocio.getNombre(), negocio.getDescripcion(),negocio.getCodigoCliente(),negocio.getCodigo() ,recorrerUrl(negocio.getImagenes()),
                 negocio.getTelefonos(), negocio.getHorarios(), negocio.getTipoNegocio(),
                negocio.getCoordenada());
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
     * @throws Exception
     */
    @Override
    public void actualizarNegocio(ActualizarNegocioDTO actualizarNegocioDTO) throws Exception {

        Optional<Negocio> optionalNegocio = negocioRepo.findByCodigoAndEstadoRegistro(actualizarNegocioDTO.idNegocio()
                , ESTADO_REGISTRO.ACTIVO);

        if (optionalNegocio.isEmpty()) {
            throw new ResourceNotFoundException("Negocio no encontrado");
        }

//        List<Imagen> imagenes = subirImagenes(actualizarNegocioDTO.imagenes());

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
        List<Negocio> negocios = negocioRepo.findByNombreContainingIgnoreCaseAndEstadoRegistro(nombre,
                ESTADO_REGISTRO.ACTIVO);

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
    private List<ItemNegocioDTO> getNegociosItemDTO(List<Negocio> negocios) throws Exception {
        List<ItemNegocioDTO> items = new ArrayList<>();

        for (Negocio negocio : negocios) {
            items.add(new ItemNegocioDTO(negocio.getNombre(), negocio.getDescripcion(), negocio.getCodigoCliente(),negocio.getCodigo(),
                     recorrerUrl(negocio.getImagenes()),
                     negocio.getTipoNegocio(), negocio.getCoordenada()));
        }
        return items;
    }

    @Override
    public List<ItemNegocioDTO> listarNegocios() throws Exception {
        List<Negocio> negocios = negocioRepo.findByEstadoNegocioAndEstadoRegistro(ESTADO_NEGOCIO.APROBADO, ESTADO_REGISTRO.ACTIVO);
        return getNegociosItemDTO(negocios);
    }

    @Override
    public List<ItemNegocioDTO> listarNegocioPropietario(String idCliente) throws Exception {
        List<Negocio> negocioList = negocioRepo.findByCodigoClienteAndEstadoNegocioAndEstadoRegistro(idCliente,ESTADO_NEGOCIO.APROBADO, ESTADO_REGISTRO.ACTIVO);
        return getNegociosItemDTO(negocioList);
    }


}
