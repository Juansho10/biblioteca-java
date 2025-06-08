/**
 * Representa un nodo en el grafo. Puede ser un libro o un usuario.
 */
public class NodoGrafo {
    private Object dato;

    /**
     * Constructor que inicializa el nodo con un dato.
     * @param dato Objeto representado (Usuario o Libro).
     */
    public NodoGrafo(Object dato) {
        this.dato = dato;
    }

    /**
     * Devuelve el dato almacenado en el nodo.
     * @return Dato del nodo.
     */
    public Object getDato() {
        return dato;
    }

    @Override
    public String toString() {
        return dato.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof NodoGrafo)) return false;
        NodoGrafo otro = (NodoGrafo) obj;
        return this.dato.equals(otro.dato);
    }

    @Override
    public int hashCode() {
        return dato.hashCode();
    }
}
