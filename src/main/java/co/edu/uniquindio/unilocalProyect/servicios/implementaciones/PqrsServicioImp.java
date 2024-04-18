package co.edu.uniquindio.unilocalProyect.servicios.implementaciones;

import co.edu.uniquindio.unilocalProyect.dtos.*;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Cliente;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Negocio;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Pqrs;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_REGISTRO;
import co.edu.uniquindio.unilocalProyect.modelo.enums.TIPO_PQRS;
import co.edu.uniquindio.unilocalProyect.repositorios.PqrsRepo;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.PqrsServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PqrsServicioImp implements PqrsServicio {

    private final PqrsRepo pqrsRepo;

    @Override
    public String crearPqrs(CrearPqrsDTO crearPqrsDTO)throws Exception {
        Pqrs pqrs = new Pqrs();
        pqrs.setTitulo(crearPqrsDTO.titulo());
        pqrs.setDescripcion(crearPqrsDTO.descripcion());
        pqrs.setTipoPqrs(crearPqrsDTO.tipoPqrs());
        Pqrs pqrsCreado = pqrsRepo.save(pqrs);

        return pqrsCreado.getCodigoPqrs();
    }

    private Pqrs existeCodigo(String codigoPqrs)throws Exception{
        Optional<Pqrs> pqrsOptional = pqrsRepo.findByCodigoPqrs(codigoPqrs);
        if (pqrsOptional.isEmpty()){
            throw new Exception("No existe este codigo");
        }
        return pqrsOptional.get();
    }

    public void eliminarPqrs (String codigoPqrs)throws Exception{
        Optional<Pqrs> optionalPqrs = pqrsRepo.findByCodigoPqrs(codigoPqrs);
        if (optionalPqrs.isEmpty()){
            throw new Exception("No existe");
        }
        Pqrs pqrs = optionalPqrs.get();
        pqrs.setEstadoPqrs(ESTADO_REGISTRO.INACTIVO);

        pqrsRepo.save(pqrs);
    }

    public void editarPqrs (EditarPqrsDTO editarPqrsDTO) throws Exception{
        Optional<Pqrs> pqrsOpcional = pqrsRepo.findByCodigoPqrs(editarPqrsDTO.codigoPqrs());

        if (pqrsOpcional.isEmpty()){
            throw new Exception("No existe un pqrs con el codigo: " + editarPqrsDTO.codigoPqrs());
        }

        Pqrs pqrs = pqrsOpcional.get();
        pqrs.setTitulo(editarPqrsDTO.titulo());
        pqrs.setDescripcion(editarPqrsDTO.descripcion());
        pqrs.setTipoPqrs(editarPqrsDTO.tipoPqrs());
        pqrsRepo.save(pqrs);
    }

    public DetallePqrsDTO obtenerPqrs(String codigoPqrs)throws Exception{
        Optional<Pqrs> pqrsOptional = pqrsRepo.findByCodigoPqrs(codigoPqrs);

        if (pqrsOptional.isEmpty()){
            throw new Exception("No existe un pqrs con el codigo: " + codigoPqrs);
        }

        Pqrs pqrs = pqrsOptional.get();
        return new DetallePqrsDTO(pqrs.getCodigoPqrs(), pqrs.getTitulo(), pqrs.getDescripcion());
    }


    private List<ItemPqrsDTO> listarPqrs(List<Pqrs> pqrs) {

        List<ItemPqrsDTO> items = new ArrayList<>();

        for (Pqrs pqrs1 : pqrs){
            items.add(new ItemPqrsDTO(pqrs1.getTitulo(), pqrs1.getDescripcion(), pqrs1.getTipoPqrs()));
        }
        return items;
    }


    //ENVIAR
    public String enviarPqrs(EnviarPqrsDTO enviarPqrsDTO)throws Exception {
        Optional<Pqrs> pqrsOpcional = pqrsRepo.findByCodigoPqrs(enviarPqrsDTO.codigoPqrs());
        Optional<Cliente> optionalCliente = pqrsRepo.findByCodigoCliente(enviarPqrsDTO.codigoCliente());
        Optional<Negocio> optionalNegocio = pqrsRepo.findByCodigoNegocio(enviarPqrsDTO.codigoNegocio());
        String mensajeConfirmacion = " ";

        if (pqrsOpcional.isEmpty()) {
            throw new Exception("No existe un pqrs con el codigo: " + enviarPqrsDTO.codigoPqrs());
        } else if (optionalCliente.isEmpty()){
            throw new Exception("Este cliente no existe: " + enviarPqrsDTO.codigoCliente());
        } else if (optionalNegocio.isEmpty()){
            throw new Exception("Este negocio no existe: " + enviarPqrsDTO.codigoNegocio());
        }

        //Aqui obtengo el pqrs, el cliente y le añade a la lista de pqrses del negocio

        return mensajeConfirmacion = "La pqrs se ha enviado correctamente";
    }

    @Override
    public List<ItemPqrsDTO> filtrarPqrsPorTipo(TIPO_PQRS tipoPqrs) {
        List<Pqrs>pqrsList= pqrsRepo.findPqrsByTipoPqrs(tipoPqrs);
        return listarPqrs(pqrsList);
    }
}
