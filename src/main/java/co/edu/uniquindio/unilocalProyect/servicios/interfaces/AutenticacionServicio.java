package co.edu.uniquindio.unilocalProyect.servicios.interfaces;

import co.edu.uniquindio.unilocalProyect.dtos.LoginDTO;
import co.edu.uniquindio.unilocalProyect.dtos.TokenDTO;

public interface AutenticacionServicio {
    public TokenDTO iniciarSesionCliente(LoginDTO loginDTO) throws Exception;
    public TokenDTO iniciarSesionModerador(LoginDTO loginDTO) throws Exception;
}
