package boletin02.EJ03.Main;

import java.io.*;
import java.util.*;

public class Main {
    private static final String RUTA_ARCHIVO = "src/boletin02/EJ03/more.txt";

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO)); 
        		Scanner sc = new Scanner(System.in);){
        	String linea; 
        	int lineasMostradas = 0; 
        	while ((linea = br.readLine()) != null) {
        		System.out.println(linea);
        		lineasMostradas ++; 
        		if (lineasMostradas  == 24) {
        			lineasMostradas = 0; 
        			System.out.println("------------ Enter para continuar / [0] para salir --------------");
        			String u = sc.nextLine(); 
        			if (u.equalsIgnoreCase("0")) break; 
        			
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