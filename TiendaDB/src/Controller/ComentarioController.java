package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Comentario;
import Model.Usuario;
import Service.ComentarioService;

public class ComentarioController {
    static Scanner sc = new Scanner(System.in);

    // ! Insertar comentario (cliente)
    public static void insertarComentario(Usuario usuario) {
        try {
            System.out.println("===== INSERTAR COMENTARIO =====");
            System.out.print("Ingrese el ID del producto: ");
            int productoid = sc.nextInt();
            sc.nextLine();
            System.out.print("Escriba su comentario: ");
            String comentario = sc.nextLine();
            ComentarioService.insertarComentario(productoid, comentario, usuario.getId());
        } catch (Exception e) {
            System.out.println("Error al insertar comentario: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Actualizar comentario (cliente solo los suyos, admin cualquiera)
    public static void actualizarComentario(Usuario usuario) {
        try {
            System.out.println("===== ACTUALIZAR COMENTARIO =====");
            System.out.print("Ingrese el ID del comentario a actualizar: ");
            int comentarioid = sc.nextInt();
            sc.nextLine();
            System.out.print("Ingrese el nuevo texto del comentario: ");
            String nuevocomentario = sc.nextLine();
            ComentarioService.actualizarComentario(comentarioid, nuevocomentario, usuario.getId());
        } catch (Exception e) {
            System.out.println("Error al actualizar comentario: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Eliminar comentario (cliente solo los suyos, admin cualquiera)
    public static void eliminarComentario(Usuario usuario) {
        try {
            System.out.println("===== ELIMINAR COMENTARIO =====");
            System.out.print("Ingrese el ID del comentario a eliminar: ");
            int comentarioid = sc.nextInt();
            sc.nextLine();
            ComentarioService.eliminarComentario(comentarioid, usuario.getId());
        } catch (Exception e) {
            System.out.println("Error al eliminar comentario: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Consultar todos los comentarios (admin y cliente)
    public static void consultarComentarios() {
        try {
            System.out.println("===== LISTA DE COMENTARIOS =====");
            ArrayList<Comentario> comentarios = ComentarioService.consultarComentarios();
            if (comentarios.isEmpty()) {
                System.out.println("No hay comentarios registrados");
            } else {
                for (Comentario c : comentarios) {
                    System.out.println("ID: " + c.getId());
                    System.out.println("Usuario ID: " + c.getUsuario_id());
                    System.out.println("Producto ID: " + c.getProducto_id());
                    System.out.println("Comentario: " + c.getComentario());
                    System.out.println("Fecha: " + c.getFecha());
                    System.out.println("-----------------------------");
                }
            }
        } catch (Exception e) {
            System.out.println("Error al consultar comentarios: " + e.getMessage());
            sc.nextLine();
        }
    }
}