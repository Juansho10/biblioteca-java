/**
 * Clase principal que inicia el sistema de gestión de biblioteca.
 * 
 * Se encarga de crear el gestor de usuarios y abrir la ventana de login.
 */
public class Main {
    public static void main(String[] args) {
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        new VentanaLogin(gestorUsuarios);
    }
}


