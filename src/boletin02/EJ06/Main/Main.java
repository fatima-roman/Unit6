package boletin02.EJ06.Main;

import java.io.*;

public class Main {
    private static final String RUTA_ARCHIVO = "src/boletin02/EJ06/deportistas.txt";

    public static void main(String[] args) {
        String nombreMayorEdad = "";
        String nombreMayorPeso = "";
        String nombreMayorEstatura = "";

        int mayorEdad = Integer.MIN_VALUE;
        double mayorPeso = Double.MIN_VALUE;
        double mayorEstatura = Double.MIN_VALUE;

        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_ARCHIVO))) {
            String linea;

            br.readLine();

            while ((linea = br.readLine()) != null) {
                if (linea.trim().isEmpty()) continue;

                String[] partes = linea.trim().split("\\s+");

                if (partes.length < 4) continue;

                int n = partes.length;

                int edad = Integer.parseInt(partes[n - 3]);
                double peso = Double.parseDouble(partes[n - 2].replace(",", "."));
                double estatura = Double.parseDouble(partes[n - 1].replace(",", "."));

                StringBuilder nombre = new StringBuilder();
                for (int i = 0; i < n - 3; i++) {
                    nombre.append(partes[i]);
                    if (i < n - 4) {
                        nombre.append(" ");
                    }
                }

                String nombreCompleto = nombre.toString();

                if (edad > mayorEdad) {
                    mayorEdad = edad;
                    nombreMayorEdad = nombreCompleto;
                }

                if (peso > mayorPeso) {
                    mayorPeso = peso;
                    nombreMayorPeso = nombreCompleto;
                }

                if (estatura > mayorEstatura) {
                    mayorEstatura = estatura;
                    nombreMayorEstatura = nombreCompleto;
                }
            }

            System.out.println("Deportista de mayor edad: " + nombreMayorEdad + " (" + mayorEdad + " años)");
            System.out.println("Deportista de mayor peso: " + nombreMayorPeso + " (" + mayorPeso + " kg)");
            System.out.println("Deportista de mayor estatura: " + nombreMayorEstatura + " (" + mayorEstatura + " m)");

        } catch (IOException e) {
            System.err.println("Error al procesar el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir datos numéricos: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
}