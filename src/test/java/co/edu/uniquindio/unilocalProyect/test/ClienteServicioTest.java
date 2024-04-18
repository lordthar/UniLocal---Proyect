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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

@SpringBootTest
public class ClienteServicioTest {

    @Autowired
    private ClienteServicio clienteServicio;
    @Test
    public void registrarClienteTest() throws Exception {
        RegistroClienteDTO registroClienteDTO = new RegistroClienteDTO(
                "Miguel Angel",
                "Lantharusus",
                new MockMultipartFile("fotoPerfil", "mi_imagen.jpg", "image/jpeg", "contenido_de_imagen".getBytes()),
                "IM01",
                "Armenia",
                "Gwy@email.com",
                "miPassword"
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
    public void subirFotoClienteTest() throws Exception {
        String filePath = "C:\\Users\\migue\\Desktop\\miAmigo.jpg";

        try (InputStream inputStream = new FileInputStream(new File(filePath))) {
            MockMultipartFile fotoPerfil = new MockMultipartFile("fotoPerfil", "imagen.jpg", "image/jpeg", inputStream);

            clienteServicio.subirFotoCliente(fotoPerfil);
        }
    }
    @Test
    public void eliminarFotoPerfilTest() throws Exception {
        clienteServicio.eliminarFotoPerfil("IM01");
    }
    @Test
    public void listarClientes() {
        List<ItemClienteDTO> lista = clienteServicio.listarClientes();
        Assertions.assertEquals(7, lista.size());
    }

    @Test void enviarLinkRecuperacionTest() throws Exception {
        clienteServicio.enviarLinkRecuperacion("huendy.caicedot@uqvirtual.edu.co");
    }
    @Test
    public void eliminarCuentaTest() throws Exception{

        clienteServicio.eliminarCuenta("Cliente1");

        Assertions.assertThrows(Exception.class, () -> clienteServicio.obtenerCliente("Cliente1") );
    }


}
