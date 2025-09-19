package streaming.play.util;

import streaming.play.contenido.Genero;

import java.util.Scanner;

public class ScannerUtils {
    public static final Scanner SCANNER = new Scanner(System.in);

    public static String capturarTexto(String mensaje) {
        System.out.println(mensaje + ": ");
        return SCANNER.nextLine();
    }

    public static int capturarNumero(String mensaje) {
        System.out.println(mensaje + ": ");

        while (!SCANNER.hasNextInt()) {
            System.out.println("Por favor ingresa un número válido.");
            SCANNER.next();
        }

        int dato = SCANNER.nextInt();
        SCANNER.nextLine();
        return dato;
    }

    public static double capturarDecimal(String mensaje) {
        System.out.println(mensaje + ": ");

        while (!SCANNER.hasNextDouble()) {
            System.out.println("Por favor ingresa un número válido.");
            SCANNER.next();
        }

        double dato = SCANNER.nextDouble();
        SCANNER.nextLine();
        return dato;
    }

    public static Genero capturarGenero(String mensaje) {
        while (true) {
            System.out.println(mensaje + " Opciones: ");
            for (Genero genero : Genero.values()) {
                System.out.println("- " + genero.name());
            }
            System.out.println("Ingresa el género: ");
            String input = SCANNER.nextLine();
            try {
                return Genero.valueOf(input.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Género inválido. Por favor ingresa un género válido.");
            }
        }
    }
}