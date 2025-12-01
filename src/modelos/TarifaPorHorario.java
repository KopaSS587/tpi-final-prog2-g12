package src.modelos;

import src.interfaces.Tarifa;
import java.time.LocalDateTime;

public class TarifaPorHorario implements Tarifa {
    private final double[][] matrizTarifas = {
            {100, 100, 150},  // Moto
            {130, 130, 150},  // Auto
            {130, 130, 150}   // Camioneta
    };


    public double calcularTarifa(long minutos, int tipoVehiculo) {

        int hora = LocalDateTime.now().getHour();
        int horario;

        if (hora >= 6 && hora < 12) horario = 0;
        else if (hora >= 12 && hora < 18) horario = 1;
        else horario = 2;

        double precioPorMinuto = matrizTarifas[tipoVehiculo][horario];
        return minutos * precioPorMinuto;
    }
}
