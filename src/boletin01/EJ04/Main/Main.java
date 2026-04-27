package boletin01.EJ04.Main;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	
    	String cadena;
    	
        try (BufferedWriter  in = new BufferedWriter (new FileWriter("src/boletin01/EJ04/Archivo/texto.txt" ));
            	Scanner sc = new Scanner(System.in);	) {
        	pedirDatos();
        	cadena = sc.nextLine().trim();
        	while (!cadena.equals("fin")) {
        		in.write(cadena);
        		in.newLine();
        		
            	pedirDatos();
            	cadena = sc.nextLine().trim();
            }

        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el fichero texto.txt");
        }catch (IOException e) {
			System.out.println("IOException");
		}catch (Exception e) {
			System.out.println("Error inesperado: " +e);
		}
        //try-with-resources, no necesitas finally para cerrar ni vaciar el buffer; el cierre automático ya lo hace por ti.
    }
    
    public static void pedirDatos() {
    	System.out.println("Introduce una cadena por teclado ('fin' para terminar)");
    }

}