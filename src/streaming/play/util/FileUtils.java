package streaming.play.util;

import streaming.play.contenido.Contenido;
import streaming.play.contenido.Genero;
import streaming.play.excepcion.PeliculaExistenteException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static final String SEPARADOR = "|"; // Para escribir
    public static final String SPLIT_SEPARADOR = "\\|"; // Para leer
    public static final String FILE_PATH = "contenido.txt";

    public static void escribirContenido(Contenido contenido){
        String linea = String.join(SEPARADOR,
                contenido.getTitulo(),
                String.valueOf(contenido.getDuracion()),
                contenido.getGenero().name(),
                String.valueOf(contenido.getCalificacion()),
                contenido.getFechaEstreno().toString()
        );
        try{
            Files.writeString(Paths.get(FILE_PATH),
                linea + System.lineSeparator(),
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);
        }catch (IOException e){
            System.out.println("No se pudo guardar el contenido en el archivo.");}
        }

    public static List<Contenido> leerContenido() {
        List<Contenido> contenidoDesdeArchivo = new ArrayList<>();

        try {
            List<String> lineas = Files.readAllLines(Paths.get(FILE_PATH));

            lineas.forEach(linea -> {
                String[] partes = linea.split(SPLIT_SEPARADOR);
                if (partes.length == 5) {
                    String titulo = partes[0].trim();
                    int duracion = Integer.parseInt(partes[1].trim());
                    Genero genero = Genero.valueOf(partes[2].trim().toUpperCase());
                    double calificacion = partes[3].isBlank() ? 0 : Double.parseDouble(partes[3].trim());
                    LocalDate fecha = LocalDate.parse(partes[4].trim());

                    try {
                        Contenido contenido = new Contenido(titulo, duracion, genero, calificacion);
                        contenido.setFechaEstreno(fecha);
                        contenidoDesdeArchivo.add(contenido);
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
