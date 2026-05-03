package boletin02.EJ07.cliente;

import java.time.LocalDate;
import java.time.Period;

public class Cliente implements Comparable<Cliente> {
    private String dni;
    private String nombreCompleto;
    private LocalDate fechaNacimiento;
    private double saldo;

    public Cliente(String dni, String nombreCompleto, LocalDate fechaNacimiento, double saldo) {
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
        this.saldo = saldo;
    }

    public String getDni() {
        return dni;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public double getSaldo() {
        return saldo;
    }

    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    @Override
    public int compareTo(Cliente o) {
        return this.dni.compareTo(o.dni);
    }

    @Override
    public String toString() {
        return dni + " - " + nombreCompleto + " - Saldo: " + saldo + " - Edad: " + getEdad();
    }
}