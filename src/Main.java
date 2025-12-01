package src;

import src.excepciones.VehiculoNoEncontradoException;
import src.interfaces.Tarifa;
import src.modelos.*;
import src.modelos.TarifaPorHorario;
import src.servicios.Estacionamiento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Estacionamiento estacionamiento = new Estacionamiento();

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE ESTACIONAMIENTO (TFI) ===");

        while (true) {
            mostrarMenu();
            String op = scanner.nextLine().trim();

            switch (op) {
                case "1" -> registrarIngreso();
                case "2" -> registrarSalida();
                case "3" -> buscarVehiculo();
                case "4" -> listarVehiculos();
                case "5" -> mostrarGanancias();
                case "6" -> ordenarPorHoraEntrada();
                case "7" -> eliminarVehiculo();
                case "8" -> buscarHistorico();
                case "9" -> mostrarMovimientos();
                case "10" -> mostrarTarifas();
                case "00", "0" -> {
                    System.out.println("Saliendo... ¡Buen día!");
                    return;
                }
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }

            System.out.println("→ Fin de la operación.\n");
        }
    }

    private static void mostrarMenu() {
        System.out.println();
                
                ===========================
                MENÚ - SISTEMA ESTACIONAMIENTO
                ===========================
                1  - Registrar ingreso de vehículo
                2  - Registrar salida de vehículo
                3  - Buscar vehículo por patente
                4  - Listar vehículos en el estacionamiento
                5  - Mostrar ganancias del día
                6  - Ordenar por hora de entrada
                7  - Eliminar vehículo (Administrador)
                8  - Mostrar historial por patente
                9  - Mostrar movimientos (TAD Propio)
                10 - Mostrar tarifas actuales
                00 - Salir
                """);
        System.out.print("Seleccione una opción: ");
    }


    private static void registrarIngreso() {
        System.out.print("Patente: ");
        final String patente = scanner.nextLine().trim().toUpperCase();

        if (patente.isEmpty()) {
            System.out.println("Patente inválida. Operación cancelada.");
            return;
        }

        System.out.print("Marca: ");
        String marca = scanner.nextLine().trim();

        System.out.println("Tipo de vehículo: 1=Moto, 2=Auto, 3=Camioneta");
        String tipoStr = scanner.nextLine().trim();

        Tarifa tarifa = new TarifaPorHorario();
        Vehiculo v;
        LocalDateTime ahora = LocalDateTime.now();

        switch (tipoStr) {
            case "1" -> v = new Moto(patente, marca, ahora, tarifa);
            case "2" -> v = new Auto(patente, marca, ahora, tarifa);
            case "3" -> v = new Camioneta(patente, marca, ahora, tarifa);
            default -> {
                System.out.println("Tipo inválido. Ingreso cancelado.");
                return;
            }
        }

        estacionamiento.registrarIngreso(v);
        System.out.println("Ingreso registrado correctamente. Ticket emitido.");
    }

    private static void registrarSalida() {
        System.out.print("Patente a registrar salida: ");
        final String patente = scanner.nextLine().trim().toUpperCase();

        if (patente.isEmpty()) {
            System.out.println("Patente inválida. Operación cancelada.");
            return;
        }

        try {

            Function<Ticket, Double> calcularMonto = ticket -> {
                long minutos = ticket.calcularMinutos();
                return ticket.getVehiculo().calcularTarifa(minutos);
            };

            estacionamiento.registrarSalida(patente, calcularMonto);
            System.out.println("Salida procesada y monto calculado.");

        } catch (VehiculoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }


    private static void buscarVehiculo() {
        System.out.print("Patente a buscar: ");
        final String patente = scanner.nextLine().trim().toUpperCase();

        try {
            Ticket t = estacionamiento.buscarPorPatente(patente);
            System.out.println("Vehículo encontrado:");
            System.out.println(t);
            System.out.println("Solicitud completada correctamente.");
        } catch (VehiculoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }


    private static void listarVehiculos() {
        estacionamiento.listarOcupados();
    }


    private static void mostrarGanancias() {
        estacionamiento.mostrarGananciasDelDia();
    }


    private static void ordenarPorHoraEntrada() {
        System.out.println("Ordenar por hora de entrada:");
        System.out.println("A - Más antiguo → más reciente (ascendente)");
        System.out.println("B - Más reciente → más antiguo (descendente)");
        System.out.print("Seleccione A o B: ");

        String op = scanner.nextLine().trim().toUpperCase();
        boolean desc = op.equals("B");

        estacionamiento.ordenarPorHoraEntrada(desc);
    }

    private static void eliminarVehiculo() {
        System.out.print("Patente a eliminar: ");
        final String patente = scanner.nextLine().trim().toUpperCase();

        try {
            estacionamiento.eliminarPorPatente(patente);
            System.out.println("Vehículo eliminado correctamente.");
        } catch (VehiculoNoEncontradoException e) {
            System.out.println(e.getMessage());
        }
    }


    private static void buscarHistorico() {
        System.out.print("Patente: ");
        final String patente = scanner.nextLine().trim().toUpperCase();


        try {
            estacionamiento.buscarPorPatente(patente);
            System.out.println("El vehículo todavía NO se retiró del estacionamiento.");
            return;
        } catch (VehiculoNoEncontradoException ignored) {}

        List<Ticket> lista = estacionamiento.buscarHistoricoPorPatente(patente);

        if (lista.isEmpty()) {
            System.out.println("No existe historial para esa patente.");
            return;
        }

        System.out.println("=== HISTORIAL ===");
        lista.forEach(System.out::println);
        System.out.println("Operación realizada con éxito.");
    }


    private static void mostrarMovimientos() {
        List<String> movs = estacionamiento.movimientosToList();

        if (movs.isEmpty()) {
            System.out.println("No hay movimientos registrados todavía.");
            return;
        }

        System.out.println("=== MOVIMIENTOS (TAD PROPIO) ===");
        movs.forEach(System.out::println);
        System.out.println("Operación realizada con éxito.");
    }

    private static void mostrarTarifas() {
        System.out.println("=== TARIFAS ACTUALES (por minuto) ===");
        System.out.println("           MOTO    AUTO    CAMIONETA");
        System.out.printf("Mañana   %7s %7s %11s%n", "$100", "$130", "$130");
        System.out.printf("Tarde    %7s %7s %11s%n", "$100", "$130", "$130");
        System.out.printf("Noche    %7s %7s %11s%n", "$150", "$150", "$150");
        System.out.println("Operación realizada con éxito.");
    }
}