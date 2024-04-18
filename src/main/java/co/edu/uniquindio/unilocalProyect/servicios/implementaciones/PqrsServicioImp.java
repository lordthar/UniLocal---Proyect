package co.edu.uniquindio.unilocalProyect.servicios.implementaciones;

import co.edu.uniquindio.unilocalProyect.dtos.DetallePqrsDTO;
import co.edu.uniquindio.unilocalProyect.dtos.EditarPqrsDTO;
import co.edu.uniquindio.unilocalProyect.dtos.PqrsActualizar;
import co.edu.uniquindio.unilocalProyect.dtos.PqrsDTO;
import co.edu.uniquindio.unilocalProyect.modelo.documentos.Pqrs;
import co.edu.uniquindio.unilocalProyect.modelo.enums.ESTADO_REGISTRO;
import co.edu.uniquindio.unilocalProyect.repositorios.PqrsRepo;
import co.edu.uniquindio.unilocalProyect.servicios.interfaces.PqrsServicio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PqrsServicioImp implements PqrsServicio {

    private final PqrsRepo pqrsRepo;

    @Override
    public String CrearPqrs(PqrsDTO pqrsDTO)throws Exception {
        Pqrs pqrs = new Pqrs();
        pqrs.setTitulo(pqrsDTO.titulo());
        pqrs.setDescripcion(pqrsDTO.descripcion());
        Pqrs pqrsCreado = pqrsRepo.save(pqrs);

        return pqrsCreado.getCodigo();
    }

    private Pqrs existeCodigo(String codigo)throws Exception{
        Optional<Pqrs> pqrsOptional = pqrsRepo.findByCodigo(codigo);
        if (pqrsOptional.isEmpty()){
            throw new Exception("No existe este codigo");
        }
        return pqrsOptional.get();
    }

    @Override
    public void actulizarPqrs(PqrsActualizar pqrsActualizar)throws Exception {
        Optional<Pqrs> pqrsOptional = pqrsRepo.findByCodigo(pqrsActualizar.codigo());

        if (pqrsOptional.isEmpty()){
            throw new Exception("No existe ningun Pqrs con el codigo " + pqrsActualizar.codigo());
        }
        Pqrs pqrs = pqrsOptional.get();
        pqrs.setTitulo(pqrsActualizar.titulo());
        pqrs.setDescripcion(pqrsActualizar.descripcion());

        pqrsRepo.save(pqrs);
    }

    public void eliminarPqrs (String codigo)throws Exception{
        Optional<Pqrs> optionalPqrs = pqrsRepo.findByCodigo(codigo);

        if (optionalPqrs.isEmpty()){
            throw new Exception("No existe");
        }
        Pqrs pqrs = optionalPqrs.get();
        pqrs.setEstadoPqrs(ESTADO_REGISTRO.INACTIVO);

        pqrsRepo.save(pqrs);
    }

    //ENVIAR

    public void editarPqrs (EditarPqrsDTO editarPqrsDTO) throws Exception{
        Optional<Pqrs> pqrsOpcional = pqrsRepo.findByCodigo(editarPqrsDTO.codigo());

        if (pqrsOpcional.isEmpty()){
            throw new Exception("No existe un pqrs con el codigo: " + editarPqrsDTO.codigo());
        }

        Pqrs pqrs = pqrsOpcional.get();
        pqrs.setTitulo(editarPqrsDTO.titulo());
        pqrs.setDescripcion(editarPqrsDTO.descripcion());
        pqrsRepo.save(pqrs);
    }

    public DetallePqrsDTO obtenerPqrs(String codigoPqrs)throws Exception{
        Optional<Pqrs> pqrsOptional = pqrsRepo.findByCodigo(codigoPqrs);

        if (pqrsOptional.isEmpty()){
            throw new Exception("No existe un pqrs con el codigo: " + codigoPqrs);
        }

        Pqrs pqrs = pqrsOptional.get();
        return new DetallePqrsDTO(pqrs.getCodigo(), pqrs.getTitulo(), pqrs.getDescripcion());
    }


}
