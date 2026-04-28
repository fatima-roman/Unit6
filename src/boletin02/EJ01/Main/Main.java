package boletin02.EJ01.Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int numCaracteres = 0;
        int numPalabras = 0;
        int numLineas = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("src/boletin02/EJ01/carta.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                numLineas++;
                numCaracteres += linea.length();
                String[] palabras = linea.trim().split("\\s+");
                if (!linea.trim().isEmpty()) {
                    numPalabras += palabras.length;
                }
            }

            if (numLineas > 0) {
                System.out.println("Nº palabras: " + numPalabras);
                System.out.println("Nº lineas: " + numLineas);
                System.out.println("Nº Caracteres: " + numCaracteres);
            } else {
                System.out.println("El fichero está vacío.");
            }

        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        }
    }
}