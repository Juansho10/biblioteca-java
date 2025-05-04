import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {

    // Nodo raíz del árbol binario de usuarios
    private NodoUsuario raiz;

    // Constructor que inicializa la raíz como null
    public GestorUsuarios() {
        this.raiz = null;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * @param nombre Nombre completo del usuario
     * @param usuario Nombre de usuario
     * @param password Contraseña del usuario
     * @return true si el registro del usuario fue exitoso
     */
    public boolean registrarUsuario(String nombre, String usuario, String password) {
        // Crea un nuevo objeto de tipo Usuario
        Usuario nuevoUsuario = new Usuario(nombre, usuario, password, false);

        // Inserta el usuario en el árbol binario de búsqueda
        raiz = insertar(raiz, nuevoUsuario);
        return true; // Suponemos que no hay duplicados para simplificar
    }

    /**
     * Inserta un nuevo usuario en el árbol binario de búsqueda.
     * @param nodo Nodo actual en el árbol
     * @param usuario El usuario que se va a insertar
     * @return El nodo actualizado con el nuevo usuario insertado
     */
    private NodoUsuario insertar(NodoUsuario nodo, Usuario usuario) {
        // Si el nodo es null, significa que encontramos una posición vacía, se crea un nuevo nodo
        if (nodo == null) {
            return new NodoUsuario(usuario);
        }

        // Comparación del nombre de usuario con el nodo actual
        int comparacion = usuario.getNombre().compareTo(nodo.usuario.getNombre());

        // Si el nombre del usuario es menor que el del nodo actual, insertamos en el subárbol izquierdo
        if (comparacion < 0) {
            nodo.izquierda = insertar(nodo.izquierda, usuario);
        } else if (comparacion > 0) {
            // Si es mayor, insertamos en el subárbol derecho
            nodo.derecha = insertar(nodo.derecha, usuario);
        }

        return nodo;
    }

    /**
     * Permite a un usuario iniciar sesión verificando sus credenciales.
     * @param usuario Nombre de usuario para la autenticación
     * @param password Contraseña del usuario
     * @return El objeto Usuario si las credenciales son correctas, o null si no se encuentra
     */
    public Usuario login(String usuario, String password) {
        // Busca el usuario en el árbol para verificar las credenciales
        return buscarUsuario(raiz, usuario, password);
    }

    /**
     * Método recursivo para buscar un usuario en el árbol binario de búsqueda.
     * @param nodo Nodo actual en el árbol
     * @param usuario Nombre de usuario que se busca
     * @param password Contraseña que se busca
     * @return El usuario si se encuentra, o null si no se encuentra
     */
    private Usuario buscarUsuario(NodoUsuario nodo, String usuario, String password) {
        // Si el nodo es null, no se encuentra el usuario
        if (nodo == null) return null;

        // Verifica si el nombre de usuario y la contraseña coinciden
        if (usuario.equals(nodo.usuario.getNombre()) && password.equals(nodo.usuario.getPassword())) {
            return nodo.usuario;
        }

        // Realiza la búsqueda recursiva en el subárbol izquierdo o derecho según la comparación
        int comparacion = usuario.compareTo(nodo.usuario.getNombre());
        if (comparacion < 0) {
            return buscarUsuario(nodo.izquierda, usuario, password);
        } else {
            return buscarUsuario(nodo.derecha, usuario, password);
        }
    }

    /**
     * Lista todos los usuarios registrados en el sistema.
     * @return Lista de usuarios ordenada
     */
    public List<Usuario> listarUsuarios() {
        // Crea una lista para almacenar los usuarios
        List<Usuario> usuarios = new ArrayList<>();
        
        // Realiza un recorrido en orden para obtener todos los usuarios registrados
        listarUsuariosInOrden(raiz, usuarios);
        return usuarios;
    }

    /**
     * Realiza un recorrido en orden (in-order traversal) para obtener los usuarios del árbol.
     * @param nodo Nodo actual en el árbol de usuarios
     * @param usuarios Lista donde se agregarán los usuarios
     */
    private void listarUsuariosInOrden(NodoUsuario nodo, List<Usuario> usuarios) {
        // Si el nodo no es null, realiza un recorrido en orden
        if (nodo != null) {
            listarUsuariosInOrden(nodo.izquierda, usuarios);  // Recorrer subárbol izquierdo
            usuarios.add(nodo.usuario);                        // Agregar el usuario al resultado
            listarUsuariosInOrden(nodo.derecha, usuarios);   // Recorrer subárbol derecho
        }
    }

    // Clase interna NodoUsuario que representa un nodo en el árbol de usuarios
    private static class NodoUsuario {
        Usuario usuario;
        NodoUsuario izquierda, derecha;

        // Constructor que inicializa el nodo con un usuario
        NodoUsuario(Usuario usuario) {
            this.usuario = usuario;
            this.izquierda = this.derecha = null;
        }
    }
}
