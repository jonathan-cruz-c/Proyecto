package UI;

import java.util.Scanner;

import Controller.ProductoController;
import Model.Usuario;
import Repository.Pantalla;

public class ProductoMenu {
    static Scanner sc = new Scanner(System.in);

    public static void mostrarMenuProductos(Usuario usuario) {
        int op = 0;
        do {
            try {
                if (usuario.getRol_id() == 1) { // ADMIN
                    System.out.println("===== MENÚ DE PRODUCTOS (ADMIN) =====");
                    System.out.println("1. Insertar producto");
                    System.out.println("2. Actualizar producto");
                    System.out.println("3. Eliminar producto");
                    System.out.println("4. Consultar productos");
                    System.out.println("5. Regresar");
                } else { // CLIENTE
                    System.out.println("===== MENÚ DE PRODUCTOS =====");
                    System.out.println("1. Consultar productos");
                    System.out.println("2. Regresar");
                }
                System.out.print("Selecciona una opción (número): ");
                op = sc.nextInt();
                sc.nextLine();
                Pantalla.limpiarPantalla();
                if (usuario.getRol_id() == 1) { // ADMIN
                    switch (op) {
                        case 1:
                            Pantalla.limpiarPantalla();
                            ProductoController.insertarProducto(usuario.getId(), usuario.getRol_id());
                            break;
                        case 2:
                            Pantalla.limpiarPantalla();
                            ProductoController.actualizarProducto(usuario.getId(), usuario.getRol_id());
                            break;
                        case 3:
                            Pantalla.limpiarPantalla();
                            ProductoController.eliminarProducto(usuario.getId(), usuario.getRol_id());
                            break;
                        case 4:
                            Pantalla.limpiarPantalla();
                            ProductoController.consultarProductos();
                            break;
                        case 5:
                            Pantalla.limpiarPantalla();
                            System.out.println("Regresando...");
                            break;
                        default:
                            Pantalla.limpiarPantalla();
                            System.out.println("Opción inválida");
                    }
                } else { // CLIENTE
                    switch (op) {
                        case 1:
                            Pantalla.limpiarPantalla();
                            ProductoController.consultarProductos();
                            break;
                        case 2:
                            Pantalla.limpiarPantalla();
                            System.out.println("Regresando...");
                            break;
                        default:
                            Pantalla.limpiarPantalla();
                            System.out.println("Opción inválida");
                    }
                }
                System.out.println();
            } catch (Exception e) {
                System.out.println("Error en el menú de productos: " + e.getMessage());
                sc.nextLine();
            }
        } while ((usuario.getRol_id() == 1 && op != 5) || (usuario.getRol_id() != 1 && op != 2));
    }
}