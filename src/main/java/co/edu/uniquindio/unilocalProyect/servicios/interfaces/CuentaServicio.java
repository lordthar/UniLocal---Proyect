package co.edu.uniquindio.unilocalProyect.servicios.interfaces;

import co.edu.uniquindio.unilocalProyect.dtos.CambioPasswordDTO;
import co.edu.uniquindio.unilocalProyect.dtos.LoginCuentaDTO;
import co.edu.uniquindio.unilocalProyect.dtos.SesionCuentaDTO;

public interface CuentaServicio {

    void loginCuenta(LoginCuentaDTO loginCuentaDTO)throws Exception;

    void sesionCuenta(SesionCuentaDTO sesionCuentaDTO)throws Exception;

    String cambioContraseña(CambioPasswordDTO cambioPasswordDTO)throws Exception;



}
