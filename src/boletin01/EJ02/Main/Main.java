package boletin01.EJ02.Main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int suma = 0;
        int contador = 0;

        try (Scanner sc = new Scanner(new File("src/boletin01/EJ02/Numeros/Numeros.txt" ))) {
            while (sc.hasNextInt()) {
                double numero = sc.nextInt();
                suma += numero;
                contador++;
            }

            if (contador > 0) {
                double media = suma / contador;
                System.out.println("Suma: " + suma);
                System.out.println("Media: " + media);
                System.out.println(contador);
            } else {
                System.out.println("El fichero no contiene números reales válidos.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el fichero NumerosReales.txt");
        }
    }
    /*
     * Cambio de ruta. EJ02 = EJ01 . + Cambios doble a int. 
     */
}