package src.servicios;

import src.excepciones.VehiculoNoEncontrado;
import src.modelos.*;

import java.time.LocalDateTime;
import java.util.*;

public class Estacionamiento {

    private Map<String, Ticket> ocupados = new HashMap<>();
    private List<Ticket> historico = new ArrayList<>();
    private SimpleLinkedList<String> movimientos = new SimpleLinkedList<>();

    public Map<String, Ticket> getOcupados() { return ocupados; }
    public List<Ticket> getHistorico() { return historico; }
    public SimpleLinkedList<String> getMovimientos() { return movimientos; }

    public void registrarIngreso(Vehiculo v) {
        String patente = v.getPatente();

        if (ocupados.containsKey(patente)) {
            System.out.println("No es posible registrar el ingreso: el vehículo ya está dentro.");
            return;
        }

        Ticket t = new Ticket(v);
        ocupados.put(patente, t);

        movimientos.insertAtEnd("Ingreso: " + patente + " (" + LocalDateTime.now() + ")");

        System.out.println("Ingreso registrado correctamente. Ticket #" + t.getNumeroTicket());
    }
}
