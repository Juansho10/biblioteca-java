/**
 * Representa un usuario del sistema, que puede ser administrador o usuario normal.
 */
public class Usuario {
    private String nombre;
    private String username;
    private String password;
    private boolean esAdmin;

    /**
     * Constructor para crear un nuevo usuario.
     *
     * @param nombre Nombre del usuario.
     * @param username Nombre de usuario (login).
     * @param password Contraseña del usuario.
     * @param esAdmin Indica si el usuario tiene privilegios de administrador.
     */
    public Usuario(String nombre, String username, String password, boolean esAdmin) {
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.esAdmin = esAdmin;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public boolean esAdmin() { return esAdmin; }
    public String getNombre() { return nombre; }

    /**
     * Representación en texto del usuario, indicando si es admin o usuario normal.
     */
    @Override
    public String toString() {
        return nombre + " (" + (esAdmin ? "Admin" : "Usuario") + ")";
    }
}

