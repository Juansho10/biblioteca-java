import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * VentanaPrestarLibro permite al usuario seleccionar un libro disponible y solicitar su préstamo.
 * 
 * Gestiona el préstamo a través de `GestorPrestamos` y actualiza el estado de los libros.
 */

public class VentanaPrestarLibro extends JFrame {
    @SuppressWarnings("unused")
    public VentanaPrestarLibro(GestorLibros gestorLibros, GestorPrestamos gestorPrestamos, Usuario usuario) {
        setTitle("📗 Prestar Libro");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10)); // 4 filas
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Obtener lista de libros disponibles una vez
        List<Libro> librosDisponibles = gestorLibros.obtenerLibrosDisponibles();
        String[] titulos = librosDisponibles.stream().map(Libro::getTitulo).toArray(String[]::new);
        JComboBox<String> comboLibros = new JComboBox<>(titulos);

        JButton btnPrestar = new JButton("Prestar");
        JLabel mensaje = new JLabel("", SwingConstants.CENTER);

        btnPrestar.addActionListener((ActionEvent e) -> {
            int index = comboLibros.getSelectedIndex();
            if (index >= 0) {
                Libro libroSeleccionado = librosDisponibles.get(index);
                boolean exito = gestorPrestamos.prestarLibro(usuario, libroSeleccionado);

                if (exito) {
                    mensaje.setText("✅ Libro prestado: " + libroSeleccionado.getTitulo());
                } else {
                    mensaje.setText("❌ No se pudo prestar el libro.");
                }
            }
        });

        panel.add(new JLabel("Seleccione un libro:"));
        panel.add(comboLibros);
        panel.add(btnPrestar);
        panel.add(mensaje);

        add(panel);
        setVisible(true);
    }
}

