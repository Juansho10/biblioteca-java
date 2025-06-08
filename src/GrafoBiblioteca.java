import java.util.*;

/**
 * Representa un grafo no dirigido donde los nodos son libros y usuarios,
 * y las aristas representan relaciones como préstamos.
 */
public class GrafoBiblioteca {
    private Map<NodoGrafo, List<NodoGrafo>> adyacencias = new HashMap<>();

    /**
     * Agrega un nodo al grafo si no existe previamente.
     * @param nodo Nodo a agregar.
     */
    public void agregarNodo(NodoGrafo nodo) {
        adyacencias.putIfAbsent(nodo, new ArrayList<>());
    }

    /**
     * Crea una arista no dirigida entre dos nodos.
     * @param origen Nodo origen.
     * @param destino Nodo destino.
     */
    public void agregarArista(NodoGrafo origen, NodoGrafo destino) {
        agregarNodo(origen);
        agregarNodo(destino);
        adyacencias.get(origen).add(destino);
        adyacencias.get(destino).add(origen);
    }

    /**
     * Devuelve los nodos adyacentes a un nodo dado.
     * @param nodo Nodo del cual obtener adyacencias.
     * @return Lista de nodos conectados.
     */
    public List<NodoGrafo> obtenerAdyacentes(NodoGrafo nodo) {
        return adyacencias.getOrDefault(nodo, new ArrayList<>());
    }

    /**
     * Muestra por consola las relaciones del grafo.
     */
    public void imprimirRelaciones() {
        for (Map.Entry<NodoGrafo, List<NodoGrafo>> entry : adyacencias.entrySet()) {
            System.out.println(entry.getKey() + " está conectado con:");
            for (NodoGrafo vecino : entry.getValue()) {
                System.out.println("  - " + vecino);
            }
        }
    }
}

