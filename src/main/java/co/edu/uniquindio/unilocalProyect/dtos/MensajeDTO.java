package co.edu.uniquindio.unilocalProyect.dtos;

public record MensajeDTO<T>(
        boolean error,
        T respuesta
) {
}
