package streaming.play.plataforma;

import streaming.play.contenido.Contenido;

import java.time.LocalDateTime;

public class Usuario {

        private String nombre;
        private String email;
        private LocalDateTime fechaRegistro;

    public Usuario(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
        this.fechaRegistro = LocalDateTime.now();
    }

    public void ver(Contenido contenido) {
            System.out.println(nombre + " está viendo...");
            contenido.reproducir();
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }
}