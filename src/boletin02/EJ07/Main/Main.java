package boletin02.EJ07.Main;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import boletin02.EJ07.cliente.Cliente;

public class Main {
    private static final String RUTA_ARCHIVO = "src/boletin02/EJ07/clientes.txt";
    private static ArrayList<Cliente> clientes = new ArrayList<>();

    public static void main(String[] args) {
        cargarClientes();

        try (Scanner sc = new Scanner(System.in)) {
            int opcion;

            do {
                System.out.println("\n----- MENÚ -----");
                System.out.println("1. Alta cliente");
                System.out.println("2. Baja cliente");
                System.out.println("3. Listar clientes");
                System.out.println("4. Salir");
                System.out.print("Opción: ");
                opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1:
                        altaCliente(sc);
                        break;
                    case 2:
                        bajaCliente(sc);
                        break;
                    case 3:
                        listarClientes();
                        break;
                    case 4:
                        guardarClientes();
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }

            } while (opcion != 4);

        } catch (Exception e) {
            System.out.println("Error");
        }
    }

    private static void cargarClientes() {
        File fichero = new File(RUTA_ARCHIVO);

        if (!fichero.exists()) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] partes = linea.split(";");

                if (partes.length == 4) {
                    String dni = partes[0];
                    String nombre = partes[1];
                    LocalDate fechaNacimiento = LocalDate.parse(partes[2]);
                    double saldo = Double.parseDouble(partes[3].replace(",", "."));

                    clientes.add(new Cliente(dni, nombre, fechaNacimiento, saldo));
                }
            }

            Collections.sort(clientes);

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private static void altaCliente(Scanner sc) {
        try {
            System.out.print("DNI: ");
            String dni = sc.nextLine();

            if (buscarClientePorDni(dni) != null) {
                System.out.println("Ya existe un cliente con ese DNI.");
                return;
            }

            System.out.print("Nombre completo: ");
            String nombre = sc.nextLine();

            System.out.print("Fecha de nacimiento (AAAA-MM-DD): ");
            LocalDate fechaNacimiento = LocalDate.parse(sc.nextLine());

            System.out.print("Saldo: ");
            double saldo = Double.parseDouble(sc.nextLine().replace(",", "."));

            Cliente nuevo = new Cliente(dni, nombre, fechaNacimiento, saldo);
            clientes.add(nuevo);
            Collections.sort(clientes);

            System.out.println("Cliente dado de alta correctamente.");

        } catch (Exception e) {
            System.out.println("Error al dar de alta el cliente.");
        }
    }

    private static void bajaCliente(Scanner sc) {
        System.out.print("Introduce el DNI del cliente a eliminar: ");
        String dni = sc.nextLine();

        Cliente cliente = buscarClientePorDni(dni);

        if (cliente != null) {
            clientes.remove(cliente);
            System.out.println("Cliente eliminado correctamente.");
        } else {
            System.out.println("No existe un cliente con ese DNI.");
        }
    }

    private static Cliente buscarClientePorDni(String dni) {
        for (Cliente c : clientes) {
            if (c.getDni().equalsIgnoreCase(dni)) {
                return c;
            }
        }
        return null;
    }

    private static void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        double saldoMax = Double.MIN_VALUE;
        double saldoMin = Double.MAX_VALUE;
        double sumaSaldos = 0;

        for (Cliente c : clientes) {
            System.out.println(c);

            if (c.getSaldo() > saldoMax) {
                saldoMax = c.getSaldo();
            }

            if (c.getSaldo() < saldoMin) {
                saldoMin = c.getSaldo();
            }

            sumaSaldos += c.getSaldo();
        }

        double promedio = sumaSaldos / clientes.size();

        System.out.println("\nSaldo máximo: " + saldoMax);
        System.out.println("Saldo mínimo: " + saldoMin);
        System.out.println("Saldo promedio: " + promedio);
    }

    private static void guardarClientes() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(RUTA_ARCHIVO))) {
            for (Cliente c : clientes) {
                bw.write(c.getDni() + ";" +
                         c.getNombreCompleto() + ";" +
                         c.getFechaNacimiento() + ";" +
                         c.getSaldo());
                bw.newLine();
            }

            System.out.println("Datos guardados correctamente.");

        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }
}