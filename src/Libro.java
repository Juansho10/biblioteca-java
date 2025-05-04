/**
 * Representa un libro dentro del sistema de biblioteca.
 */
public class Libro {
    private String titulo;
    private String autor;
    private boolean disponible = true;

    /**
     * Constructor para crear un nuevo libro.
     *
     * @param titulo Título del libro.
     * @param autor Autor del libro.
     */
    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public boolean estaDisponible() { return disponible; }

    /**
     * Marca el libro como no disponible (prestado).
     */
    public void prestar() { disponible = false; }

    /**
     * Marca el libro como disponible (devuelto).
     */
    public void devolver() { disponible = true; }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    

    /**
     * Representación en texto del libro, indicando su disponibilidad.
     */
    @Override
    public String toString() {
        return titulo + " - " + autor + " [" + (disponible ? "Disponible" : "Prestado") + "]";
    }
}


