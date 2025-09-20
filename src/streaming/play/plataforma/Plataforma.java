package streaming.play.plataforma;

import streaming.play.contenido.Genero;
import streaming.play.contenido.Pelicula;
import streaming.play.contenido.ResumenContenido;
import streaming.play.excepcion.PeliculaExistenteException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plataforma {
    private String nombre;
    private List<Pelicula> contenido;
    private Map<Pelicula, Integer> visualizaciones;

    public Plataforma(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<>();
        this.visualizaciones = new HashMap<>();
    }

    public void agregarContenido(Pelicula pelicula) {
        Pelicula contenido = this.buscarContenido(pelicula.getTitulo());

        if (contenido != null) {
            throw new PeliculaExistenteException(pelicula.getTitulo());
        }
        this.contenido.add(pelicula);
    }

    public void reproducir(Pelicula contenido) {
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        System.out.println(contenido.getTitulo() + " ha sido reproducido " + conteoActual + " veces.");

        this.contarVisualizacion(contenido);
        contenido.reproducir();
    }

    private void contarVisualizacion(Pelicula contenido) {
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        visualizaciones.put(contenido, conteoActual + 1);
    }


    public List<String> getTitulos() {
        return contenido.stream()
                .map(Pelicula::getTitulo)
                .toList();
    }

    public List<ResumenContenido> getResumenContenido() {
        return contenido.stream()
                .map(pelicula -> new ResumenContenido(
                        pelicula.getTitulo(),
                        pelicula.getDuracion(),
                        pelicula.getGenero()))
                .toList();
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

    public List<Pelicula> buscarPorGenero(Genero genero) {
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equals(genero))
                .toList();
    }

    public int getDuracionTotal() {
        return contenido.stream()
                .mapToInt(Pelicula::getDuracion)
                .sum();
    }

    public List<Pelicula> getPeliculasPopulares() {
        return contenido.stream()
                .filter(Pelicula::esPopular)
                .toList();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pelicula> getContenido() {
        return contenido;
    }

}
