package co.edu.uniquindio.unilocalProyect.servicios.interfaces;

import co.edu.uniquindio.unilocalProyect.dtos.CambioPasswordDTO;
import co.edu.uniquindio.unilocalProyect.dtos.LoginCuentaDTO;
import co.edu.uniquindio.unilocalProyect.dtos.SesionCuentaDTO;

public interface CuentaServicio {
    void eliminarCuenta(String idCuenta)throws Exception;
    void enviarLinkRecuperacion(String email)throws Exception;
    void cambiarPassword(CambioPasswordDTO cambioPasswordDTO)throws Exception;

}
