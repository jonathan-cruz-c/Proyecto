package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Pago;
import Service.PagoService;
import java.sql.Date;

public class PagoController {
    static Scanner sc = new Scanner(System.in);

    // ! Insertar pago (solo admin)
    public static void insertarPago(int idsolicitante, int rol_id) {
        try {
            if (rol_id == 1) {
                System.out.println("===== INSERTAR PAGO (ADMIN) =====");
                System.out.print("Ingrese el ID del producto: ");
                int idproducto = sc.nextInt();
                sc.nextLine();
                PagoService.insertarPago(idsolicitante, idproducto);
            } else {
                System.out.println("No tienes permisos para insertar pagos");
            }
        } catch (Exception e) {
            System.out.println("Error en PagoController.insertarPago: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Actualizar pago (solo admin)
    public static void actualizarPago(int idsolicitante, int rol_id) {
        try {
            if (rol_id == 1) {
                System.out.println("===== ACTUALIZAR PAGO (ADMIN) =====");
                System.out.print("Ingrese el ID del pago: ");
                int idpago = sc.nextInt();
                sc.nextLine();
                System.out.print("Ingrese la nueva fecha (formato YYYY-MM-DD): ");
                String fechas = sc.nextLine();
                Date fecha = Date.valueOf(fechas);
                System.out.print("Ingrese el nuevo monto: ");
                double monto = sc.nextDouble();
                sc.nextLine();
                Pago pago = new Pago();
                pago.setId(idpago);
                pago.setFecha_pago(fecha);
                pago.setMonto(monto);
                PagoService.actualizarPago(pago, idsolicitante);
            } else {
                System.out.println("No tienes permisos para actualizar pagos");
            }
        } catch (Exception e) {
            System.out.println("Error en PagoController.actualizarPago: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Eliminar pago (solo admin)
    public static void eliminarPago(int idsolicitante, int rol_id) {
        try {
            if (rol_id == 1) {
                System.out.println("===== ELIMINAR PAGO (ADMIN) =====");
                System.out.print("Ingrese el ID del pago a eliminar: ");
                int idpago = sc.nextInt();
                sc.nextLine();
                PagoService.eliminarPago(idpago, idsolicitante);
            } else {
                System.out.println("No tienes permisos para eliminar pagos");
            }
        } catch (Exception e) {
            System.out.println("Error en PagoController.eliminarPago: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Consultar pagos (admin ve todos, cliente ve los suyos)
    public static void consultarPagos(int idsolicitante, int rol_id) {
        try {
            System.out.println("===== CONSULTAR PAGOS =====");
            ArrayList<Pago> pagos = PagoService.consultarPagos(idsolicitante);
            if (pagos.isEmpty()) {
                System.out.println("No se encontraron pagos");
            } else {
                for (Pago pago : pagos) {
                    System.out.println("ID: " + pago.getId() +
                            " | Fecha para pagar: " + pago.getFecha_pago() +
                            " | Monto: $" + pago.getMonto());
                }
            }
        } catch (Exception e) {
            System.out.println("Error en PagoController.consultarPagos: " + e.getMessage());
            sc.nextLine();
        }
    }
}