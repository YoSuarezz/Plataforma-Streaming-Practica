package streaming.play.contenido;

public class Pelicula extends Contenido{
    public Pelicula(String titulo, int duracion, Genero genero, double calificacion) {
        super(titulo, duracion, genero, calificacion);
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo película: " + getTitulo());
    }

    @Override
    public String obtenerFichaTecnica() {
        return "Pelicula: " + getTitulo() + "\n" +
               "Duración: " + getDuracion() + " minutos\n" +
               "Género: " + getGenero() + "\n" +
               "Calificación: " + getCalificacion() + "\n" +
               "Fecha de Estreno: " + getFechaEstreno();
    }
}
