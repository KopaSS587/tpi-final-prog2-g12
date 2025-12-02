package src.modelos;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {

    private static int contador = 1;

    private final int numeroTicket;
    private final Vehiculo vehiculo;

    private final LocalDateTime horaEntrada;
    private LocalDateTime horaSalida;

    public Double monto;

    public Ticket(Vehiculo vehiculo) {
        this.numeroTicket = contador++;
        this.vehiculo = vehiculo;
        this.horaEntrada = vehiculo.getHoraEntrada();
    }

    public int getNumeroTicket() { return numeroTicket; }

    public Vehiculo getVehiculo() { return vehiculo; }

    public LocalDateTime getHoraEntrada() { return horaEntrada; }

    public LocalDateTime getHoraSalida() { return horaSalida; }

    public Double getMontoAPagar() { return monto; }

    public void registrarSalida(LocalDateTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public void setMonto(Double m) { this.monto = m; }


    public long calcularMinutos() {
        if (horaSalida == null) return 0;
        Duration d = Duration.between(horaEntrada, horaSalida);
        long mins = d.toMinutes();
        return (mins<1) ? 1 : mins;
    }

    public String toString() {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String salidaStr = horaSalida == null ? "-" : horaSalida.format(f);
        String montoStr = monto == null ? "-" : String.format("$%.2f", monto);
        return String.format("Ticket #%d | Patente: %s | Entrada: %s | Salida: %s | Monto: %s",
                numeroTicket, vehiculo.getPatente(), horaEntrada.format(f), salidaStr, montoStr);
    }
}
