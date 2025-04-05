import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de gestionar los préstamos de libros.
 * Utiliza una lista para almacenar todos los préstamos activos.
 */
public class GestorPrestamos {
    private List<Prestamo> prestamos = new ArrayList<>();

    /**
     * Registra un préstamo si el libro está disponible.
     *
     * @param usuario Usuario que solicita el préstamo.
     * @param libro Libro que desea prestar.
     * @return true si el préstamo fue exitoso, false si el libro ya está prestado.
     */
    public boolean prestarLibro(Usuario usuario, Libro libro) {
        if (!libro.estaDisponible()) return false;

        libro.prestar();
        prestamos.add(new Prestamo(usuario, libro));
        return true;
    }

    /**
     * Devuelve un libro si el préstamo existe.
     *
     * @param libro Libro a devolver.
     * @param usuario Usuario que devuelve el libro.
     * @return true si se encontró y procesó el préstamo, false en caso contrario.
     */
    public boolean devolverLibro(Libro libro, Usuario usuario) {
        for (Prestamo p : prestamos) {
            if (p.getUsuario().equals(usuario) && p.getLibro().equals(libro)) {
                p.getLibro().devolver();
                prestamos.remove(p);
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene los libros que tiene actualmente prestados un usuario.
     *
     * @param usuario Usuario del cual se quieren obtener los préstamos.
     * @return Lista de libros prestados por el usuario.
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
     * Retorna la lista completa de préstamos activos.
     *
     * @return Lista de préstamos.
     */
    public List<Prestamo> listarPrestamos() {
        return prestamos;
    }

    /**
     * Retorna todos los préstamos asociados a un usuario específico.
     *
     * @param usuario Usuario a consultar.
     * @return Lista de préstamos del usuario.
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
}




