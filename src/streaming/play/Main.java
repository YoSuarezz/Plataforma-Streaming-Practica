package streaming.play;

import streaming.play.contenido.Pelicula;
import streaming.play.plataforma.Usuario;
import streaming.play.util.ScannerUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("streaming PLAY 🍿");

        String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
        String genero = ScannerUtils.capturarTexto("Genero del contenido");
        int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
        double calificacion = ScannerUtils.capturarDecimal("Calificacion del contenido");

        Pelicula pelicula = new Pelicula();
        pelicula.titulo = nombre;
        pelicula.fechaEstreno = LocalDate.of(2018,10, 15);
        pelicula.genero = genero;
        pelicula.calificar(calificacion);
        pelicula.duracion = duracion;

        System.out.println(pelicula.obtenerFichaTecnica());

        Usuario usuario = new Usuario();
        usuario.nombre = "Juan";
        usuario.fechaRegistro = LocalDateTime.of(2025, 12, 24, 17, 15, 14);

        usuario.ver(pelicula);
    }
}