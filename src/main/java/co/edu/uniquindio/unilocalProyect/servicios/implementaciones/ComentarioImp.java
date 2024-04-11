package co.edu.uniquindio.unilocalProyect.servicios.implementaciones;

import co.edu.uniquindio.unilocalProyect.dtos.CrearComentarioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.EditarComentarioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.EliminarComentarioDTO;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Comentario;
import co.edu.uniquindio.unilocalProyect.repositorios.ComentarioRepo;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ComentarioServicio;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ComentarioImp implements ComentarioServicio {

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


    }

    @Override
    public void eliminarComentario(EliminarComentarioDTO eliminarComentarioDTO) throws Exception {

    }

    @Override
    public void buscarComentario() throws Exception {

    }
}
