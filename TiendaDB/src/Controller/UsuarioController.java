package Controller;

import java.util.ArrayList;
import java.util.Scanner;

import Model.Usuario;
import Service.UsuariosService;

public class UsuarioController {
    static Scanner sc = new Scanner(System.in);

    // ! Insertar Usuario (Admin puede crear admins y clientes, cliente solo crea
    // cliente)
    public static void insertarUsuario(int idsolicitante) {
        try {
            System.out.println("===== REGISTRO DE USUARIO =====");
            System.out.print("Ingresa tu nombre(s): ");
            String nombre = sc.nextLine();
            System.out.print("Ingresa tu apellido(s): ");
            String apellido = sc.nextLine();
            System.out.print("Ingresa un nombre de usuario: ");
            String username = sc.nextLine();
            System.out.print("Ingresa un Correo: ");
            String correo = sc.nextLine();
            System.out.print("Ingresa una Contraseña: ");
            String contraseña = sc.nextLine();
            System.out.print("Ingresa tu numero de telefono: ");
            String telefono = sc.nextLine();
            int rol_id = 2; // Por defecto cliente
            if (idsolicitante == 1) {
                System.out.print("Ingresa el rol (1=Admin, 2=Cliente): ");
                rol_id = sc.nextInt();
                sc.nextLine(); // limpiar salto
            }
            Usuario nuevo = new Usuario();
            nuevo.setNombre(nombre);
            nuevo.setApellido(apellido);
            nuevo.setUsername(username);
            nuevo.setCorreo(correo);
            nuevo.setContraseña(contraseña);
            nuevo.setTelefono(telefono);
            nuevo.setRol_id(rol_id);
            UsuariosService.insertarUsuario(nuevo, idsolicitante);
        } catch (Exception e) {
            System.out.println("Error en UsuarioController (insertar): " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Actualizar Usuario (admin puede editar a otros, cliente solo a sí mismo)
    public static void actualizarUsuario(int idsolicitante) {
        try {
            System.out.println("===== ACTUALIZAR USUARIO =====");
            int idactualizar = idsolicitante;
            if (idsolicitante == 1) {
                System.out.print("Ingrese el ID del usuario a actualizar: ");
                idactualizar = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.print("Ingresa tu id para actualizar : ");
                idactualizar = sc.nextInt();
                sc.nextLine();
            }
            System.out.print("Ingresa el nuevo nombre(s): ");
            String nombre = sc.nextLine();
            System.out.print("Ingresa el nuevo apellido(s): ");
            String apellido = sc.nextLine();
            System.out.print("Ingresa el nuevo nombre de usuario: ");
            String username = sc.nextLine();
            System.out.print("Ingresa el nuevo correo: ");
            String correo = sc.nextLine();
            System.out.print("Ingresa la nueva contraseña: ");
            String contraseña = sc.nextLine();
            System.out.print("Ingresa el nuevo numero de telefono: ");
            String telefono = sc.nextLine();
            int rol_id = 2;
            if (idsolicitante == 1) {
                System.out.print("Ingresa el nuevo rol (1=Admin, 2=Cliente): ");
                rol_id = sc.nextInt();
                sc.nextLine();
            }
            Usuario actualizar = new Usuario();
            actualizar.setId(idactualizar);
            actualizar.setNombre(nombre);
            actualizar.setApellido(apellido);
            actualizar.setUsername(username);
            actualizar.setCorreo(correo);
            actualizar.setContraseña(contraseña);
            actualizar.setTelefono(telefono);
            actualizar.setRol_id(rol_id);
            UsuariosService.actualizarUsuario(actualizar, idactualizar, idsolicitante);
        } catch (Exception e) {
            System.out.println("Error en UsuarioController (actualizar): " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Eliminar Usuario (admin puede eliminar a cualquiera, cliente solo a sí
    // ! mismo)
    public static void eliminarUsuario(int idsolicitante) {
        try {
            System.out.println("===== ELIMINAR USUARIO =====");
            int ideliminar = idsolicitante;
            if (idsolicitante == 1) {
                System.out.print("Ingrese el ID del usuario a eliminar: ");
                ideliminar = sc.nextInt();
                sc.nextLine();
            }
            UsuariosService.eliminarUsuario(ideliminar, idsolicitante);
        } catch (Exception e) {
            System.out.println("Error en UsuarioController (eliminar): " + e.getMessage());
            sc.nextLine();
        }
    }

    // ! Consultar usuarios (admin ve todos, cliente solo su perfil)
    public static void consultarUsuarios(int idsolicitante) {
        try {
            System.out.println("===== CONSULTAR USUARIOS =====");
            ArrayList<Usuario> lista = UsuariosService.consultarUsuarios(idsolicitante);
            for (Usuario u : lista) {
                System.out.println("--------------------------------");
                System.out.println("ID: " + u.getId());
                System.out.println("Rol: " + (u.getRol_id() == 1 ? "Admin" : "Cliente"));
                System.out.println("Nombre de usuario: " + u.getUsername());
                System.out.println("Nombre(s): " + u.getNombre());
                System.out.println("Apellido(s): " + u.getApellido());
                System.out.println("Correo: " + u.getCorreo());
                System.out.println("Telefono: " + u.getTelefono());
            }
        } catch (Exception e) {
            System.out.println("Error en UsuarioController (consultar): " + e.getMessage());
            sc.nextLine();
        }
    }
}