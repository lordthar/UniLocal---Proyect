package co.edu.uniquindio.unilocalProyect.servicios.interfaces;

import co.edu.uniquindio.unilocalProyect.dtos.DetalleNegocioModeradorDTO;
import co.edu.uniquindio.unilocalProyect.dtos.RechazarNegcioDTO;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Negocio;

import java.util.List;

public interface ModeradorServicio extends CuentaServicio {

    void aprobarNegocio(String idLugar);

    void rechazarNegocio(RechazarNegcioDTO rechazarNegcioDTO);

    List<DetalleNegocioModeradorDTO> filtarPorNombreNegocio();

    List<DetalleNegocioModeradorDTO> filtarPorNombrePersona();

    List<DetalleNegocioModeradorDTO> negociosPendientes();

    List<DetalleNegocioModeradorDTO> negociosDenegados(String idModerador);

    List<DetalleNegocioModeradorDTO> negociosAprobados(String idModerador);
}
