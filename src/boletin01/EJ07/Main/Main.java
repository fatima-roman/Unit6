package boletin01.EJ07.Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    private static final String FILE = "src/boletin01/EJ07/agenda.txt";
    private static final int MAX_CONTACTOS = 20;

    public static void main(String[] args) {
        Map<String, String> agenda = new TreeMap<>();
        cargarAgenda(agenda);

        try (Scanner sc = new Scanner(System.in)) {
            int opcion;
            do {
                menu();
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1 -> nuevoContacto(sc, agenda);
                    case 2 -> buscarPorNombre(sc, agenda);
                    case 3 -> mostrarTodos(agenda);
                    case 4 -> guardarAgenda(agenda);
                    default -> System.out.println("Opción no válida");
                }
            } while (opcion != 4);
        }
    }

    private static void menu() {
        System.out.println("1. Nuevo contacto.");
        System.out.println("2. Buscar por nombre.");
        System.out.println("3. Mostrar todos.");
        System.out.println("4. Salir.");
        System.out.print("Elige una opción: ");
    }

    private static void nuevoContacto(Scanner sc, Map<String, String> agenda) {
        if (agenda.size() >= MAX_CONTACTOS) {
            System.out.println("La agenda está llena.");
            return;
        }

        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        if (agenda.containsKey(nombre)) {
            System.out.println("Ese nombre ya existe.");
            return;
        }

        System.out.print("Teléfono: ");
        String telefono = sc.nextLine().trim();
        agenda.put(nombre, telefono);
        System.out.println("Contacto guardado.");
    }

    private static void buscarPorNombre(Scanner sc, Map<String, String> agenda) {
        System.out.print("Nombre a buscar: ");
        String nombre = sc.nextLine().trim();
        String telefono = agenda.get(nombre);
        if (telefono != null) {
            System.out.println("Teléfono: " + telefono);
        } else {
            System.out.println("No existe ese contacto.");
        }
    }

    private static void mostrarTodos(Map<String, String> agenda) {
        if (agenda.isEmpty()) {
            System.out.println("Agenda vacía.");
            return;
        }
        for (Map.Entry<String, String> e : agenda.entrySet()) {
            System.out.println(e.getKey() + " - " + e.getValue());
        }
    }

    private static void guardarAgenda(Map<String, String> agenda) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
            for (Map.Entry<String, String> e : agenda.entrySet()) {
                bw.write(e.getKey() + ";" + e.getValue());
                bw.newLine();
            }
            System.out.println("Agenda guardada.");
        } catch (IOException e) {
            System.out.println("Error al guardar la agenda: " + e.getMessage());
        }
    }

    private static void cargarAgenda(Map<String, String> agenda) {
        File f = new File(FILE);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length == 2) agenda.put(datos[0], datos[1]);
            }
        } catch (IOException e) {
            System.out.println("Error al cargar agenda: " + e.getMessage());
        }
    }
}