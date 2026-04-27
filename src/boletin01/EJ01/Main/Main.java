package boletin01.EJ01.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double suma = 0;
        int contador = 0;

        try (Scanner sc = new Scanner(new File("NumerosReales.txt"))) {
            while (sc.hasNextDouble()) {
                double numero = sc.nextDouble();
                suma += numero;
                contador++;
            }

            if (contador > 0) {
                double media = suma / contador;
                System.out.println("Suma: " + suma);
                System.out.println("Media: " + media);
            } else {
                System.out.println("El fichero no contiene números reales válidos.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el fichero NumerosReales.txt");
        }
    }
}