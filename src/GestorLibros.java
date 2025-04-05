import java.util.ArrayList;

/**
 * Clase encargada de gestionar los libros disponibles en el sistema.
 * Utiliza un ArrayList como estructura de datos para almacenar los libros.
 */
public class GestorLibros {
    private ArrayList<Libro> libros = new ArrayList<>();

    /**
     * Constructor que inicializa la biblioteca con una lista predefinida de libros.
     */
    public GestorLibros() {
        agregarLibro("Cien años de soledad", "Gabriel García Márquez");
        agregarLibro("1984", "George Orwell");
        agregarLibro("El Principito", "Antoine de Saint-Exupéry");
        agregarLibro("Don Quijote de la Mancha", "Miguel de Cervantes");
        agregarLibro("Harry Potter y la piedra filosofal", "J.K. Rowling");
    }

    /**
     * Agrega un nuevo libro a la biblioteca.
     *
     * @param titulo Título del libro.
     * @param autor Autor del libro.
     */
    public void agregarLibro(String titulo, String autor) {
        libros.add(new Libro(titulo, autor));
    }

    /**
     * Busca libros cuyo título o autor coincida con una palabra clave.
     *
     * @param palabraClave Término de búsqueda.
     * @return Lista de libros que coinciden con la búsqueda.
     */
    public ArrayList<Libro> buscarLibro(String palabraClave) {
        ArrayList<Libro> resultados = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.getTitulo().toLowerCase().contains(palabraClave.toLowerCase()) ||
                libro.getAutor().toLowerCase().contains(palabraClave.toLowerCase())) {
                resultados.add(libro);
            }
        }
        return resultados;
    }

    /**
     * Retorna la lista completa de libros.
     *
     * @return Lista de libros.
     */
    public ArrayList<Libro> getLibros() {
        return libros;
    }

    /**
     * Devuelve una lista con todos los libros que están disponibles para préstamo.
     *
     * @return Lista de libros disponibles.
     */
    public ArrayList<Libro> obtenerLibrosDisponibles() {
        ArrayList<Libro> disponibles = new ArrayList<>();
        for (Libro libro : libros) {
            if (libro.estaDisponible()) {
                disponibles.add(libro);
            }
        }
        return disponibles;
    }
}
