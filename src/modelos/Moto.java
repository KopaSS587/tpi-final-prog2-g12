package src.modelos;

import java.time.LocalDateTime;
import src.interfaces.Tarifa;

public class Moto extends Vehiculo {

    public Moto(String patente, String marca, LocalDateTime horaEntrada, Tarifa tarifa) {
        super(patente, marca, horaEntrada, tarifa);
    }

    public double calcularTarifa(long minutos) {
        return super.calcularTarifa(minutos, 0); // MOTO = tipo 0
    }
}
