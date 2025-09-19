package streaming.play.excepcion;

public class PeliculaExistenteException extends RuntimeException {
    public PeliculaExistenteException(String titulo) {
        super("La película con título '" + titulo + "' ya existe en la plataforma.");
    }
}