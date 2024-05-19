package co.edu.uniquindio.unilocalProyect.servicios.interfaces;

import co.edu.uniquindio.unilocalProyect.dtos.DetalleModeradorDTO;
import co.edu.uniquindio.unilocalProyect.exceptions.ResourceNotFoundException;

public interface ModeradorServicio extends CuentaServicio {

    DetalleModeradorDTO obtenerModerador(String codigoModerador) throws ResourceNotFoundException;
}
