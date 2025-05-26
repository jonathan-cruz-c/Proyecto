package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.DetalleOrden;
import Service.DetalleOrdenService;
import java.sql.Date;

public class DetalleOrdenController {
    static Scanner sc = new Scanner(System.in);

    // ! Insertar detalle de orden (admin puede cualquiera, cliente solo su pedido)
    public static void insertar(int idsolicitante) {
        try {
            System.out.println("=== INSERTAR DETALLE DE ORDEN ===");
            System.out.print("Ingrese el ID de la orden: ");
            int ordenid = sc.nextInt();
            sc.nextLine();
            System.out.print("Ingrese el ID de la dirección: ");
            int direccionid = sc.nextInt();
            sc.nextLine();
            System.out.print("Ingrese el ID del pago: ");
            int pagoid = sc.nextInt();
            sc.nextLine();
            DetalleOrdenService.insertarDetalleOrden(ordenid, direccionid, pagoid, idsolicitante);
        } catch (Exception e) {
            System.out.println("Error en DetalleOrdenController.insertar: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Actualizar detalle (solo admin)
    public static void actualizar(int idsolicitante) {
        try {
            System.out.println("=== ACTUALIZAR DETALLE DE ORDEN (Solo Admin) ===");
            System.out.print("Ingrese el ID del detalle a actualizar: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Ingrese el Nuevo ID de orden: ");
            int ordenId = sc.nextInt();
            sc.nextLine();
            System.out.print("Ingrese el nuevo ID de dirección: ");
            int direccionId = sc.nextInt();
            sc.nextLine();
            System.out.print("Ingrese el Nuevo ID de pago: ");
            int pagoId = sc.nextInt();
            sc.nextLine();
            System.out.print("Ingrese la nueva fecha de llegada (YYYY-MM-DD): ");
            String fechas = sc.nextLine();
            Date fechaLlegada = Date.valueOf(fechas);
            DetalleOrden detalle = new DetalleOrden();
            detalle.setId(id);
            detalle.setOrden_id(ordenId);
            detalle.setDireccion_id(direccionId);
            detalle.setPago_id(pagoId);
            detalle.setFecha_llegada(fechaLlegada);
            DetalleOrdenService.actualizarDetalleOrden(detalle, idsolicitante);
        } catch (Exception e) {
            System.out.println("Error en DetalleOrdenController.actualizar: " + e.getMessage());
        }
    }

    // ! Eliminar detalle (solo admin)
    public static void eliminar(int idsolicitante) {
        try {
            System.out.println("=== ELIMINAR DETALLE DE ORDEN (Solo Admin) ===");
            System.out.print("Ingrese el ID del detalle a eliminar: ");
            int id = sc.nextInt();
            sc.nextLine();
            DetalleOrdenService.eliminarDetalleOrden(id, idsolicitante);
        } catch (Exception e) {
            System.out.println("Error en DetalleOrdenController.eliminar: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Consultar detalles de orden (admin ve todo, cliente solo sus órdenes)
    public static void consultar(int idsolicitante) {
        try {
            System.out.println("=== CONSULTA DE DETALLES DE ORDEN ===");
            ArrayList<DetalleOrden> lista = DetalleOrdenService.consultarDetalles(idsolicitante);
            if (lista.isEmpty()) {
                System.out.println("No se encontraron detalles de orden.");
            } else {
                for (DetalleOrden d : lista) {
                    System.out.println("--------------------------------------");
                    System.out.println("ID Detalle: " + d.getId());
                    System.out.println("Orden ID: " + d.getOrden_id());
                    System.out.println("Dirección ID: " + d.getDireccion_id());
                    System.out.println("Pago ID: " + d.getPago_id());
                    System.out.println("Fecha de Llegada: " + d.getFecha_llegada());
                }
            }
        } catch (Exception e) {
            System.out.println("Error en DetalleOrdenController.consultar: " + e.getMessage());
        }
    }
}