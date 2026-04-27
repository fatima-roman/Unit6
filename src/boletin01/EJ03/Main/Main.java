package boletin01.EJ03.Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int sumaEdades = 0;
        double sumaEstatura = 0;
        String nombre = "";
        int contador = 0;

        try (BufferedReader in = new BufferedReader(new FileReader("src/boletin01/EJ03/Alumnos/Alumnos.txt" ))) {
        	String line = in.readLine();
        	while (line != null) {
                String[] datos = line.trim().split("\\s+");
                int edad = Integer.parseInt(datos[1]);
                sumaEdades += edad ;
                double estatura = Double.parseDouble(datos[2]);
                sumaEstatura += estatura; 
                nombre = nombre + " " + datos[0];
                contador++;
            	line = in.readLine();
            }

            if (contador > 0) {
            	double mediaEdad = (double) sumaEdades / contador;
            	double mediaEstatura = sumaEstatura / contador;
            	System.out.println(nombre);
            	System.out.println("Suma Edades: " + sumaEdades);
                System.out.println("Suma Estatura: " + sumaEstatura);
                System.out.println("Media Edad: " + mediaEdad);
                System.out.println("Media Estatura: " + mediaEstatura);
            } else {
                System.out.println("El fichero no contiene alumnos válidos.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el fichero Alumnos.txt");
        }catch (IOException e) {
			System.out.println("IOException");
		}catch (Exception e) {
			System.out.println("Error inesperado: " +e);
		}
    }

}