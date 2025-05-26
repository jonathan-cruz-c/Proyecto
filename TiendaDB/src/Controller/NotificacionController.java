package Controller;

import java.util.Scanner;
import java.util.ArrayList;

import Model.Notificacion;
import Model.Usuario;
import Service.NotificacionService;

public class NotificacionController {
    static Scanner sc = new Scanner(System.in);

    // ! Insertar (solo admin)
    public static void solicitarInsertar(Usuario solicitante) {
        try {
            if (solicitante.getRol_id() == 1) {
                System.out.println("=== Insertar Notificación (Admin) ===");
                System.out.print("Ingrese el ID del usuario al que va dirigida: ");
                int usuario_id = sc.nextInt();
                sc.nextLine();
                System.out.print("Ingrese el mensaje: ");
                String mensaje = sc.nextLine();
                Notificacion n = new Notificacion();
                n.setUsuario_id(usuario_id);
                n.setMensaje(mensaje);
                NotificacionService.insertar(n, solicitante.getId());
            } else {
                System.out.println("No tienes permisos para insertar notificaciones");
            }
        } catch (Exception e) {
            System.out.println("Error en solicitarInsertar: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Actualizar (solo admin)
    public static void solicitarActualizar(Usuario solicitante) {
        try {
            if (solicitante.getRol_id() == 1) {
                System.out.println("=== Actualizar Notificación (Admin) ===");
                System.out.print("Ingrese el ID de la notificación a actualizar: ");
                int id = sc.nextInt();
                System.out.print("Ingrese el nuevo ID de usuario: ");
                int usuario_id = sc.nextInt();
                sc.nextLine();
                System.out.print("Ingrese el nuevo mensaje: ");
                String mensaje = sc.nextLine();
                Notificacion n = new Notificacion();
                n.setId(id);
                n.setUsuario_id(usuario_id);
                n.setMensaje(mensaje);
                NotificacionService.actualizar(n, solicitante.getId());
            } else {
                System.out.println("No tienes permisos para actualizar notificaciones.");
            }
        } catch (Exception e) {
            System.out.println("Error en solicitarActualizar: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Eliminar (admin cualquier notificación, cliente solo las suyas)
    public static void solicitarEliminar(Usuario solicitante) {
        try {
            System.out.println("=== Eliminar Notificación ===");
            System.out.print("Ingrese el ID de la notificación a eliminar: ");
            int id = sc.nextInt();
            sc.nextLine();
            NotificacionService.eliminar(id, solicitante.getId());
        } catch (Exception e) {
            System.out.println("Error en solicitarEliminar: " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Consultar (admin todas, cliente solo las suyas)
    public static void solicitarConsulta(Usuario solicitante) {
        try {
            System.out.println("=== Consultar Notificaciones ===");
            ArrayList<Notificacion> lista = NotificacionService.consultar(solicitante.getId());
            if (lista.isEmpty()) {
                System.out.println("No hay notificaciones");
            } else {
                for (Notificacion n : lista) {
                    System.out.println("----------------------------");
                    System.out.println("ID: " + n.getId());
                    System.out.println("Usuario ID: " + n.getUsuario_id());
                    System.out.println("Mensaje: " + n.getMensaje());
                    System.out.println("Fecha: " + n.getFecha());
                }
            }
        } catch (Exception e) {
            System.out.println("Error en solicitarConsulta: " + e.getMessage());
        }
    }
}