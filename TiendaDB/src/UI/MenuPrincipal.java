package UI;

import java.util.Scanner;

import Model.Usuario;
import Repository.Pantalla;

public class MenuPrincipal {
    static Scanner sc = new Scanner(System.in);

    public static void mostrarMenuPrincipal(Usuario usuario) {
        int op = 0;
        int rol = usuario.getRol_id();
        do {
            try {
                if (rol == 1) {
                    System.out.println("===== MENÚ PRINCIPAL ADMINISTRADOR =====");
                    System.out.println("1. Gestión de Roles");
                    System.out.println("2. Gestión de Usuarios");
                    System.out.println("3. Gestión de Categorías");
                    System.out.println("4. Gestión de Direcciones");
                    System.out.println("5. Gestión de Productos");
                    System.out.println("6. Gestión de Órdenes");
                    System.out.println("7. Gestión de Pagos");
                    System.out.println("8. Gestión de Detalles de Órdenes");
                    System.out.println("9. Gestión de Carritos");
                    System.out.println("10. Gestión de Comentarios");
                    System.out.println("11. Gestión de Inventario");
                    System.out.println("12. Gestión de Favoritos");
                    System.out.println("13. Gestión de Notificaciones");
                    System.out.println("14. Cerrar sesión");
                } else {
                    System.out.println("===== MENÚ PRINCIPAL CLIENTE =====");
                    System.out.println("1. Perfil de Usuario");
                    System.out.println("2. Ver Categorías");
                    System.out.println("3. Direcciones");
                    System.out.println("4. Productos");
                    System.out.println("5. Órdenes");
                    System.out.println("6. Pagos");
                    System.out.println("7. Detalles de Órdenes");
                    System.out.println("8. Carrito");
                    System.out.println("9. Comentarios");
                    System.out.println("10. Favoritos");
                    System.out.println("11. Notificaciones");
                    System.out.println("12. Cerrar sesión");
                }
                System.out.print("Seleccione una opción (número): ");
                op = sc.nextInt();
                sc.nextLine();
                Pantalla.limpiarPantalla();
                if (rol == 1) {
                    switch (op) {
                        case 1:
                            Pantalla.limpiarPantalla();
                            RolMenu.mostrarMenuRol(usuario);
                            break;
                        case 2:
                            Pantalla.limpiarPantalla();
                            boolean cerrarsesion = UsuarioMenu.mostrarMenuUsuario(usuario);
                            if (cerrarsesion) {
                                op=14;
                            }
                            break;
                        case 3:
                            Pantalla.limpiarPantalla();
                            CategoriaMenu.mostrarMenuCategorias(usuario);
                            break;
                        case 4:
                            Pantalla.limpiarPantalla();
                            DireccionMenu.mostrarMenuDirecciones(usuario);
                            break;
                        case 5:
                            Pantalla.limpiarPantalla();
                            ProductoMenu.mostrarMenuProductos(usuario);
                            break;
                        case 6:
                            Pantalla.limpiarPantalla();
                            OrdenMenu.mostrarMenuOrden(usuario);
                            break;
                        case 7:
                            Pantalla.limpiarPantalla();
                            PagoMenu.mostrarMenuPago(usuario);
                            break;
                        case 8:
                            Pantalla.limpiarPantalla();
                            DetalleOrdenMenu.mostrarMenuDetalleOrden(usuario);
                            break;
                        case 9:
                            Pantalla.limpiarPantalla();
                            CarritoMenu.mostrarCarritoMenu(usuario);
                            break;
                        case 10:
                            Pantalla.limpiarPantalla();
                            ComentarioMenu.mostrarComentarioMenu(usuario);
                            break;
                        case 11:
                            Pantalla.limpiarPantalla();
                            InventarioMenu.mostrarInventarioMenu(usuario);
                            break;
                        case 12:
                            Pantalla.limpiarPantalla();
                            FavoritoMenu.mostrarFavoritoMenu(usuario);
                            break;
                        case 13:
                            Pantalla.limpiarPantalla();
                            NotificacionMenu.mostrarNotificacionMenu(usuario);
                            break;
                        case 14:
                            Pantalla.limpiarPantalla();
                            System.out.println("Cerrando sesión...");
                            break;
                        default:
                            Pantalla.limpiarPantalla();
                            System.out.println("Opción inválida.");
                    }
                } else {
                    switch (op) {
                        case 1:
                            Pantalla.limpiarPantalla();
                            boolean cerrarsesion = UsuarioMenu.mostrarMenuUsuario(usuario);
                            if (cerrarsesion) {
                                op=12;
                            }
                            break;
                        case 2:
                            Pantalla.limpiarPantalla();
                            CategoriaMenu.mostrarMenuCategorias(usuario);
                            break;
                        case 3:
                            Pantalla.limpiarPantalla();
                            DireccionMenu.mostrarMenuDirecciones(usuario);
                            break;
                        case 4:
                            Pantalla.limpiarPantalla();
                            ProductoMenu.mostrarMenuProductos(usuario);
                            break;
                        case 5:
                            Pantalla.limpiarPantalla();
                            OrdenMenu.mostrarMenuOrden(usuario);
                            break;
                        case 6:
                            Pantalla.limpiarPantalla();
                            PagoMenu.mostrarMenuPago(usuario);
                            break;
                        case 7:
                            Pantalla.limpiarPantalla();
                            DetalleOrdenMenu.mostrarMenuDetalleOrden(usuario);
                            break;
                        case 8:
                            Pantalla.limpiarPantalla();
                            CarritoMenu.mostrarCarritoMenu(usuario);
                            break;
                        case 9:
                            Pantalla.limpiarPantalla();
                            ComentarioMenu.mostrarComentarioMenu(usuario);
                            break;
                        case 10:
                            Pantalla.limpiarPantalla();
                            FavoritoMenu.mostrarFavoritoMenu(usuario);
                            break;
                        case 11:
                            Pantalla.limpiarPantalla();
                            NotificacionMenu.mostrarNotificacionMenu(usuario);
                            break;
                        case 12:
                            Pantalla.limpiarPantalla();
                            System.out.println("Cerrando sesión...");
                            break;
                        default:
                            Pantalla.limpiarPantalla();
                            System.out.println("Opción inválida.");
                    }
                }
                System.out.println("\nPresiona Enter para continuar...");
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("Error en el menú principal: " + e.getMessage());
                sc.nextLine();
            }
        } while ((rol == 1 && op != 14) || (rol != 1 && op != 12));
    }
}