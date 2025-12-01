package src.modelos;

import src.interfaces.Tarifa;

public class TarifaPorHorario implements Tarifa {

    private final double[][] matrizTarifas = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };

    @Override
    public double calcularTarifa(long minutos, int tipoVehiculo) {
        return 0; // Implementado luego
    }
}