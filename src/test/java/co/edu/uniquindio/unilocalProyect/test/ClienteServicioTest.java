package co.edu.uniquindio.unilocalProyect.test;

import co.edu.uniquindio.unilocalProyect.dtos.ActualizarClienteDTO;
import co.edu.uniquindio.unilocalProyect.dtos.DetalleClienteDTO;
import co.edu.uniquindio.unilocalProyect.dtos.ItemClienteDTO;
import co.edu.uniquindio.unilocalProyect.dtos.RegistroClienteDTO;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ClienteServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootTest
public class ClienteServicioTest {

    @Autowired
    private ClienteServicio clienteServicio;
    @Test
    public void registrarTest() throws Exception {
        RegistroClienteDTO registroClienteDTO = new RegistroClienteDTO(
                "Miguel Angel",
                "Lantharusus",
                "Mi foto",
                "Armenia",
                "Gwy@email.com",
                "mipassword"
        );
        String codigo = clienteServicio.registrarCliente(registroClienteDTO);
        Assertions.assertNotNull(codigo);
    }
    @Test
    public void actualizarTest() throws Exception{
        ActualizarClienteDTO actualizarClienteDTO = new ActualizarClienteDTO(
                "Cliente1",
                "Juan",
                "etesech",
                "nueva foto",
                "Armenia"
        );

        clienteServicio.actualizarCliente(actualizarClienteDTO);

        DetalleClienteDTO detalleClienteDTO = clienteServicio.obtenerCliente("Cliente1");
        Assertions.assertEquals("nueva foto", detalleClienteDTO.fotoPerfil());
    }
    @Test
    public void pagarSubscripcionTest() throws Exception {
        clienteServicio.pagarSuscripcion("Cliente1");
        Assertions.assertThrows(Exception.class, () -> clienteServicio.pagarSuscripcion("Cliente1") );
    }
    @Test
    public void anularSubscripciontest()throws ExecutionException{

    }
    @Test
    public void listarClientes() {
        List<ItemClienteDTO> lista = clienteServicio.listarClientes();
        Assertions.assertEquals(7, lista.size());
    }

    @Test void enviarLinkRecuperacionTest() throws Exception {
        clienteServicio.enviarLinkRecuperacion("miguelcreed77@gmail.com");
    }
    @Test
    public void eliminarCuentaTest() throws Exception{

        clienteServicio.eliminarCuenta("Cliente1");

        Assertions.assertThrows(Exception.class, () -> clienteServicio.obtenerCliente("Cliente1") );
    }


}
