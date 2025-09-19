package streaming.play;

import streaming.play.contenido.Pelicula;
import streaming.play.plataforma.Plataforma;
import streaming.play.plataforma.Usuario;
import streaming.play.util.ScannerUtils;

public class Main {
    public static final String NOMBRE_PLATAFORMA = "STREAMING PLAY 🍿";
    public static final String VERSION = "1.0.0";

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + " v" + VERSION);

        String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
        String genero = ScannerUtils.capturarTexto("Genero del contenido");
        int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
        double calificacion = ScannerUtils.capturarDecimal("Calificacion del contenido");

        Pelicula pelicula = new Pelicula(nombre, duracion, genero, calificacion);
        Pelicula pelicula2 = new Pelicula("The Batman", 180, "Accion", 4.5);

        plataforma.agregarContenido(pelicula);
        plataforma.agregarContenido(pelicula2);
        System.out.println("Numero de elementos en la plataforma: " + plataforma.getContenido().size());
        plataforma.eliminarContenido(pelicula2);

        plataforma.mostrarContenido();

        Usuario usuario = new Usuario("Juan", "juan@platzi.com");
        usuario.ver(pelicula);
    }
}