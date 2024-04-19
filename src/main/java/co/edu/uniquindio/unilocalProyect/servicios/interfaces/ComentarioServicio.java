package co.edu.uniquindio.unilocalProyect.servicios.interfaces;

import co.edu.uniquindio.unilocalProyect.dtos.*;

import java.time.LocalDateTime;
import java.util.List;

public interface ComentarioServicio {

    String crearComentario(CrearComentarioDTO comentarioDTO)throws Exception;

    void editarComentario(EditarComentarioDTO editarComentarioDTO)throws Exception;

    void eliminarComentario(String idComentario)throws Exception;

    DetalleComentarioDTO obtenerComentario(String idComentario)throws Exception;

    List<ItemComantarioDTO> listarComentarios();

    void responderComentario(ResponderComentarioDTO responderComentarioDTO) throws Exception;

    List<ItemComantarioDTO> filtrarComentarioPorFecha(LocalDateTime fechaComentario)throws Exception;

    List<ItemComantarioDTO> filtrarComentarioPorCliente(String codigoCliente)throws Exception;

    List<ItemComantarioDTO> filtrarComentarioPorCalificacion(int calificacion)throws Exception;


}
