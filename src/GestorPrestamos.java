import java.util.ArrayList;
import java.util.List;

/**
 * Clase GestorPrestamos que gestiona los préstamos de libros utilizando un árbol binario de búsqueda.
 * Permite registrar un préstamo, obtener los préstamos activos, los libros prestados por un usuario
 * y devolver libros.
 */
public class GestorPrestamos {
    private NodoPrestamo raiz;

    /**
     * Constructor de la clase GestorPrestamos.
     * Inicializa el árbol binario de búsqueda vacío.
     */
    public GestorPrestamos() {
        this.raiz = null;
    }

    /**
     * Presta un libro a un usuario.
     * @param usuario El usuario que desea tomar el libro.
     * @param libro El libro que será prestado.
     * @return true si el préstamo fue exitoso.
     */
    public boolean prestarLibro(Usuario usuario, Libro libro) {
        Prestamo prestamo = new Prestamo(usuario, libro);
        raiz = insertar(raiz, prestamo);
        libro.prestar(); // Asegúrate de que el libro esté marcado como prestado.
        return true;
    }

    /**
     * Método recursivo para insertar un préstamo en el árbol binario de búsqueda.
     * Los préstamos se ordenan según el nombre del usuario.
     * @param nodo El nodo actual del árbol.
     * @param prestamo El préstamo que se desea insertar.
     * @return El nodo actualizado.
     */
    private NodoPrestamo insertar(NodoPrestamo nodo, Prestamo prestamo) {
        if (nodo == null) {
            return new NodoPrestamo(prestamo);
        }

        // Compara el nombre del usuario que realiza el préstamo.
        int comparacion = prestamo.getUsuario().getNombre().compareTo(nodo.prestamo.getUsuario().getNombre());
        
        if (comparacion < 0) {
            // Si el nombre del usuario es menor, lo insertamos en el subárbol izquierdo.
            nodo.izquierda = insertar(nodo.izquierda, prestamo);
        } else {
            // Si el nombre del usuario es mayor o igual, lo insertamos en el subárbol derecho.
            nodo.derecha = insertar(nodo.derecha, prestamo);
        }
        
        return nodo;
    }

    /**
     * Obtiene una lista de todos los préstamos activos en el sistema.
     * Realiza un recorrido en orden (in-order traversal) para obtener todos los préstamos.
     * @return Una lista de préstamos activos.
     */
    public List<Prestamo> obtenerPrestamosActivos() {
        List<Prestamo> prestamos = new ArrayList<>();
        obtenerPrestamosInOrden(raiz, prestamos);
        return prestamos;
    }

    /**
     * Método recursivo para realizar un recorrido en orden del árbol y agregar los préstamos a la lista.
     * @param nodo El nodo actual del árbol.
     * @param lista La lista donde se agregarán los préstamos.
     */
    private void obtenerPrestamosInOrden(NodoPrestamo nodo, List<Prestamo> lista) {
        if (nodo != null) {
            // Primero recorre el subárbol izquierdo.
            obtenerPrestamosInOrden(nodo.izquierda, lista);

            // Agrega el préstamo del nodo a la lista.
            lista.add(nodo.prestamo);

            // Luego recorre el subárbol derecho.
            obtenerPrestamosInOrden(nodo.derecha, lista);
        }
    }

    /**
     * Obtiene una lista de libros prestados por un usuario específico.
     * @param usuario El usuario cuyos libros prestados se desean obtener.
     * @return Una lista de libros prestados por el usuario.
     */
    public List<Libro> obtenerLibrosPrestados(Usuario usuario) {
        List<Libro> libros = new ArrayList<>();
        obtenerLibrosUsuario(raiz, usuario, libros);
        return libros;
    }

    /**
     * Método recursivo para obtener los libros prestados por un usuario en particular.
     * @param nodo El nodo actual del árbol.
     * @param usuario El usuario cuyas transacciones de préstamo se desean consultar.
     * @param libros La lista donde se agregarán los libros prestados.
     */
    private void obtenerLibrosUsuario(NodoPrestamo nodo, Usuario usuario, List<Libro> libros) {
        if (nodo != null) {
            // Recorre el subárbol izquierdo.
            obtenerLibrosUsuario(nodo.izquierda, usuario, libros);

            // Si el préstamo corresponde al usuario, agrega el libro a la lista.
            if (nodo.prestamo.getUsuario().getNombre().equals(usuario.getNombre())) {
                libros.add(nodo.prestamo.getLibro());
            }

            // Recorre el subárbol derecho.
            obtenerLibrosUsuario(nodo.derecha, usuario, libros);
        }
    }

    /**
     * Permite devolver un libro prestado por un usuario.
     * @param libro El libro que se va a devolver.
     * @param usuario El usuario que desea devolver el libro.
     * @return true si la devolución fue exitosa, false si no se encontró el préstamo.
     */
    public boolean devolverLibro(Libro libro, Usuario usuario) {
        return devolverLibroRecursivo(raiz, libro, usuario);
    }

    /**
     * Método recursivo para devolver un libro, buscando el préstamo correspondiente.
     * @param nodo El nodo actual del árbol.
     * @param libro El libro que se va a devolver.
     * @param usuario El usuario que devuelve el libro.
     * @return true si el libro fue devuelto, false si no se encontró el préstamo.
     */
    private boolean devolverLibroRecursivo(NodoPrestamo nodo, Libro libro, Usuario usuario) {
        if (nodo == null) return false;

        // Compara si el libro y el usuario corresponden al préstamo.
        if (nodo.prestamo.getLibro().getTitulo().equals(libro.getTitulo())
                && nodo.prestamo.getUsuario().getNombre().equals(usuario.getNombre())) {
            libro.devolver(); // Marca el libro como devuelto.
            return true;
        }

        // Busca en los subárboles izquierdo y derecho.
        return devolverLibroRecursivo(nodo.izquierda, libro, usuario)
            || devolverLibroRecursivo(nodo.derecha, libro, usuario);
    }

    /**
     * Clase interna NodoPrestamo que representa un nodo en el árbol binario.
     * Cada nodo contiene un préstamo y dos referencias a los subárboles izquierdo y derecho.
     */
    private static class NodoPrestamo {
        Prestamo prestamo; // El préstamo que se almacena en el nodo.
        NodoPrestamo izquierda, derecha; // Referencias a los subárboles izquierdo y derecho.

        /**
         * Constructor de NodoPrestamo.
         * @param prestamo El préstamo que se va a almacenar en el nodo.
         */
        NodoPrestamo(Prestamo prestamo) {
            this.prestamo = prestamo;
            this.izquierda = this.derecha = null;
        }
    }
}
