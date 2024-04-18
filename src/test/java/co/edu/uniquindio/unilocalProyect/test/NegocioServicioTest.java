package co.edu.uniquindio.unilocalProyect.test;

import co.edu.uniquindio.unilocalProyect.dtos.ActualizarNegocioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.CrearNegocioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.DetalleNegocioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.ItemNegocioDTO;
import co.edu.uniquindio.unilocalProyect.exceptions.ResourceNotFoundException;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Negocio;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.Coordenada;
import co.edu.uniquindio.unilocalProyect.modelo.entidades.Horario;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_NEGOCIO;
import co.edu.uniquindio.unilocalProyect.repositorios.NegocioRepo;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.NegocioServicio;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class NegocioServicioTest {

    @Autowired
    private NegocioServicio negocioServicio;
    @Autowired
    private NegocioRepo negocioRepo;

    @Test
    public void crearNegocioTest() throws Exception {

        MultipartFile multipartFile = new MockMultipartFile("img", "Cafeteria",
                null, new FileInputStream("src/test/resources/cafeteria.jpg"));

        CrearNegocioDTO crearNegocioDTO = new CrearNegocioDTO(
                "El museum",
                "Historias divertidas",
                "Cliente4",
                List.of(multipartFile),
                List.of("Telefono1"),
                List.of(new Horario("Lunes", LocalTime.of(6, 15), LocalTime.of(15, 00))),
                new Coordenada(11.24, 22.223),
                TIPO_NEGOCIO.MUSEO
        );

        negocioServicio.crearNegocio(crearNegocioDTO);

        Negocio negocio = negocioRepo.findById("N6").get();

        assertEquals("El museum", negocio.getNombre());
    }

    @Test
    public void buscarNegocioTest() throws Exception {
        DetalleNegocioDTO negocio = negocioServicio.buscarNegocio("N02");

        assertEquals("El café de la esquina", negocio.nombre());
    }

    @Test
    public void actualizarNegocioTest() throws Exception {
        MultipartFile multipartFile = new MockMultipartFile("img", "Cafeteria",
                null, new FileInputStream("src/test/resources/cafeteria.jpg"));



        Negocio negocio = negocioRepo.findById("N01").get();
        negocio.setNombre("La panaderia de juan");

        negocioServicio.actualizarNegocio(new ActualizarNegocioDTO(
                negocio.getCodigo(),
                negocio.getNombre(),
                negocio.getDescripcion(),
                List.of(multipartFile),
                negocio.getTelefonos(),
                negocio.getHorarios(),
                negocio.getCoordenada(),
                negocio.getTipoNegocio()
        ));

        DetalleNegocioDTO negocioActualizado = negocioServicio.buscarNegocio("N01");

        assertEquals("La panaderia de juan", negocioActualizado.nombre());
    }

    @Test
    public void eliminarNegocioTest() throws Exception {
        negocioServicio.eliminarNegocio("N03");

        try {
            DetalleNegocioDTO negocio = negocioServicio.buscarNegocio("N03");
            fail("Eliminar negocio fallo");
        } catch (ResourceNotFoundException e) {
            assertEquals("Negocio no encontrado", e.getMessage());
        }
    }

    @Test
    public void filtrarPorNombreTest() throws Exception {
        List<ItemNegocioDTO> negocios = negocioServicio.filtrarPorNombre("Gimnasio");

        assertEquals(1, negocios.size());
    }

    @Test
    public void filtrarPorTipoNegocioTest() throws Exception {
        List<ItemNegocioDTO> negocios = negocioServicio.filtrarPorTipoNegocio(TIPO_NEGOCIO.CAFETERIA);

        assertEquals(1, negocios.size());
        assertEquals("El café de la esquina", negocios.get(0).nombre());
    }
}
