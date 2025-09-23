package streaming.play.plataforma;

import streaming.play.contenido.*;
import streaming.play.excepcion.PeliculaExistenteException;
import streaming.play.util.FileUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Plataforma {
    private String nombre;
    private List<Contenido> contenido;
    private Map<Contenido, Integer> visualizaciones;

    public Plataforma(String nombre) {
        this.nombre = nombre;
        this.contenido = new ArrayList<>();
        this.visualizaciones = new HashMap<>();
    }

    public void agregarContenido(Contenido pelicula) {
        Contenido contenido = this.buscarContenido(pelicula.getTitulo());

        if (contenido != null) {
            throw new PeliculaExistenteException(pelicula.getTitulo());
        }

        FileUtils.escribirContenido(pelicula);
        this.contenido.add(pelicula);
    }

    public void reproducir(Contenido contenido) {
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        System.out.println(contenido.getTitulo() + " ha sido reproducido " + conteoActual + " veces.");

        this.contarVisualizacion(contenido);
        contenido.reproducir();
    }

    private void contarVisualizacion(Contenido contenido) {
        int conteoActual = visualizaciones.getOrDefault(contenido, 0);
        visualizaciones.put(contenido, conteoActual + 1);
    }


    public List<String> getTitulos() {
        return contenido.stream()
                .map(Contenido::getTitulo)
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

    public void eliminarContenido(Contenido contenido) {
        this.contenido.remove(contenido);
    }

    public Contenido buscarContenido(String nombre) {
        return contenido.stream()
                .filter(contenido -> contenido.getTitulo().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public List<Contenido> buscarPorGenero(Genero genero) {
        return contenido.stream()
                .filter(contenido -> contenido.getGenero().equals(genero))
                .toList();
    }

    public int getDuracionTotal() {
        return contenido.stream()
                .mapToInt(Contenido::getDuracion)
                .sum();
    }

    public List<Contenido> getPeliculasPopulares() {
        return contenido.stream()
                .filter(Contenido::esPopular)
                .toList();
    }

    public List<Pelicula> getPeliculas() {
        return contenido.stream()
                .filter(contenido -> contenido instanceof Pelicula)
                .map(contenido -> (Pelicula) contenido)
                .toList();
    }

    public List<Promocionable> getContenidoPromocionable() {
        return contenido.stream()
                .filter(contenido -> contenido instanceof Promocionable)
                .map(contenido -> (Promocionable) contenido)
                .toList();
    }

    public List<Documental> getDocumentales() {
        return contenido.stream()
                .filter(contenido -> contenido instanceof Documental)
                .map(contenido -> (Documental) contenido)
                .toList();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Contenido> getContenido() {
        return contenido;
    }

}
