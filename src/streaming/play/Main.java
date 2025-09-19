package streaming.play;

import streaming.play.contenido.Pelicula;
import streaming.play.plataforma.Usuario;
import streaming.play.util.ScannerUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static final String NOMBRE_PLATAFORMA = "STREAMING PLAY 🍿";
    public static final String VERSION = "1.0.0";

    public static void main(String[] args) {
        System.out.println(NOMBRE_PLATAFORMA + " v" + VERSION);

        String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
        String genero = ScannerUtils.capturarTexto("Genero del contenido");
        int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
        double calificacion = ScannerUtils.capturarDecimal("Calificacion del contenido");

        Pelicula pelicula = new Pelicula(nombre, duracion, genero, calificacion);

        System.out.println(pelicula.obtenerFichaTecnica());

        Usuario usuario = new Usuario("Juan", "juan@platzi.com");
        usuario.ver(pelicula);
    }
}