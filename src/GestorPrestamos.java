import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de gestionar los préstamos de libros y registrar las relaciones
 * entre usuarios y libros mediante un grafo.
 */
public class GestorPrestamos {
    private List<Prestamo> prestamos = new ArrayList<>();
    private GrafoBiblioteca grafo = new GrafoBiblioteca();

    /**
     * Registra un préstamo si el libro está disponible, y agrega la relación al grafo.
     *
     * @param usuario Usuario que solicita el préstamo.
     * @param libro Libro que desea prestar.
     * @return true si el préstamo fue exitoso, false si el libro ya está prestado.
     */
    public boolean prestarLibro(Usuario usuario, Libro libro) {
        if (!libro.estaDisponible()) return false;

        libro.prestar();
        prestamos.add(new Prestamo(usuario, libro));

        NodoGrafo nodoUsuario = new NodoGrafo(usuario);
        NodoGrafo nodoLibro = new NodoGrafo(libro);
        grafo.agregarArista(nodoUsuario, nodoLibro);

        return true;
    }

    /**
     * Devuelve un libro si el préstamo existe y lo elimina de la lista de préstamos.
     *
     * @param libro Libro a devolver.
     * @param usuario Usuario que devuelve el libro.
     * @return true si se procesó correctamente, false si no se encontró el préstamo.
     */
    public boolean devolverLibro(Libro libro, Usuario usuario) {
        for (Prestamo p : prestamos) {
            if (p.getUsuario().equals(usuario) && p.getLibro().equals(libro)) {
                libro.devolver();
                prestamos.remove(p);
                // No removemos del grafo para mantener el historial
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene la lista de libros prestados por un usuario.
     *
     * @param usuario Usuario a consultar.
     * @return Lista de libros prestados por ese usuario.
     */
    public List<Libro> obtenerLibrosPrestados(Usuario usuario) {
        List<Libro> libros = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (p.getUsuario().equals(usuario)) {
                libros.add(p.getLibro());
            }
        }
        return libros;
    }

    /**
     * Lista todos los préstamos activos.
     *
     * @return Lista de préstamos.
     */
    public List<Prestamo> listarPrestamos() {
        return prestamos;
    }

    /**
     * Devuelve los préstamos asociados a un usuario.
     *
     * @param usuario Usuario a consultar.
     * @return Lista de préstamos de ese usuario.
     */
    public List<Prestamo> prestamosPorUsuario(Usuario usuario) {
        List<Prestamo> resultado = new ArrayList<>();
        for (Prestamo p : prestamos) {
            if (p.getUsuario().equals(usuario)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    /**
     * Imprime por consola las relaciones entre usuarios y libros en el grafo.
     */
    public void imprimirRelaciones() {
        grafo.imprimirRelaciones();
    }

    /**
     * Devuelve todos los libros relacionados con un usuario usando el grafo.
     *
     * @param usuario Usuario a consultar.
     * @return Lista de libros relacionados.
     */
    public List<Libro> obtenerLibrosPorUsuarioDesdeGrafo(Usuario usuario) {
        List<Libro> relacionados = new ArrayList<>();
        NodoGrafo nodoUsuario = new NodoGrafo(usuario);
        for (NodoGrafo ady : grafo.obtenerAdyacentes(nodoUsuario)) {
            if (ady.getDato() instanceof Libro) {
                relacionados.add((Libro) ady.getDato());
            }
        }
        return relacionados;
    }
}
