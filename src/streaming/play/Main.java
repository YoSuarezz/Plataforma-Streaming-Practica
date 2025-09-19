package streaming.play;

import streaming.play.contenido.Pelicula;
import streaming.play.plataforma.Plataforma;
import streaming.play.plataforma.Usuario;
import streaming.play.util.ScannerUtils;

public class Main {
    public static final String NOMBRE_PLATAFORMA = "STREAMING PLAY 🍿";
    public static final String VERSION = "1.0.0";
    public static final int AGREGAR = 1;
    public static final int MOSTRAR = 2;
    public static final int ELIMINAR = 3;
    public static final int BUSCAR = 4;
    public static final int SALIR = 5;

    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma(NOMBRE_PLATAFORMA);
        System.out.println(NOMBRE_PLATAFORMA + " v" + VERSION);

        cargarPeliculas(plataforma);

        while (true) {
            int opcion = ScannerUtils.capturarNumero(
                    "\nSeleccione una opción:\n" +
                            "1. Agregar contenido\n" +
                            "2. Mostrar contenido\n" +
                            "3. Eliminar contenido\n" +
                            "4. Buscar contenido\n" +
                            "5. Salir\n" +
                            "Opción: "
            );
            System.out.println("Opcion seleccionada: " + opcion);

            switch (opcion) {
                case AGREGAR -> {
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido");
                    String genero = ScannerUtils.capturarTexto("Genero del contenido");
                    int duracion = ScannerUtils.capturarNumero("Duracion del contenido");
                    double calificacion = ScannerUtils.capturarDecimal("Calificacion del contenido");

                    plataforma.agregarContenido(new Pelicula(nombre, duracion, genero, calificacion));
                    System.out.println("Contenido agregado: " + nombre);
                }
                case MOSTRAR -> {
                    System.out.println("Contenido en " + plataforma.getNombre() + ":");
                    plataforma.mostrarContenido();
                }
                case ELIMINAR -> {
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido a eliminar");
                    Pelicula pelicula = plataforma.buscarContenido(nombre);
                    if (pelicula != null) {
                        plataforma.eliminarContenido(pelicula);
                        System.out.println("Contenido eliminado: " + nombre);
                    } else {
                        System.out.println("Contenido no encontrado dentro de " + plataforma.getNombre());
                    }
                }
                case BUSCAR -> {
                    String nombre = ScannerUtils.capturarTexto("Nombre del contenido a buscar");
                    Pelicula pelicula = plataforma.buscarContenido(nombre);
                    if (pelicula != null) {
                        System.out.println("Contenido encontrado:\n" + pelicula.obtenerFichaTecnica());
                    } else {
                        System.out.println("Contenido no encontrado dentro de" + plataforma.getNombre());
                    }
                }
                case SALIR -> System.exit(0);
            }
        }
    }

        private static void cargarPeliculas(Plataforma plataforma) {
            plataforma.agregarContenido(new Pelicula("Inception", 148, "Sci-Fi", 4.8));
            plataforma.agregarContenido(new Pelicula("The Dark Knight", 152, "Action", 4.9));
            plataforma.agregarContenido(new Pelicula("Interstellar", 169, "Sci-Fi", 4.7));
            plataforma.agregarContenido(new Pelicula("Parasite", 132, "Thriller", 4.6));
            plataforma.agregarContenido(new Pelicula("The Godfather", 175, "Crime", 4.9));
    }
}