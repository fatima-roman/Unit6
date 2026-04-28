package boletin02.EJ04.Main;

import java.io.*;
import java.util.*;

public class Main {
    private static final String RUTA_MENSAJE = "src/boletin02/EJ04/mensaje.txt";
    private static final String RUTA_CODEC = "src/boletin02/EJ04/codec.txt";

    /*
     * Carga inicial: Al arrancar el programa, lee codec.txt y almacena la relación en un HashMap<Character, Character> 
     * 	o un array de tamaño 26 (donde el índice es la letra original y el valor la cifrada).
     * Procesamiento: Lee el archivo de texto original (mensaje.txt) carácter a carácter o línea a línea.
     * Sustitución: Por cada carácter leído:
     * Si es una letra, busca su equivalente en tu estructura (Map o array) y escríbelo en el fichero de salida.
     * Si es un espacio, tabulación o signo de puntuación, mantenlo tal cual (o según indique tu profesor).
     * Escritura: Usa un BufferedWriter para volcar el resultado en un nuevo archivo (ej. mensaje_cifrado.txt).
     */
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_CODEC)); 
        		Scanner sc = new Scanner(System.in);){
        	Map<Character, Character> codec = new HashMap<>();
            String lineaAlfabeto = br.readLine(); 
            String lineaCifrado = br.readLine();  
            
            if (lineaAlfabeto != null && lineaCifrado != null) {
                String letrasOriginales = lineaAlfabeto.split(":")[1].replace(" ", "");
                String letrasCifradas = lineaCifrado.split(":")[1].replace(" ", "");
                
                for (int i = 0; i < letrasOriginales.length(); i++) {
                    codec.put(letrasOriginales.charAt(i), letrasCifradas.charAt(i));
                }
        	}
        	System.out.println("Saliendo...");
        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        } catch (Exception e) {
        	System.out.println("Error");
        }
    }

}