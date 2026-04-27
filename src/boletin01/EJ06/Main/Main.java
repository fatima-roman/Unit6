package boletin01.EJ06.Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numeros = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("src/boletin01/EJ06/Enteros.txt"))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (!linea.isEmpty()) {
                    numeros.add(Integer.parseInt(linea));
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer el fichero de entrada: " + e.getMessage());
            return;
        }

        Collections.sort(numeros);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("src/boletin01/EJ06/EnterosOrdenados.txt"))) {
            for (int n : numeros) {
                bw.write(String.valueOf(n));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al escribir el fichero de salida: " + e.getMessage());
        }
    }
}