package co.edu.uniquindio.unilocalProyect.servicios.implementaciones;

import co.edu.uniquindio.unilocalProyect.servicios.interfaces.ImagenesServicio;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Service
public class ImagenesServicioImp implements ImagenesServicio {

    private final Cloudinary cloudinary;

    public ImagenesServicioImp() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dis01om1b");
        config.put("api_key", "221352385395683");
        config.put("api_secret", "T2zabrA_FAeyURPzG6Iv-fUxTwI");
        cloudinary = new Cloudinary(config);
    }

    @Override
    public Map subirImagen(MultipartFile imagen) throws Exception {
        File file = convertir(imagen);
        return cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", "unilocal"));
    }

    @Override
    public Map eliminarImagen(String idImagen) throws Exception {
        return cloudinary.uploader().destroy(idImagen, ObjectUtils.emptyMap());
    }

    private File convertir(MultipartFile imagen) throws IOException {
        File file = File.createTempFile(imagen.getOriginalFilename(), null);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(imagen.getBytes());
        fos.close();
        return file;
    }
}
