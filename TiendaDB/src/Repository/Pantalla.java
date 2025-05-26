package Repository;

public class Pantalla {
    public static void limpiarPantalla() throws Exception {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}