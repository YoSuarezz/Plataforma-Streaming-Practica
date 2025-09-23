package streaming.play.util;

import streaming.play.contenido.Contenido;
import streaming.play.contenido.Documental;
import streaming.play.contenido.Genero;
import streaming.play.contenido.Pelicula;
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

        String lineaFinal;

        if(contenido instanceof Documental docu) {
            lineaFinal = "Documental" + SEPARADOR + linea + SEPARADOR + docu.getNarrador();
        } else {
            lineaFinal = "Pelicula" + SEPARADOR + linea;
        }

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

                String tipoContenido = partes[0];

            if (("PELICULA".equals(tipoContenido) && partes.length == 6) ||
                ("DOCUMENTAL".equals(tipoContenido) && partes.length == 7)) {
                    String titulo = partes[1];
                    int duracion = Integer.parseInt(partes[2]);
                    Genero genero = Genero.valueOf(partes[3].toUpperCase());
                    double calificacion = partes[4].isBlank() ? 0 : Double.parseDouble(partes[4]);
                    LocalDate fecha = LocalDate.parse(partes[5]);

                    try {
                        Contenido contenido;

                        if ("PELICULA".equals(tipoContenido)) {
                            contenido = new Pelicula(titulo, duracion, genero, calificacion);
                        } else {
                            String narrador = partes[6];
                            contenido = new Documental(titulo, duracion, genero, calificacion, narrador);
                        }

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
