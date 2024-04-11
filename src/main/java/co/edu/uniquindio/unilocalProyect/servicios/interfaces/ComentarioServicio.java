package co.edu.uniquindio.unilocalProyect.servicios.interfaces;

import co.edu.uniquindio.unilocalProyect.dtos.CrearComentarioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.EditarComentarioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.EliminarComentarioDTO;

public interface ComentarioServicio {

    String crearComentario(CrearComentarioDTO comentarioDTO)throws Exception;

    void editarComentario(EditarComentarioDTO editarComentarioDTO)throws Exception;

    void eliminarComentario(EliminarComentarioDTO eliminarComentarioDTO)throws Exception;

    void buscarComentario()throws Exception;



}
