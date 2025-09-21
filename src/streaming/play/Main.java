package streaming.play;

import streaming.play.contenido.*;
import streaming.play.excepcion.PeliculaExistenteException;
import streaming.play.plataforma.Plataforma;
import streaming.play.util.FileUtils;
import streaming.play.util.ScannerUtils;

import java.util.List;

public class Main {
    public static final String NOMBRE_PLATAFORMA = "STREAMING PLAY 🍿";
    public static final String VERSION = "1.0.0";
    public static final int AGREGAR = 1;
    public static final int MOSTRAR = 2;
    public static final int ELIMINAR = 3;
    public static final int BUSCAR = 4;
    public static final int BUSCAR_POR_GENERO = 5;
    public static final int VER_POPULARES = 6;
    public static final int REPRODUCIR = 7;
    public static final int SALIR = 8;

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + " v" + VERSION);

        cargarPeliculas(plataforma);

        System.out.println("Mas de " + plataforma.getDuracionTotal() + " minutos de contenido.");

        while (true) {
            int opcion = ScannerUtils.capturarNumero(
                    "\nSeleccione una opción:\n" +
                            "1. Agregar contenido\n" +
                            "2. Mostrar contenido\n" +
                            "3. Eliminar contenido\n" +
                            "4. Buscar contenido\n" +
                            "5. Buscar por género\n" +
                            "6. Ver populares\n" +
                            "7. Reproducir\n" +
                            "8. Salir\n" +
                            "Opción: "
            );

            switch (opcion) {
                case AGREGAR -> {
                    int tipoDeContenido = ScannerUtils.capturarNumero(
                            "Seleccione el tipo de contenido a agregar:\n" +
                                    "1. Película\n" +
                                    "2. Documental\n" +
                                    "Opción: "
                    );
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
                    Genero genero = ScannerUtils.capturarGenero("Genero del contenido");
                    int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
                    double calificacion = ScannerUtils.capturarDecimal("Calificacion del contenido");

                    try {
                        if (tipoDeContenido == 2) {
                            String narrador = ScannerUtils.capturarTexto("Nombre del narrador");
                            plataforma.agregarContenido(new Documental(nombre, duracion, genero, calificacion, narrador));
                        } else{
                            plataforma.agregarContenido(new Pelicula(nombre, duracion, genero, calificacion));
                        }
                    } catch (PeliculaExistenteException e) {
                        System.out.println(e.getMessage());
                    }
                }
                case MOSTRAR -> {
                    System.out.println("Contenido en " + plataforma.getNombre() + ":");
                    List<ResumenContenido> titulos = plataforma.getResumenContenido();
                    titulos.forEach(contenido -> System.out.println("- " + contenido.titulo() +
                            " (" + contenido.duracion() + " min) - " + contenido.genero()));
                    if (titulos.isEmpty()) {
                        System.out.println("No hay contenido disponible.");
                    }
                }
                case ELIMINAR -> {
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido a eliminar");
                    Contenido contenido = plataforma.buscarContenido(nombre);
                    if (contenido != null) {
                        plataforma.eliminarContenido(contenido);
                        System.out.println("Contenido eliminado: " + nombre);
                    } else {
                        System.out.println("Contenido no encontrado dentro de " + plataforma.getNombre());
                    }
                }
                case BUSCAR -> {
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido a buscar");
                    Contenido contenido = plataforma.buscarContenido(nombre);
                    if (contenido != null) {
                        System.out.println("Contenido encontrado:\n" + contenido.obtenerFichaTecnica());
                    } else {
                        System.out.println("Contenido no encontrado dentro de " + plataforma.getNombre());
                    }
                }
                case BUSCAR_POR_GENERO -> {
                    Genero genero = ScannerUtils.capturarGenero("Genero del contenido");
                    List<Contenido> contenidoPorGenero = plataforma.buscarPorGenero(genero);
                    if (!contenidoPorGenero.isEmpty()) {
                        System.out.println("Resultados encontrados:");
                        contenidoPorGenero.forEach(pelicula -> System.out.println("- " + pelicula.obtenerFichaTecnica()));
                    } else {
                        System.out.println("No se encontraron resultados para el género: " + genero);
                    }
                }
                case VER_POPULARES -> {
                    List<Contenido> populares = plataforma.getPeliculasPopulares();
                    if (!populares.isEmpty()) {
                        System.out.println("Películas populares:");
                        populares.forEach(pelicula -> System.out.println("- " + pelicula.obtenerFichaTecnica()));
                    } else {
                        System.out.println("No hay películas populares en este momento.");
                    }
                }
                case REPRODUCIR -> {
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido a reproducir");
                    Contenido contenido = plataforma.buscarContenido(nombre);

                    if (contenido != null) {
                        plataforma.reproducir(contenido);
                    } else {
                        System.out.println(nombre + " no existe.");
                    }
                }
                case SALIR -> System.exit(0);
            }
        }
    }

        private static void cargarPeliculas(Plataforma plataforma) {
            plataforma.getContenido().addAll(FileUtils.leerContenido());
    }
}