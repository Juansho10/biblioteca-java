/**
 * Representa un préstamo de un libro realizado por un usuario.
 */
public class Prestamo {
    private Usuario usuario;
    private Libro libro;

    /**
     * Constructor para crear un nuevo préstamo.
     *
     * @param usuario Usuario que realiza el préstamo.
     * @param libro Libro que ha sido prestado.
     */
    public Prestamo(Usuario usuario, Libro libro) {
        this.usuario = usuario;
        this.libro = libro;
    }

    public Usuario getUsuario() { return usuario; }
    public Libro getLibro() { return libro; }

    /**
     * Representación en texto del préstamo realizado.
     */
    @Override
    public String toString() {
        return usuario.getNombre() + " tiene prestado: " + libro.getTitulo();
    }
}

