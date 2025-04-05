import java.util.ArrayList;

/**
 * Clase encargada de gestionar los usuarios del sistema.
 * Utiliza un ArrayList como estructura de datos para almacenar los usuarios.
 */
public class GestorUsuarios {
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    /**
     * Constructor que inicializa el sistema con un usuario administrador por defecto.
     */
    public GestorUsuarios() {
        usuarios.add(new Usuario("Admin", "admin", "admin123", true));
    }

    /**
     * Registra un nuevo usuario si el nombre de usuario no existe previamente.
     *
     * @param nombre Nombre completo del usuario.
     * @param username Nombre de usuario único.
     * @param password Contraseña del usuario.
     * @return true si el registro fue exitoso, false si el username ya existe.
     */
    public boolean registrarUsuario(String nombre, String username, String password) {
        if (buscarPorUsername(username) != null) return false;

        usuarios.add(new Usuario(nombre, username, password, false));
        return true;
    }

    /**
     * Verifica las credenciales de inicio de sesión.
     *
     * @param username Nombre de usuario.
     * @param password Contraseña.
     * @return Usuario si las credenciales son correctas, null en caso contrario.
     */
    public Usuario login(String username, String password) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username Nombre de usuario a buscar.
     * @return Usuario si se encuentra, null si no existe.
     */
    public Usuario buscarPorUsername(String username) {
        for (Usuario u : usuarios) {
            if (u.getUsername().equals(username)) return u;
        }
        return null;
    }

    /**
     * Retorna la lista completa de usuarios registrados.
     *
     * @return Lista de usuarios.
     */
    public ArrayList<Usuario> listarUsuarios() {
        return usuarios;
    }
}

