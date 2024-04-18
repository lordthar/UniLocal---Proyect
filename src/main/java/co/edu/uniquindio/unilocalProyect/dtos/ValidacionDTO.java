package co.edu.uniquindio.unilocalProyect.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public record ValidacionDTO(
        String campo,
        String error
) {
}
