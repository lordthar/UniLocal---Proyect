package co.edu.uniquindio.unilocalProyect.servicios.implementaciones;

import co.edu.uniquindio.unilocalProyect.dtos.*;
import co.edu.uniquindio.unilocalProyect.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Cliente;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Comentario;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_REGISTRO;
import co.edu.uniquindio.unilocalProyect.repositorios.ComentarioRepo;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ComentarioServicio;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
@RequiredArgsConstructor
public class ComentarioServicioImp implements ComentarioServicio {

    private final ComentarioRepo comentarioRepo;
    @Override
    public String crearComentario(CrearComentarioDTO crearComentarioDTO) throws Exception {
        Comentario comentario = new Comentario();
        comentario.setMensaje(crearComentarioDTO.mensaje());
        comentario.setCalificacion(crearComentarioDTO.calificacion());
        comentario.setFechaComentario(crearComentarioDTO.fechaComentario());
        Comentario comentarioGuardado= comentarioRepo.save(comentario);

        return comentarioGuardado.getCodigo();
    }

    @Override
    public void editarComentario(EditarComentarioDTO editarComentarioDTO) throws Exception {
        Optional<Comentario> comentarioOptional = comentarioRepo.findById(editarComentarioDTO.codigo());

        if(comentarioOptional.isEmpty()){
            throw new Exception("No existe un cliente con el id : " + editarComentarioDTO.codigo());
        }
        Comentario comentario = comentarioOptional.get();
        comentario.setMensaje(editarComentarioDTO.mensaje());
        comentario.setCalificacion(editarComentarioDTO.calificacion());
        comentario.setFechaComentario(editarComentarioDTO.fechaComentario());

        comentarioRepo.save(comentario);
    }

    @Override
    public void eliminarComentario(String idComentario) throws Exception {
        Optional<Comentario> comentarioOptional = comentarioRepo.findById(idComentario);

        if(comentarioOptional.isEmpty()){
            throw new Exception("El comentario no fue encontrado");
        }

       Comentario comentario = comentarioOptional.get();
        comentario.setEstadoRegistro(ESTADO_REGISTRO.INACTIVO);

        comentarioRepo.save(comentario);
    }

    @Override
    public DetalleComentarioDTO obtenerComentario(String idComentario) throws Exception {
        Optional<Comentario> comentarioOptional = comentarioRepo.findById(idComentario);
        if (comentarioOptional.isEmpty()) {
            throw new Exception("No se encontró el cliente a con el id " + idComentario);
        }
        Comentario comentario = comentarioOptional.get();
        return new DetalleComentarioDTO(comentario.getCodigo(), comentario.getMensaje(), comentario.getRespuesta(),comentario.getCalificacion(),comentario.getFechaComentario());
    }

    @Override
    public List<ItemComantarioDTO> listarComentarios() {
        List<Comentario> comentarios = comentarioRepo.findAll();

        List<ItemComantarioDTO> items = new ArrayList<>();

        for (Comentario comentario: comentarios) {
            items.add(new ItemComantarioDTO(comentario.getMensaje(),comentario.getCalificacion(), comentario.getFechaComentario(),comentario.getRespuesta()));
        }
        return items;
    }

    private List<ItemComantarioDTO> getListarComentarios(List<Comentario> comentarios){
        List<ItemComantarioDTO> items = new ArrayList<>();

        for (Comentario comentario : comentarios) {
            items.add(new ItemComantarioDTO(comentario.getMensaje(),comentario.getCalificacion(), comentario.getFechaComentario(),comentario.getRespuesta()));
        }
        return items;
    }

    @Override
    public void responderComentario(ResponderComentarioDTO responderComentarioDTO) throws Exception {
        Optional<Comentario> comentarioOptional = comentarioRepo.findById(responderComentarioDTO.idComentario());
        if(comentarioOptional.isEmpty()){
            throw new ResourceNotFoundException("El comentario a responder no existe o fue eliminado");
        }
        Comentario comentario= comentarioOptional.get();
        comentario.setRespuesta(responderComentarioDTO.respuesta());
        comentario.setFechaRespuesta(responderComentarioDTO.fechaRespuesta());
    }

    @Override
    public List<ItemComantarioDTO> filtrarComentarioPorFecha(LocalDateTime fechaComentario) throws Exception {
        List<Comentario> comentarioList= comentarioRepo.findComentarioByFechaComentario(fechaComentario);
        return getListarComentarios(comentarioList);
    }

    @Override
    public List<ItemComantarioDTO> filtrarComentarioPorCliente(String codigoCliente) throws Exception {
        List<Comentario> comentarioList= comentarioRepo.findComentarioByCodigoCliente(codigoCliente);
        return getListarComentarios(comentarioList);
    }

    @Override
    public List<ItemComantarioDTO> filtrarComentarioPorCalificacion(int calificacion) throws Exception {
        List<Comentario> comentarioList= comentarioRepo.findComentarioByCalificacion(calificacion);
        return getListarComentarios(comentarioList);
    }


}
