package co.edu.uniquindio.unilocalProyect.test;

import co.edu.uniquindio.unilocalProyect.dtos.CrearComentarioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.DetalleComentarioDTO;
import co.edu.uniquindio.unilocalProyect.dtos.EditarComentarioDTO;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Comentario;
import co.edu.uniquindio.unilocalProyect.repositorios.ComentarioRepo;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ComentarioServicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

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
        DetalleComentarioDTO detalleComentarioDTO = comentarioServicio.obtenerComentario("C01");
        Assertions.assertEquals("Corrijo si estuvo bien el trato en el museo", detalleComentarioDTO.mensaje());
    }
    @Test
    public void eliminarComentarioTest() throws Exception {
        comentarioServicio.eliminarComentario("C01");
    }

}
