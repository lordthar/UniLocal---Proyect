package co.edu.uniquindio.unilocalProyect.modelo.entidades;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Imagen {
    private String id;
    private String urlImagen;
}
