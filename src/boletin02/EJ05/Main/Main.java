package boletin02.EJ05.Main;

import java.io.*;

public class Main {
    private static final String RUTA_ARCHIVO1 = "src/boletin02/EJ05/archivo1.txt";
    private static final String RUTA_ARCHIVO2 = "src/boletin02/EJ05/archivo2.txt";

    public static void main(String[] args) {
        try (
            BufferedReader br1 = new BufferedReader(new FileReader(RUTA_ARCHIVO1));
            BufferedReader br2 = new BufferedReader(new FileReader(RUTA_ARCHIVO2))
        ) {
            String linea1, linea2;
            int numLinea = 1;
            boolean sonIguales = true;

            while (true) {
                linea1 = br1.readLine();
                linea2 = br2.readLine();

                if (linea1 == null && linea2 == null) {
                    break;
                }

                if (linea1 == null || linea2 == null) {
                    sonIguales = false;
                    System.out.println("Los archivos son distintos.");
                    System.out.println("Primera diferencia en la línea: " + numLinea);
                    System.out.println("Uno de los archivos terminó antes que el otro.");
                    break;
                }

                if (!linea1.equals(linea2)) {
                    sonIguales = false;
                    int minLongitud = Math.min(linea1.length(), linea2.length());
                    int numCaracter = 1;

                    while (numCaracter <= minLongitud &&
                           linea1.charAt(numCaracter - 1) == linea2.charAt(numCaracter - 1)) {
                        numCaracter++;
                    }

                    System.out.println("Los archivos son distintos.");
                    System.out.println("Primera diferencia en la línea: " + numLinea);
                    System.out.println("Primera diferencia en el carácter: " + numCaracter);
                    break;
                }

                numLinea++;
            }

            if (sonIguales) {
                System.out.println("Los archivos son iguales.");
            }

        } catch (IOException e) {
            System.err.println("Error al procesar los archivos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}