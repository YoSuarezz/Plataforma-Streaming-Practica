package streaming.play.util;

import streaming.play.contenido.Genero;
import streaming.play.contenido.Pelicula;
import streaming.play.excepcion.PeliculaExistenteException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static final String SEPARADOR = "\\|";
    public static final String FILE_PATH = "contenido.txt";

    public static List<Pelicula> leerContenido() {
        List<Pelicula> contenidoDesdeArchivo = new ArrayList<>();

        try {
            List<String> lineas = Files.readAllLines(Paths.get(FILE_PATH));

            lineas.forEach(linea -> {
                String[] partes = linea.split(SEPARADOR);
                if (partes.length == 5) {
                    String titulo = partes[0].trim();
                    int duracion = Integer.parseInt(partes[1].trim());
                    Genero genero = Genero.valueOf(partes[2].trim().toUpperCase());
                    double calificacion = partes[3].isBlank() ? 0 : Double.parseDouble(partes[3].trim());
                    LocalDate fecha = LocalDate.parse(partes[4].trim());

                    try {
                        Pelicula pelicula = new Pelicula(titulo, duracion, genero, calificacion);
                        pelicula.setFechaEstreno(fecha);
                        contenidoDesdeArchivo.add(pelicula);
                    } catch (PeliculaExistenteException e) {
                        System.out.println(e.getMessage());
                    }
                }
            });
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo de contenido.");
        }
        return contenidoDesdeArchivo;
    }
}
