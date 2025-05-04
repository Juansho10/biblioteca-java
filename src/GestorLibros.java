import java.util.ArrayList;
import java.util.List;

/**
 * Clase GestorLibros que gestiona la colección de libros utilizando un árbol binario de búsqueda.
 * Permite agregar, buscar y obtener libros disponibles.
 */
public class GestorLibros {
    private NodoLibro raiz;

    /**
     * Constructor de la clase GestorLibros.
     * Inicializa el árbol binario de búsqueda vacío.
     */
    public GestorLibros() {
        this.raiz = null;
    }

    /**
     * Agrega un nuevo libro al árbol binario de búsqueda.
     * @param libro El libro que se desea agregar.
     */
    public void agregarLibro(Libro libro) {
        raiz = insertar(raiz, libro);
    }

    /**
     * Método recursivo para insertar un libro en el árbol binario de búsqueda.
     * @param nodo El nodo actual del árbol.
     * @param libro El libro a insertar.
     * @return El nodo actualizado.
     */
    private NodoLibro insertar(NodoLibro nodo, Libro libro) {
        if (nodo == null) {
            return new NodoLibro(libro);
        }

        // Compara el título del libro con el nodo actual.
        int comparacion = libro.getTitulo().compareTo(nodo.libro.getTitulo());
        
        if (comparacion < 0) {
            // Si el título del libro es menor, lo insertamos en el subárbol izquierdo.
            nodo.izquierda = insertar(nodo.izquierda, libro);
        } else if (comparacion > 0) {
            // Si el título del libro es mayor, lo insertamos en el subárbol derecho.
            nodo.derecha = insertar(nodo.derecha, libro);
        }
        
        return nodo;
    }

    /**
     * Busca un libro en el árbol binario de búsqueda por su título.
     * @param titulo El título del libro a buscar.
     * @return El libro encontrado, o null si no existe.
     */
    public Libro buscarLibro(String titulo) {
        return buscar(raiz, titulo);
    }

    /**
     * Método recursivo para buscar un libro por su título.
     * @param nodo El nodo actual del árbol.
     * @param titulo El título del libro a buscar.
     * @return El libro encontrado, o null si no existe.
     */
    private Libro buscar(NodoLibro nodo, String titulo) {
        if (nodo == null) return null;

        int comparacion = titulo.compareTo(nodo.libro.getTitulo());
        if (comparacion == 0) {
            return nodo.libro; // Si se encuentra el libro, lo retorna.
        } else if (comparacion < 0) {
            // Si el título es menor, busca en el subárbol izquierdo.
            return buscar(nodo.izquierda, titulo);
        } else {
            // Si el título es mayor, busca en el subárbol derecho.
            return buscar(nodo.derecha, titulo);
        }
    }

    /**
     * Obtiene una lista de libros disponibles en el sistema.
     * Realiza un recorrido en orden (in-order traversal) para listar los libros disponibles.
     * @return Una lista de libros disponibles.
     */
    public List<Libro> obtenerLibrosDisponibles() {
        List<Libro> libros = new ArrayList<>();
        obtenerLibrosDisponiblesInOrden(raiz, libros);
        return libros;
    }

    /**
     * Método recursivo para realizar un recorrido en orden del árbol y obtener los libros disponibles.
     * @param nodo El nodo actual del árbol.
     * @param libros La lista donde se agregarán los libros disponibles.
     */
    private void obtenerLibrosDisponiblesInOrden(NodoLibro nodo, List<Libro> libros) {
        if (nodo != null) {
            // Primero recorre el subárbol izquierdo.
            obtenerLibrosDisponiblesInOrden(nodo.izquierda, libros);

            // Si el libro está disponible, lo agrega a la lista.
            if (nodo.libro.isDisponible()) {
                libros.add(nodo.libro);
            }

            // Luego recorre el subárbol derecho.
            obtenerLibrosDisponiblesInOrden(nodo.derecha, libros);
        }
    }

    /**
     * Clase interna NodoLibro que representa un nodo en el árbol binario.
     * Cada nodo contiene un libro y dos referencias a los subárboles izquierdo y derecho.
     */
    private static class NodoLibro {
        Libro libro; // El libro que se almacena en el nodo.
        NodoLibro izquierda, derecha; // Referencias a los subárboles izquierdo y derecho.

        /**
         * Constructor de NodoLibro.
         * @param libro El libro que se va a almacenar en el nodo.
         */
        NodoLibro(Libro libro) {
            this.libro = libro;
            this.izquierda = this.derecha = null;
        }
    }
}
