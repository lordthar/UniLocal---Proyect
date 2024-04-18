package co.edu.uniquindio.unilocalProyect.test;

import co.edu.uniquindio.unilocalProyect.dtos.*;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Moderador;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Negocio;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_REGISTRO;
import co.edu.uniquindio.unilocalProyect.repositorios.ModeradorRepo;
import co.edu.uniquindio.unilocalProyect.repositorios.NegocioRepo;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ModeradorServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class ModeradorServicioTest {

    @Autowired
    private ModeradorServicio moderadorServicio;
    @Autowired
    private NegocioRepo negocioRepo;
    @Autowired
    private ModeradorRepo moderadorRepo;

    @Test
    public void eliminarCuentaTest() throws Exception {
        moderadorServicio.eliminarCuenta("M01");

        Moderador moderador = moderadorRepo.findById("M01").get();

        assertEquals(ESTADO_REGISTRO.INACTIVO, moderador.getEstadoRegistro());
    }

    @Test
    public void enviarLinkRecuperacionTest() throws Exception {
        moderadorServicio.enviarLinkRecuperacion("jakoja5649@etopys.com");
    }

    @Test
    public void cambiarPasswordTest() throws Exception {

        Moderador moderador = moderadorRepo.findById("M01").get();

        moderadorServicio.cambiarPassword(new CambioPasswordDTO(
                "newPassword",
                "mipassword",
                "M01",
                null
        ));

        Moderador moderadorActualizado = moderadorRepo.findById("M01").get();

        assertNotEquals(moderador.getPassword(), moderadorActualizado.getPassword());
    }

    @Test
    public void rechazarNegocioTest() throws Exception {

        moderadorServicio.rechazarNegocio(new RechazarNegocioDTO(
                "N04",
                "M04",
                "El negocio no es apropiado"
        ));


        Negocio negocio = negocioRepo.findById("N04").get();
        assertEquals(ESTADO_NEGOCIO.RECHAZADO, negocio.getEstadoNegocio());
    }

    @Test
    public void aprobarNegocioTest() throws Exception {
        moderadorServicio.aprobarNegocio(new AprobarNegocioDTO(
                "N04",
                "M04"
        ));

        Negocio negocio = negocioRepo.findById("N04").get();
        assertEquals(ESTADO_NEGOCIO.APROBADO, negocio.getEstadoNegocio());
    }

    @Test
    public void buscarNegocioPorIdTest() throws Exception {
        DetalleNegocioModeradorDTO negocio = moderadorServicio.buscarNegocioPorId("N05");

        assertEquals("N05", negocio.codigo());
    }

    @Test
    public void filtarPorNombreNegocioTest() throws Exception {
        List<ItemNegocioModeradorDTO> negocios = moderadorServicio.filtrarPorNombreNegocio("gimnasio");

        assertEquals(1, negocios.size());
    }

    @Test
    public void filtarNegociosPorNombrePropietarioTest() throws Exception {
        List<ItemNegocioModeradorDTO> negocios = moderadorServicio.filtrarNegociosPorNombrePropietario("juan");

        assertEquals(2, negocios.size());
    }

    @Test
    public void filtrarPorEstadoNegocioTest() throws Exception {
        List<ItemNegocioModeradorDTO> negocios = moderadorServicio.filtrarPorEstadoNegocio(ESTADO_NEGOCIO.APROBADO);

        assertEquals(4, negocios.size());
    }
}
