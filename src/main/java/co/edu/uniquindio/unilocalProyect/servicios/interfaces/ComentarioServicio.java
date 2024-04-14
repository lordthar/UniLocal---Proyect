package co.edu.uniquindio.unilocalProyect.servicios.interfaces;

import co.edu.uniquindio.unilocalProyect.dtos.CrearComentarioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.DetalleComentarioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.EditarComentarioDTO;

public interface ComentarioServicio {

    String crearComentario(CrearComentarioDTO comentarioDTO)throws Exception;

    void editarComentario(EditarComentarioDTO editarComentarioDTO)throws Exception;

    void eliminarComentario(String idComentario)throws Exception;

    DetalleComentarioDTO obtenerComentario(String idComentario)throws Exception;



}
