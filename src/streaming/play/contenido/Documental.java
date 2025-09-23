package streaming.play.contenido;

public class Documental extends Contenido {
    private String narrador;

    public Documental(String titulo, int duracion, Genero genero, double calificacion, String narrador) {
        super(titulo, duracion, genero, calificacion);
        this.narrador = narrador;
    }

    public Documental(String titulo, int duracion, Genero genero) {
        super(titulo, duracion, genero);
    }

    @Override
    public void reproducir() {
        System.out.println("Reproduciendo documental: " + getTitulo() + ", narrado por " + getNarrador());
    }

    @Override
    public String obtenerFichaTecnica() {
        return "Documental: " + getTitulo() + "\n" +
               "Duración: " + getDuracion() + " minutos\n" +
               "Género: " + getGenero() + "\n" +
               "Calificación: " + getCalificacion() + "\n" +
               "Fecha de Estreno: " + getFechaEstreno() + "\n" +
               "Narrador: " + getNarrador();
    }

    public String getNarrador() {
        return narrador;
    }
}
