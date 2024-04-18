package co.edu.uniquindio.unilocalProyect.servicios.interfaces;

import co.edu.uniquindio.unilocalProyect.dtos.*;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Pqrs;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_PQRS;

import java.util.List;

public interface PqrsServicio {

    String crearPqrs(CrearPqrsDTO crearPqrsDTO)throws Exception;
    void editarPqrs(EditarPqrsDTO editarPqrsDTO)throws Exception;
    void eliminarPqrs(String codigo)throws Exception;
    DetallePqrsDTO obtenerPqrs(String codigoPqrs)throws Exception;
    String enviarPqrs(EnviarPqrsDTO enviarPqrs)throws Exception;
    List<ItemPqrsDTO>filtrarPqrsPorTipo(TIPO_PQRS tipoPqrs);
}