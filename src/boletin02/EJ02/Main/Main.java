package boletin02.EJ02.Main;

import java.io.*;
import java.util.*;

public class Main {
    private static final String RUTA_ARCHIVO = "firmas.txt";

    public static void main(String[] args) {
        List<String> firmas = cargarFirmas();
        Scanner sc = new Scanner(System.in);
        int opc = -1;

        while (opc != 0) {
            mostrarMenu();
            try {
                opc = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida, introduce un número.");
                continue;
            }

            switch (opc) {
                case 1 -> {
                    System.out.print("Introduce el nombre del nuevo visitante: ");
                    String nombre = sc.nextLine().trim();
                    
                    if (nombre.isEmpty()) {
                        System.out.println("El nombre no puede estar vacío.");
                    } else if (firmas.contains(nombre)) {
                        System.out.println("Error: El nombre ya existe en el libro.");
                    } else {
                        firmas.add(nombre);
                        guardarFirma(nombre);
                        System.out.println("Firma añadida correctamente.");
                    }
                }
                case 2 -> {
                    if (firmas.isEmpty()) {
                        System.out.println("(El libro está vacío)");
                    } else {
                        firmas.forEach(System.out::println);
                    }
                }
                default -> System.out.println("Opción incorrecta.");
            }
        }
        sc.close();
    }

    private static List<String> cargarFirmas() {
        List<String> lista = new ArrayList<>();
        File archivo = new File(RUTA_ARCHIVO);
        
        // Si el archivo no existe, no hacemos nada (simplemente se creará al añadir la primera firma)
        if (!archivo.exists()) return lista;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    lista.add(linea.trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error crítico al leer el archivo: " + e.getMessage());
        }
        return lista;
    }

    private static void guardarFirma(String nombre) {
        // 'true' en FileWriter habilita el modo APPEND
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO, true))) {
            bw.write(nombre);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Error al guardar la firma: " + e.getMessage());
        }
    }

    public static void mostrarMenu() {
        System.out.println("\n1. Introducir nuevo visitante");
        System.out.println("2. Mostrar libro");
        System.out.println("0. Salir");
        System.out.print("Selecciona una opción: ");
    }
}