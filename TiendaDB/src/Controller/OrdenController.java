package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Orden;
import Service.OrdenService;

public class OrdenController {
    static Scanner sc = new Scanner(System.in);

    // ! Insertar orden (admin puede indicar usuario_id, cliente no)
    public static void insertarOrden(int idsolicitante, int rolsolicitante) {
        try {
            Orden orden = new Orden();
            if (rolsolicitante == 1) {
                System.out.println("===== INSERTAR ÓRDENES (ADMIN) =====");
                System.out.print("Ingrese el ID del usuario para la orden: ");
                orden.setUsuario_id(sc.nextInt());
                sc.nextLine();
            } else {
                System.out.println("===== INSERTAR ÓRDENES =====");
                orden.setUsuario_id(idsolicitante);
            }
            System.out.print("Ingrese el ID del producto: ");
            orden.setProducto_id(sc.nextInt());
            sc.nextLine();
            System.out.print("Cantidad: ");
            orden.setCantidad(sc.nextInt());
            sc.nextLine();
            OrdenService.insertarOrden(orden, idsolicitante);
        } catch (Exception e) {
            System.out.println("Error al insertar orden: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Actualizar orden (solo admin)
    public static void actualizarOrden(int idsolicitante, int rolsolicitante) {
        try {
            if (rolsolicitante != 1) {
                System.out.println("No tienes permiso para actualizar órdenes.");
                return;
            }
            System.out.println("===== ACTUALIZAR ÓRDENES (ADMIN) =====");
            Orden orden = new Orden();
            System.out.print("Ingrese el ID de la orden a actualizar: ");
            orden.setId(sc.nextInt());
            sc.nextLine();
            System.out.print("Ingrese el nuevo ID de usuario: ");
            orden.setUsuario_id(sc.nextInt());
            sc.nextLine();
            System.out.print("Ingrese el nuevo ID de producto: ");
            orden.setProducto_id(sc.nextInt());
            sc.nextLine();
            System.out.print("Ingresa la nueva cantidad: ");
            orden.setCantidad(sc.nextInt());
            sc.nextLine();
            OrdenService.actualizarOrden(orden, idsolicitante);
        } catch (Exception e) {
            System.out.println("Error al actualizar orden: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Eliminar orden (solo admin)
    public static void eliminarOrden(int idsolicitante, int rolsolicitante) {
        try {
            if (rolsolicitante != 1) {
                System.out.println("No tienes permiso para eliminar órdenes.");
                return;
            }
            System.out.println("===== ELIMINAR ÓRDENES (ADMIN) =====");
            System.out.print("Ingrese el ID de la orden a eliminar: ");
            int idOrden = sc.nextInt();
            sc.nextLine();
            OrdenService.eliminarOrden(idOrden, idsolicitante);
        } catch (Exception e) {
            System.out.println("Error al eliminar orden: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Consultar órdenes (admin ve todas, cliente solo las suyas)
    public static void consultarOrdenes(int idsolicitante, int rolsolicitante) {
        try {
            ArrayList<Orden> lista = OrdenService.consultarOrdenes(idsolicitante);
            if (lista.isEmpty()) {
                System.out.println("No hay órdenes registradas.");
            } else {
                System.out.println("===== ÓRDENES =====");
                for (Orden o : lista) {
                    System.out.println("ID: " + o.getId() +
                            " | Usuario ID: " + o.getUsuario_id() +
                            " | Producto ID: " + o.getProducto_id() +
                            " | Cantidad: " + o.getCantidad());
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar órdenes: " + e.getMessage());
            sc.nextLine();
        }
    }
}