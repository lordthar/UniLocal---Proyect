package co.edu.uniquindio.unilocalProyect.servicios.interfaces;

import co.edu.uniquindio.unilocalProyect.dtos.PqrsActualizar;
import co.edu.uniquindio.unilocalProyect.dtos.PqrsDTO;

public interface PqrsServicio {

    String CrearPqrs(PqrsDTO pqrsDTO )throws Exception;

    void actulizarPqrs(PqrsActualizar pqrsActualizar)throws Exception;

}
