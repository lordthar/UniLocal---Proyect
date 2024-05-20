package co.edu.uniquindio.unilocalProyect.test;

import co.edu.uniquindio.unilocalProyect.dtos.ActualizarNegocioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.CrearNegocioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.DetalleNegocioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.ItemNegocioDTO;
import co.edu.uniquindio.unilocalProyect.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.Coordenada;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.Horario;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.NegocioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class NegocioServicioTest {

    @Autowired
    private NegocioServicio negocioServicio;

    @Test
    public void listarNegociosFavoritos() throws Exception {
        List<ItemNegocioDTO> negocios = negocioServicio.listarNegociosFavoritos("Cliente1");
        Assertions.assertEquals(1, negocios.size());
    }

    @Test
    public void listarTiposNegocio() {
        List<String > tiposNegocio = negocioServicio.listarTiposNegocio();
        Assertions.assertEquals(9, tiposNegocio.size());
    }

    @Test
    public void listarNegociosTest() {
        List<ItemNegocioDTO> negocios = negocioServicio.listarNegocios();
        Assertions.assertEquals(2, negocios.size());
    }

    @Test
    public void crearNegocioTest() throws Exception {

        CrearNegocioDTO crearNegocioDTO = new CrearNegocioDTO(
                "El museum",
                "Historias divertidas",
                "Cliente1",
                List.of("Foto1"),
                List.of("Telefono1"),
                List.of(new Horario("Lunes", LocalTime.of(6, 15), LocalTime.of(15, 0))),
                new Coordenada(11.24, 22.223),
                TIPO_NEGOCIO.MUSEO
        );

        String codigoNegocioCreado = negocioServicio.crearNegocio(crearNegocioDTO);

        Assertions.assertNotNull(codigoNegocioCreado);
    }

    @Test
    public void buscarNegocioTest() throws Exception {
        DetalleNegocioDTO negocio = negocioServicio.buscarNegocio("N02");

        assertEquals("El café de la esquina", negocio.nombre());
    }

    @Test
    public void actualizarNegocioTest() throws Exception {

        ActualizarNegocioDTO actualizarNegocioDTO = new ActualizarNegocioDTO(
                "N01",
                "La panaderia de Pedro",
                "Siempere encontraras pan calentito",
                new ArrayList<>(),
                new ArrayList<>(),
                List.of(new Horario("Lunes", LocalTime.of(6, 55), LocalTime.of(4, 30))),
                new Coordenada(11.0, 22.0),
                TIPO_NEGOCIO.BAR
        );

        negocioServicio.actualizarNegocio(actualizarNegocioDTO);

        DetalleNegocioDTO negocioActualizado = negocioServicio.buscarNegocio("N01");

        Assertions.assertEquals("La panaderia de Pedro", negocioActualizado.nombre());
    }

    @Test
    public void eliminarNegocioTest() throws Exception {

        negocioServicio.eliminarNegocio("N03");
        Assertions.assertThrows(ResourceNotFoundException.class, () -> negocioServicio.buscarNegocio("N03"));
    }

    @Test
    public void filtrarPorNombreTest() throws Exception {
        List<ItemNegocioDTO> negocios = negocioServicio.filtrarPorNombre("Gimnasio");

        Assertions.assertEquals(1, negocios.size());
    }

    @Test
    public void filtrarPorTipoNegocioTest() throws Exception {
        List<ItemNegocioDTO> negocios = negocioServicio.filtrarPorTipoNegocio(TIPO_NEGOCIO.CAFETERIA);

        Assertions.assertEquals(1, negocios.size());
        Assertions.assertEquals("El café de la esquina", negocios.get(0).nombre());
    }
}
