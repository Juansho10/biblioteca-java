import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Ventana que permite buscar libros disponibles por su título en la biblioteca.
 */

public class VentanaBuscarLibro extends JFrame {
    public VentanaBuscarLibro(GestorLibros gestorLibros) {
        setTitle("🔍 Buscar Libros");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Espacio alrededor

        JLabel labelTitulo = new JLabel("Ingrese el título del libro:");
        labelTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField campoBusqueda = new JTextField();
        campoBusqueda.setMaximumSize(new Dimension(300, 30)); // Limitar ancho
        campoBusqueda.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel resultadoLabel = new JLabel("");
        resultadoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(labelTitulo);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio
        panel.add(campoBusqueda);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(btnBuscar);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(resultadoLabel);

        add(panel);

        btnBuscar.addActionListener(e -> {
            String tituloBuscado = campoBusqueda.getText().trim();
            if (tituloBuscado.isEmpty()) {
                resultadoLabel.setText("⚠️ Ingrese un título para buscar.");
                return;
            }

            List<Libro> libros = gestorLibros.obtenerLibrosDisponibles();
            boolean encontrado = false;
            for (Libro libro : libros) {
                if (libro.getTitulo().equalsIgnoreCase(tituloBuscado)) {
                    resultadoLabel.setText("✅ Libro encontrado: " + libro.getTitulo() + " - " + libro.getAutor());
                    encontrado = true;
                    break;
                }
            }

            if (!encontrado) {
                resultadoLabel.setText("❌ No se encontró el libro.");
            }
        });

        setVisible(true);
    }
}
