package co.edu.uniquindio.unilocalProyect.test;

import co.edu.uniquindio.unilocalProyect.dtos.CrearPqrsDTO;
import co.edu.uniquindio.unilocalProyect.dtos.DetallePqrsDTO;
import co.edu.uniquindio.unilocalProyect.dtos.EditarPqrsDTO;
import co.edu.uniquindio.unilocalProyect.dtos.ItemPqrsDTO;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Pqrs;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_PQRS;
import co.edu.uniquindio.unilocalProyect.repositorios.PqrsRepo;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.PqrsServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class PqrsServicioTest {
    @Autowired
    private PqrsServicio pqrsServicio;
    @Autowired
    private PqrsRepo pqrsRepo;

    @Test
    public void crearPqrsTest() throws Exception{
        CrearPqrsDTO crearPqrsDTO = new CrearPqrsDTO(
                "Cliente2",
                "N02",
                "comida quemada",
                "en el restaurante italo la comida servida estaba muy quemada",
                TIPO_PQRS.RECLAMO
        );
        String pqrs = pqrsServicio.crearPqrs(crearPqrsDTO);
        Assertions.assertNotNull(pqrs);
    }

    @Test
    public void editarPqrsTest() throws Exception{
        EditarPqrsDTO editarPqrsDTO = new EditarPqrsDTO(
                "P05",
                "Comida fresca",
                "En el restaurante sirven comida muy rica y fresca siempre",
                TIPO_PQRS.SUGERENCIA
        );
        pqrsServicio.editarPqrs(editarPqrsDTO);

        Pqrs pqrs = pqrsRepo.findByCodigoPqrs("P05").get();
        Assertions.assertEquals("P05", pqrs.getCodigoPqrs());
    }

    @Test
    public void buscarPqrsTest() throws Exception{
        DetallePqrsDTO detallePqrsDTO = pqrsServicio.obtenerPqrs("P05");
        Assertions.assertEquals("Comida fresca", detallePqrsDTO.titulo());
    }

    @Test
    public void eliminarPqrsTest() throws Exception{
        pqrsServicio.eliminarPqrs("P05");
    }

    @Test
    public void filtrarPorTipoPqrsTest() throws Exception{
        List<ItemPqrsDTO> pqrses = pqrsServicio.filtrarPqrsPorTipo(TIPO_PQRS.SUGERENCIA);
    }
}
