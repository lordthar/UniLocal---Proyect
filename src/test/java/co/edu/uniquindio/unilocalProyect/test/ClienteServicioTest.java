package co.edu.uniquindio.unilocalProyect.test;

import co.edu.uniquindio.unilocalProyect.dtos.*;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Cliente;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_CLIENTE;
import co.edu.uniquindio.unilocalProyect.repositorios.ClienteRepo;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ClienteServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ClienteServicioTest {
    @Autowired
    private ClienteServicio clienteServicio;
    @Autowired
    private ClienteRepo clienteRepo;

    @Test
    public void registrarClienteTest() throws Exception {
        RegistroClienteDTO registroClienteDTO = new RegistroClienteDTO(
                "Miguel Angel",
                "Lantharusus",
                "FotoPerfil",
                "Armenia",
                "Gwy@email.com",
                "mipassword",
                new ArrayList<>()
        );
        String codigo = clienteServicio.registrarCliente(registroClienteDTO);
        Assertions.assertNotNull(codigo);
    }
    @Test
    public void actualizarClienteTest() throws Exception{
        ActualizarClienteDTO actualizarClienteDTO = new ActualizarClienteDTO(
                "Cliente1",
                "Juan",
                "etesech",
                "nueva foto",
                "Armenia",
                new ArrayList<>()
        );

        clienteServicio.actualizarCliente(actualizarClienteDTO);

        DetalleClienteDTO detalleClienteDTO = clienteServicio.obtenerCliente("Cliente1");
        assertEquals("nueva foto", detalleClienteDTO.fotoPerfil());
    }
    @Test
    public void pagarSubscripcionTest() throws Exception {
        clienteServicio.pagarSuscripcion("Cliente1");

        Cliente cliente = clienteRepo.findById("Cliente1").get();

        assertEquals(TIPO_CLIENTE.PREMIUM, cliente.getTipoCliente());
    }
    @Test
    public void anularSubscripciontest() throws Exception {
        clienteServicio.anularSubscripcion("Cliente1");

        Cliente cliente = clienteRepo.findById("Cliente1").get();

        assertEquals(TIPO_CLIENTE.NORMAL, cliente.getTipoCliente());

    }

    @Test
    public void subirFotoClienteTest() throws Exception {
        String filePath = "C:\\Users\\migue\\Desktop\\miAmigo.jpg";

        try (InputStream inputStream = new FileInputStream(new File(filePath))) {
            MockMultipartFile fotoPerfil = new MockMultipartFile("fotoPerfil", "imagen.jpg", "image/jpeg", inputStream);

            clienteServicio.subirFotoCliente(fotoPerfil);
        } catch (Exception e) {
            assertEquals("C:\\Users\\migue\\Desktop\\miAmigo.jpg (El sistema no puede encontrar la ruta especificada)", e.getMessage());
        }
    }

    @Test
    public void cambiarPasswordTest() throws Exception {

        Cliente cliente = clienteRepo.findById("Cliente1").get();
        clienteServicio.cambiarPassword(new CambioPasswordDTO(
                "newPassword",
                "mipassword",
                "Cliente1",
                null
        ));
        Cliente clienteActualizado = clienteRepo.findById("Cliente1").get();
        assertNotEquals(cliente.getPassword(), clienteActualizado.getPassword());
    }

    @Test
    public void eliminarFotoPerfilTest() throws Exception {
        clienteServicio.eliminarFotoPerfil("IM01");
    }
    @Test void enviarLinkRecuperacionTest() throws Exception {
        try {
            clienteServicio.enviarLinkRecuperacion("huendy.caicedot@uqvirtual.edu.co");
            fail("El test fallo");
        } catch (Exception e) {
            assertEquals("El email que ingreso no existe", e.getMessage());
        }
    }
    @Test
    public void eliminarCuentaTest() throws Exception{

        clienteServicio.eliminarCuenta("Cliente1");

        Assertions.assertThrows(Exception.class, () -> clienteServicio.obtenerCliente("Cliente1") );
    }

    @Test
    public void listarClienteTest(){
        List<Cliente> clientes = clienteRepo.findAll();
        assertEquals(6, clientes.size());
    }
    @Test
    public void filtrarPorTipoClienteTest() throws Exception {
        List<Cliente> cliente = clienteRepo.findClienteByTipoCliente(TIPO_CLIENTE.NORMAL);

        assertEquals(4, cliente.size());
        assertEquals(TIPO_CLIENTE.NORMAL, cliente.get(0).getTipoCliente());
    }
}
