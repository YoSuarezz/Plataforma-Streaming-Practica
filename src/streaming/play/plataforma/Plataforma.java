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
        contenido.forEach(contenido -> System.out.println(contenido.getTitulo()));
    }

    public void eliminarContenido(Pelicula pelicula) {
        this.contenido.remove(pelicula);
    }

    public Pelicula buscarContenido(String nombre) {
        return contenido.stream()
                .filter(contenido -> contenido.getTitulo().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public List<Pelicula> buscarPorGenero(String genero) {
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equalsIgnoreCase(genero))
                .toList();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }

}
