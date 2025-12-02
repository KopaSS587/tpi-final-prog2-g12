package src.modelos;

import java.time.LocalDateTime;
import src.interfaces.Tarifa;

public class Auto extends Vehiculo {

    public Auto(String patente, String marca, LocalDateTime horaEntrada, Tarifa tarifa) {
        super(patente, marca, horaEntrada, tarifa);
    }


    public double calcularTarifa(long minutos) {
        return super.calcularTarifa(minutos, 1); // AUTO = tipo 1
    }
}
