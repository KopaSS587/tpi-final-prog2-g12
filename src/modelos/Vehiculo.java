package src.modelos;

import java.time.LocalDateTime;
import src.interfaces.Tarifa;

public abstract class Vehiculo {

    protected String patente;
    protected String marca;
    protected LocalDateTime horaEntrada;
    protected Tarifa tarifa;

    public Vehiculo(String patente, String marca, LocalDateTime horaEntrada, Tarifa tarifa) {
        this.patente = patente.toUpperCase();
        this.marca = marca;
        this.horaEntrada = horaEntrada;
        this.tarifa = tarifa;
    }

    public String getPatente() {
        return patente;
    }

    public String getMarca() {
        return marca;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    protected double calcularTarifa(long minutos, int tipoVehiculo) {
        return tarifa.calcularTarifa(minutos, tipoVehiculo);
    }


    public abstract double calcularTarifa(long minutos);
}
