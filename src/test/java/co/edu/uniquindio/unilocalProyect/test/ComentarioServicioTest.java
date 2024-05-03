package co.edu.uniquindio.unilocalProyect.test;

import  co.edu.uniquindio.unilocalProyect.dtos.CrearComentarioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.DetalleComentarioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.EditarComentarioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.ItemComantarioDTO;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Comentario;
import co.edu.uniquindio.unilocalProyect.repositorios.ComentarioRepo;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ComentarioServicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ComentarioServicioTest {
    @Autowired
    private ComentarioServicio comentarioServicio;
    @Autowired
    private ComentarioRepo comentarioRepo;

    @Test
    public void crearComentarioTest() throws Exception {
        CrearComentarioDTO crearComentarioDTO = new CrearComentarioDTO(
                "Cliente2",
                "N02",
                "El restaurante estuvo increible volvere",
                        5,
                LocalDateTime.of(2023,4, 23, 12, 34)
        );
        String cometario = comentarioServicio.crearComentario(crearComentarioDTO);
        Assertions.assertNotNull(cometario);
    }

    @Test
    public void editarCometarioTest() throws Exception {
        EditarComentarioDTO editarComentarioDTO = new EditarComentarioDTO(
                "C01",
                "Corrijo si estuvo bien el trato en el museo",
                4,
                LocalDateTime.of(2024,3,24,10,56)
        );
        comentarioServicio.editarComentario(editarComentarioDTO);

        Comentario comentario = comentarioRepo.findByCodigo("C01").get();
        Assertions.assertEquals("C01", comentario.getCodigo());
    }
    @Test
    public void buscarComentarioTest() throws Exception {
        DetalleComentarioDTO detalleComentarioDTO = comentarioServicio.obtenerComentario("C02");
        Assertions.assertEquals("Las pizzas son increíbles, definitivamente volveré.", detalleComentarioDTO.mensaje());
    }
    @Test
    public void eliminarComentarioTest() throws Exception {
        comentarioServicio.eliminarComentario("C01");
    }

    @Test
    public void filtrarComentarioPorFechaComentarioTest() throws Exception {
        List<ItemComantarioDTO> comantarios = comentarioServicio.filtrarComentarioPorFecha(LocalDate.of(2023, 9,17).atStartOfDay());
        Assertions.assertEquals(0,comantarios.size());
    }
    @Test
    public void filtrarComentarioPorCodigoClienteTest() throws Exception {
        List<ItemComantarioDTO> comentarios = comentarioServicio.filtrarComentarioPorCliente("Cliente1");
        Assertions.assertEquals(3,comentarios.size());
    }

    @Test
    public void filtrarComentarioPorCalificacion() throws Exception {
        List<ItemComantarioDTO> cometarioCalificacion = comentarioServicio.filtrarComentarioPorCalificacion(4);
        Assertions.assertEquals(3,cometarioCalificacion.size());
    }

}
