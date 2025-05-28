package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Inventario;
import Model.Usuario;
import Service.InventarioService;

public class InventarioController {
    static Scanner sc = new Scanner(System.in);

    // ! Insertar inventario (solo admin)
    public static void insertarInventario(Usuario usuario) {
        try {
            if (usuario.getRol_id() == 1) {
                System.out.println("===== INSERTAR INVENTARIO =====");
                System.out.print("Ingrese el ID del producto: ");
                int producto_id = sc.nextInt();
                sc.nextLine();
                System.out.print("Ingrese la cantidad: ");
                int cantidad = sc.nextInt();
                sc.nextLine();
                Inventario inventario = new Inventario();
                inventario.setProducto_id(producto_id);
                inventario.setCantidad(cantidad);
                InventarioService.insertarInventario(inventario, usuario.getId());
            } else {
                System.out.println("Acceso denegado. Solo los administradores pueden insertar inventario");
            }
        } catch (Exception e) {
            System.out.println("Error en InventarioController.insertarInventario: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Actualizar inventario (solo admin)
    public static void actualizarInventario(Usuario usuario) {
        try {
            if (usuario.getRol_id() == 1) {
                System.out.println("===== ACTUALIZAR INVENTARIO =====");
                System.out.print("Ingrese el ID del inventario a actualizar: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Ingrese el nuevo ID del producto: ");
                int producto_id = sc.nextInt();
                sc.nextLine();
                System.out.print("Ingrese la nueva cantidad: ");
                int cantidad = sc.nextInt();
                sc.nextLine();
                Inventario inventario = new Inventario();
                inventario.setId(id);
                inventario.setProducto_id(producto_id);
                inventario.setCantidad(cantidad);
                InventarioService.actualizarInventario(inventario, usuario.getId());
            } else {
                System.out.println("Acceso denegado. Solo los administradores pueden actualizar inventario");
            }
        } catch (Exception e) {
            System.out.println("Error en InventarioController.actualizarInventario: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Eliminar inventario (solo admin)
    public static void eliminarInventario(Usuario usuario) {
        try {
            if (usuario.getRol_id() == 1) {
                System.out.println("===== ELIMINAR INVENTARIO =====");
                System.out.print("Ingrese el ID del inventario a eliminar: ");
                int id = sc.nextInt();
                sc.nextLine();
                InventarioService.eliminarInventario(id, usuario.getId());
            } else {
                System.out.println("Acceso denegado. Solo los administradores pueden eliminar inventario");
            }
        } catch (Exception e) {
            System.out.println("Error en InventarioController.eliminarInventario: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Consultar inventario (solo admin)
    public static void consultarInventarios(Usuario usuario) {
        try {
            if (usuario.getRol_id() == 1) {
                System.out.println("===== LISTA DE INVENTARIOS =====");
                ArrayList<Inventario> inventarios = InventarioService.consultarInventarios(usuario.getId());
                for (Inventario inv : inventarios) {
                    System.out.println("ID: " + inv.getId() +
                            " | Producto ID: " + inv.getProducto_id() +
                            " | Cantidad: " + inv.getCantidad());
                }
            } else {
                System.out.println("Acceso denegado. Solo los administradores pueden consultar inventario");
            }
        } catch (Exception e) {
            System.out.println("Error en InventarioController.consultarInventarios: " + e.getMessage());
            sc.nextLine();
        }
    }
}