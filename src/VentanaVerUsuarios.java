import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * VentanaVerUsuarios muestra una lista de los usuarios registrados en el sistema.
 * 
 * Se utiliza principalmente por administradores para visualizar los usuarios existentes.
 */


public class VentanaVerUsuarios extends JFrame {
    public VentanaVerUsuarios(GestorUsuarios gestorUsuarios) {
        setTitle("👥 Usuarios Registrados");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Obtener la lista de usuarios
        List<Usuario> usuarios = gestorUsuarios.listarUsuarios();
        String[] nombresUsuarios = usuarios.stream().map(Usuario::getNombre).toArray(String[]::new);

        // Crear lista visual de usuarios
        JList<String> listaUsuarios = new JList<>(nombresUsuarios);
        JScrollPane scrollPane = new JScrollPane(listaUsuarios);

        // Agregar al panel
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Usuarios registrados:"), BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }
}

