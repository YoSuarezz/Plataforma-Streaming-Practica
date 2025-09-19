package streaming.play;

import streaming.play.contenido.Pelicula;
import streaming.play.plataforma.Usuario;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("STREAMING PLAY");

        Pelicula pelicula = new Pelicula();
        pelicula.titulo = "El señor de los anillos";
        pelicula.fechaEstreno = LocalDate.of(2018,10, 15);
        pelicula.genero = "Fantasía";
        pelicula.calificar(4.7);

        pelicula.duracion = 120;

        long duracionLong = pelicula.duracion;
        int calificacionInt = (int) pelicula.calificacion;
        int numeroDePremios = (int) Long.parseLong("25000000000");

        System.out.println("Duracion Long: " + duracionLong);
        System.out.println("Calificacion Int: " + calificacionInt);
        System.out.println("Numero de premios: " + numeroDePremios);

        Usuario usuario = new Usuario();
        usuario.nombre = "Juan";
        usuario.fechaRegistro = LocalDateTime.of(2025, 12, 24, 17, 15, 14);

        System.out.println(usuario.fechaRegistro);

        usuario.ver(pelicula);

//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Cual es tu nombre?");
//        String nombre = scanner.nextLine();
//
//        System.out.println("Hola " + nombre + ", esto es Platzi Play!");
//
//        System.out.println(nombre + " cuantos años tienes?");
//        int edad = scanner.nextInt();
//
//        System.out.println(nombre + " puedes ver contenido +" + edad);
    }
}