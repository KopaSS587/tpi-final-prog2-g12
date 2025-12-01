package src.modelos;

import java.time.LocalDateTime;
import src.interfaces.Tarifa;

public class Camioneta extends Vehiculo {

    public Camioneta(String patente, String marca, LocalDateTime horaEntrada, Tarifa tarifa) {
        super(patente, marca, horaEntrada, tarifa);
    }

    public double calcularTarifa(long minutos) {
        return super.calcularTarifa(minutos, 2); // CAMIONETA = tipo 2
    }
}
