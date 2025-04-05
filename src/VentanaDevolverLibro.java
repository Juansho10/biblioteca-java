import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Ventana que permite a un usuario devolver un libro previamente prestado.
 */

public class VentanaDevolverLibro extends JFrame {
    @SuppressWarnings("unused")
    public VentanaDevolverLibro(GestorPrestamos gestorPrestamos, Usuario usuario) {
        setTitle("📘 Devolver Libro");
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10)); // 4 filas
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Obtener libros prestados una vez
        List<Libro> librosPrestados = gestorPrestamos.obtenerLibrosPrestados(usuario);
        String[] titulos = librosPrestados.stream().map(Libro::getTitulo).toArray(String[]::new);
        JComboBox<String> comboLibros = new JComboBox<>(titulos);

        JButton btnDevolver = new JButton("Devolver");
        JLabel mensaje = new JLabel("", SwingConstants.CENTER);

        btnDevolver.addActionListener((ActionEvent e) -> {
            int index = comboLibros.getSelectedIndex();
            if (index >= 0) {
                Libro libroSeleccionado = librosPrestados.get(index);
                boolean exito = gestorPrestamos.devolverLibro(libroSeleccionado, usuario);

                if (exito) {
                    mensaje.setText("✅ Libro devuelto: " + libroSeleccionado.getTitulo());
                } else {
                    mensaje.setText("❌ No se pudo devolver el libro.");
                }
            }
        });

        panel.add(new JLabel("Seleccione un libro a devolver:"));
        panel.add(comboLibros);
        panel.add(btnDevolver);
        panel.add(mensaje);

        add(panel);
        setVisible(true);
    }
}

