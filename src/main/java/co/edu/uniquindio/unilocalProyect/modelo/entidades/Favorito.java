package co.edu.uniquindio.unilocalProyect.modelo.entidades;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Favorito {
    private LocalDate fechaDeAgregado;
    private String codigoNegocio;
}
