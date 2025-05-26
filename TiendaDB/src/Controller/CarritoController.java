package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Carrito;
import Service.CarritoService;

public class CarritoController {
    static Scanner sc = new Scanner(System.in);

    // ! Insertar Carrito
    public static void insertarCarrito(int idsolicitante, int rolsolicitante) {
        try {
            int productoid;
            int usuarioid;
            if (rolsolicitante == 1) {
                System.out.println("=== INSERTAR CARRITOS (ADMIN) ===");
                System.out.print("Ingrese el ID del producto a agregar: ");
                productoid = sc.nextInt();
                System.out.print("Ingrese el ID del usuario al que se agregará el producto: ");
                usuarioid = sc.nextInt();
            } else {
                System.out.println("=== INSERTAR CARRITOS ===");
                System.out.print("Ingrese el ID del producto a agregar a tu carrito: ");
                productoid = sc.nextInt();
                usuarioid = idsolicitante;
            }
            CarritoService.insertarCarrito(productoid, usuarioid, idsolicitante);
        } catch (Exception e) {
            System.out.println("Error en CarritoController al insertar: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Actualizar Carrito
    public static void actualizarCarrito(int idsolicitante, int rolsolicitante) {
        try {
            Carrito carrito = new Carrito();
            if (rolsolicitante == 1) {
                System.out.println("=== ACTUALIZAR CARRITOS (ADMIN) ===");
                System.out.print("Ingrese el ID del carrito a actualizar: ");
                carrito.setId(sc.nextInt());
                System.out.print("Ingresa el nuevo ID de usuario: ");
                carrito.setUsuario_id(sc.nextInt());
                System.out.print("Ingrese el nuevo ID de producto: ");
                carrito.setProducto_id(sc.nextInt());
            } else {
                System.out.println("=== ACTUALIZAR CARRITOS ===");
                System.out.print("Ingrese el ID del carrito a actualizar: ");
                carrito.setId(sc.nextInt());
                carrito.setUsuario_id(idsolicitante);
                System.out.print("Ingrese el nuevo ID de producto para tu carrito: ");
                carrito.setProducto_id(sc.nextInt());
            }
            CarritoService.actualizarCarrito(carrito, idsolicitante);
        } catch (Exception e) {
            System.out.println("Error en CarritoController al actualizar: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Eliminar Carrito
    public static void eliminarCarrito(int idsolicitante) {
        try {
            System.out.println("=== ELIMINAR CARRITOS ===");
            System.out.print("Ingrese el ID del carrito a eliminar: ");
            int carritoid = sc.nextInt();
            CarritoService.eliminarCarrito(carritoid, idsolicitante);
        } catch (Exception e) {
            System.out.println("Error en CarritoController al eliminar: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Consultar Carritos
    public static void consultarCarritos(int idsolicitante, int rolsolicitante) {
        try {
            ArrayList<Carrito> lista = CarritoService.consultarCarritos(idsolicitante);
            if (lista.isEmpty()) {
                System.out.println("No hay carritos para mostrar");
            } else {
                System.out.println("=== Lista de Carritos ===");
                for (Carrito carrito : lista) {
                    System.out.println("ID: " + carrito.getId()
                            + " | Usuario ID: " + carrito.getUsuario_id()
                            + " | Producto ID: " + carrito.getProducto_id());
                }
            }
        } catch (Exception e) {
            System.out.println("Error en CarritoController al consultar: " + e.getMessage());
        }
    }
}