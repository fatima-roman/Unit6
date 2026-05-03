package boletin02.EJ04.Main;

import java.io.*;
import java.util.*;

public class Main {
    private static final String RUTA_ENTRADA = "src/boletin02/EJ04/mensaje.txt";
    private static final String RUTA_CODEC = "src/boletin02/EJ04/codec.txt";
    private static final String RUTA_SALIDA = "src/boletin02/EJ04/mensaje_cifrado.txt";

    public static void main(String[] args) {
        Map<Character, Character> mapaCifrado = new HashMap<>();

        try (
            BufferedReader brCodec = new BufferedReader(new FileReader(RUTA_CODEC));
            BufferedReader brTexto = new BufferedReader(new FileReader(RUTA_ENTRADA));
            BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_SALIDA))
        ) {
            String alfabeto = brCodec.readLine();
            String cifrado = brCodec.readLine();

            if (alfabeto == null || cifrado == null) {
                throw new IOException("El fichero codec.txt no tiene el formato correcto.");
            }

            alfabeto = alfabeto.replace(" ", "").toLowerCase();
            cifrado = cifrado.replace(" ", "").toLowerCase();

            if (alfabeto.length() != cifrado.length()) {
                throw new IOException("El alfabeto y el cifrado no tienen la misma longitud.");
            }

            for (int i = 0; i < alfabeto.length(); i++) {
                mapaCifrado.put(alfabeto.charAt(i), cifrado.charAt(i));
            }

            int c;
            while ((c = brTexto.read()) != -1) {
                char caracter = (char) c;
                char minuscula = Character.toLowerCase(caracter);

                if (mapaCifrado.containsKey(minuscula)) {
                    char cifradoChar = mapaCifrado.get(minuscula);

                    if (Character.isUpperCase(caracter)) {
                        bw.write(Character.toUpperCase(cifradoChar));
                    } else {
                        bw.write(cifradoChar);
                    }
                } else {
                    bw.write(caracter);
                }
            }

            System.out.println("Archivo cifrado generado correctamente en: " + RUTA_SALIDA);

        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}