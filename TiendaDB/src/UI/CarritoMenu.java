package UI;

import java.util.Scanner;

import Controller.CarritoController;
import Model.Usuario;
import Repository.Pantalla;

public class CarritoMenu {
    static Scanner sc = new Scanner(System.in);

    public static void mostrarCarritoMenu(Usuario usuarioactual) {
        int op = 0;
        int id = usuarioactual.getId();
        int rol = usuarioactual.getRol_id();
        do {
            try {
                if (rol == 1) {
                    System.out.println("===== MENÚ DE CARRITO (ADMIN) =====");
                } else {
                    System.out.println("===== MENÚ DE CARRITO =====");
                }
                System.out.print("1. Insertar carrito");
                System.out.println("\tNota: se necesitas el id del producto");
                System.out.print("2. Actualizar carrito");
                System.out.println("\tNota: se necesitas el id del carrito a actualizar y el nuevo id del producto");
                System.out.print("3. Eliminar carrito");
                System.out.println("\tNota: se necesitas el id del carrito a eliminar");
                System.out.println("4. Consultar carritos");
                System.out.println("5. Regresar");
                System.out.print("Seleccione una opción: (número) ");
                op = sc.nextInt();
                sc.nextLine();
                Pantalla.limpiarPantalla();
                switch (op) {
                    case 1:
                        Pantalla.limpiarPantalla();
                        CarritoController.insertarCarrito(id, rol);
                        break;
                    case 2:
                        Pantalla.limpiarPantalla();
                        CarritoController.actualizarCarrito(id, rol);
                        break;
                    case 3:
                        Pantalla.limpiarPantalla();
                        CarritoController.eliminarCarrito(id);
                        break;
                    case 4:
                        Pantalla.limpiarPantalla();
                        CarritoController.consultarCarritos(id, rol);
                        break;
                    case 5:
                        Pantalla.limpiarPantalla();
                        System.out.println("Regresando...");
                        break;
                    default:
                        Pantalla.limpiarPantalla();
                        System.out.println("Opción inválida. Intente nuevamente");
                }
                System.out.println(); // Espacio entre operaciones
            } catch (Exception e) {
                System.out.println("Error en el menú de carrito: " + e.getMessage());
                sc.nextLine();
            }
        } while (op != 5);
    }
}