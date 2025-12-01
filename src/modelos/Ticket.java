package src.modelos;

import java.time.LocalDateTime;

public class Ticket {

    private Vehiculo vehiculo;
    private LocalDateTime salida;
    private double monto;

    public Ticket(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Vehiculo getVehiculo() { return vehiculo; }
    public LocalDateTime getSalida() { return salida; }
    public double getMonto() { return monto; }

    public void registrarSalida(LocalDateTime salida) {
        this.salida = salida;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public long calcularMinutos() {
        return java.time.Duration.between(
                vehiculo.getHoraEntrada(),
                salida
        ).toMinutes();
    }
}
