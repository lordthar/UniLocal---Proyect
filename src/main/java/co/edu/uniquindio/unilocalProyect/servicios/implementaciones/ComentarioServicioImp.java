package co.edu.uniquindio.unilocalProyect.servicios.implementaciones;

import co.edu.uniquindio.unilocalProyect.dtos.CrearComentarioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.DetalleClienteDTO;
import co.edu.uniquindio.unilocalProyect.dtos.DetalleComentarioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.EditarComentarioDTO;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Cliente;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Comentario;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_REGISTRO;
import co.edu.uniquindio.unilocalProyect.repositorios.ComentarioRepo;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ComentarioServicio;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        comentario.setRespuesta(crearComentarioDTO.respuesta());
        comentario.setCalificacion(crearComentarioDTO.calificacion());
        comentario.setFecha(crearComentarioDTO.fecha());
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
        comentario.setRespuesta(editarComentarioDTO.respuesta());
        comentario.setCalificacion(editarComentarioDTO.calificacion());
        comentario.setFecha(editarComentarioDTO.fecha());

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
        return new DetalleComentarioDTO(comentario.getCodigo(), comentario.getMensaje(), comentario.getRespuesta(),comentario.getCalificacion(),comentario.getFecha());
    }
}
