package boletin01.EJ08.Main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FILE = "src/boletin01/EJ08/temperaturas.txt";

    public static void main(String[] args) {
        List<Registro> registros = new ArrayList<>();
        cargarRegistros(registros);

        try (Scanner sc = new Scanner(System.in)) {
            int opcion;
            do {
                System.out.println("1. Registra nueva temperatura.");
                System.out.println("2. Mostrar historial de registros.");
                System.out.println("3. Salir.");
                System.out.print("Elige una opción: ");
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1 -> registrar(sc, registros);
                    case 2 -> mostrarHistorial(registros);
                    case 3 -> guardarRegistros(registros);
                    default -> System.out.println("Opción no válida");
                }
            } while (opcion != 3);
        }catch (Exception e) {
			System.out.println("Error inesperado: " + e );
		}
    }

    private static void registrar(Scanner sc, List<Registro> registros) {
        System.out.print("Fecha (yyyy-MM-dd): ");
        LocalDate fecha = LocalDate.parse(sc.nextLine().trim());
        System.out.print("Temperatura máxima: ");
        int max = Integer.parseInt(sc.nextLine().trim());
        System.out.print("Temperatura mínima: ");
        int min = Integer.parseInt(sc.nextLine().trim());
        registros.add(new Registro(fecha, max, min));
        System.out.println("Registro añadido.");
    }

    private static void mostrarHistorial(List<Registro> registros) {
        if (registros.isEmpty()) {
            System.out.println("No hay registros.");
            return;
        }

        int maxGlobal = Integer.MIN_VALUE;
        int minGlobal = Integer.MAX_VALUE;

        for (Registro r : registros) {
            System.out.println(r);
            if (r.max > maxGlobal) maxGlobal = r.max;
            if (r.min < minGlobal) minGlobal = r.min;
        }

        System.out.println("Máxima absoluta: " + maxGlobal);
        System.out.println("Mínima absoluta: " + minGlobal);
    }

    private static void guardarRegistros(List<Registro> registros) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {
            for (Registro r : registros) {
                bw.write(r.toCsv());
                bw.newLine();
            }
            System.out.println("Registros guardados.");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    private static void cargarRegistros(List<Registro> registros) {
        File f = new File(FILE);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(",");
                if (d.length == 3) {
                    registros.add(new Registro(LocalDate.parse(d[0]), Integer.parseInt(d[1]), Integer.parseInt(d[2])));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }
    }

    static class Registro {
        LocalDate fecha;
        int max;
        int min;

        Registro(LocalDate fecha, int max, int min) {
            this.fecha = fecha;
            this.max = max;
            this.min = min;
        }

        String toCsv() {
            return fecha + "," + max + "," + min;
        }

        public String toString() {
            return fecha + " -> Máx: " + max + " / Mín: " + min;
        }
    }
}