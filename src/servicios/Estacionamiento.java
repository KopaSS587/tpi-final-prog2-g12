package src.servicios;

import src.excepciones.VehiculoNoEncontrado;
import src.modelos.Ticket;
import src.modelos.Vehiculo;
import src.modelos.SimpleLinkedList;

import java.time.LocalDateTime;
import java.util.*;

public class Estacionamiento {

    private final Map<String, Ticket> ocupados = new HashMap<>();
    private final List<Ticket> historico = new ArrayList<>();


    private final SimpleLinkedList<String> movimientos = new SimpleLinkedList<>();



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



    public void registrarSalida(String patente, java.util.function.Function<Ticket, Double> calculoMonto)
            throws VehiculoNoEncontrado {

        patente = patente.toUpperCase();

        Ticket t = ocupados.remove(patente);

        if (t == null) {
            throw new VehiculoNoEncontrado(
                    "No es posible registrar la salida: el vehículo no está en el estacionamiento."
            );
        }

        t.registrarSalida(LocalDateTime.now());

        double monto = calculoMonto.apply(t);
        t.setMonto(monto);

        historico.add(t);

        movimientos.insertAtEnd("Salida: " + patente + " (" + LocalDateTime.now() + ")");

        System.out.println("Salida registrada con éxito.");
        System.out.println(t);
    }



    public Ticket buscarPorPatente(String patente) throws VehiculoNoEncontrado {
        patente = patente.toUpperCase();
        Ticket t = ocupados.get(patente);

        if (t == null)
            throw new VehiculoNoEncontrado("El vehículo con esa patente no existe dentro del estacionamiento.");

        return t;
    }



    public void listarOcupados() {
        if (ocupados.isEmpty()) {
            System.out.println("No hay vehículos actualmente en el estacionamiento.");
            return;
        }

        System.out.println("=== Vehículos en el estacionamiento ===");
        ocupados.values().forEach(System.out::println);
    }



    public void mostrarGananciasDelDia() {
        if (historico.isEmpty()) {
            System.out.println("No hay registros de ganancias aún.");
            return;
        }

        double total = historico.stream()
                .mapToDouble(Ticket::getMontoAPagar)
                .sum();

        System.out.printf("Ganancias del día: $%.2f%n", total);
    }



    public void ordenarPorHoraEntrada() {
        ordenarPorHoraEntrada(false);
    }

    public void ordenarPorHoraEntrada(boolean descending) {
        if (ocupados.isEmpty()) {
            System.out.println("No hay vehículos para ordenar.");
            return;
        }

        List<Ticket> lista = new ArrayList<>(ocupados.values());
        lista.sort(Comparator.comparing(t -> t.getVehiculo().getHoraEntrada()));

        if (descending) Collections.reverse(lista);

        System.out.println("=== Vehículos ordenados ===");
        lista.forEach(System.out::println);
    }


    public void eliminarPorPatente(String patente) throws VehiculoNoEncontrado {
        patente = patente.toUpperCase();

        Ticket eliminado = ocupados.remove(patente);

        if (eliminado == null)
            throw new VehiculoNoEncontrado("No se puede eliminar: el vehículo no está dentro.");

        movimientos.insertAtEnd("Eliminado: " + patente + " (" + LocalDateTime.now() + ")");

        System.out.println("Vehículo eliminado correctamente.");
    }



    public List<Ticket> buscarHistoricoPorPatente(String patente) {
        patente = patente.toUpperCase();
        String finalPatente = patente;
        return historico.stream()
                .filter(t -> t.getVehiculo().getPatente().equals(finalPatente))
                .toList();
    }



    public List<String> movimientosToList() {
        return movimientos.toList();
    }
}
