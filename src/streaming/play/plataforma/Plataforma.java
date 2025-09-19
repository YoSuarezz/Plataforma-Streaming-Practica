package streaming.play.plataforma;

import streaming.play.contenido.Pelicula;

import java.util.ArrayList;
import java.util.List;

public class Plataforma {
    private String nombre;
    private List<Pelicula> contenido;

    public Plataforma(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<>();
    }

    public void agregarContenido(Pelicula pelicula) {
        this.contenido.add(pelicula);
    }

    public void mostrarContenido() {
        for (Pelicula pelicula : contenido) {
            System.out.println(pelicula.getTitulo());
        }
    }

    public void eliminarContenido(Pelicula pelicula) {
        this.contenido.remove(pelicula);
    }

    public Pelicula buscarContenido(String nombre) {
        for (Pelicula pelicula : contenido) {
            if (pelicula.getTitulo().equalsIgnoreCase(nombre)) {
                return pelicula;
            }
        }
        return null;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }

}
