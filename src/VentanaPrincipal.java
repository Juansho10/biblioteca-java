import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * VentanaPrincipal representa la ventana principal del sistema de biblioteca una vez que un usuario inicia sesión.
 * 
 * Dependiendo de si el usuario es administrador o no, se mostrarán opciones adicionales.
 * Permite navegar hacia la búsqueda de libros, préstamo, devolución y gestión de usuarios/libros.
 */

public class VentanaPrincipal extends JFrame {
    private Usuario usuario;
    private GestorUsuarios gestorUsuarios;
    private GestorLibros gestorLibros;
    private GestorPrestamos gestorPrestamos;

    public VentanaPrincipal(Usuario usuario, GestorUsuarios gestorUsuarios) {
        this.usuario = usuario;
        this.gestorUsuarios = gestorUsuarios;
        this.gestorLibros = new GestorLibros(); // Libros precargados
        this.gestorPrestamos = new GestorPrestamos();

        setTitle("📚 Biblioteca - Bienvenido " + usuario.getNombre());
        setSize(400, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // Panel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(30, 50, 30, 50)); // márgenes

        JLabel titulo = new JLabel("¿Qué deseas hacer?");
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        titulo.setBorder(new EmptyBorder(0, 0, 20, 0)); // espacio debajo del título

        JButton btnBuscar = new JButton("🔍 Buscar libros");
        JButton btnPrestar = new JButton("📗 Prestar libro");
        JButton btnDevolver = new JButton("📘 Devolver libro");

        // Centrar botones
        btnBuscar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnPrestar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnDevolver.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(titulo);
        panel.add(btnBuscar);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(btnPrestar);
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        panel.add(btnDevolver);

        if (usuario.esAdmin()) {
            JButton btnAgregarLibro = new JButton("➕ Agregar libro");
            JButton btnVerUsuarios = new JButton("👥 Ver usuarios");

            btnAgregarLibro.setAlignmentX(Component.CENTER_ALIGNMENT);
            btnVerUsuarios.setAlignmentX(Component.CENTER_ALIGNMENT);

            panel.add(Box.createRigidArea(new Dimension(0, 20)));
            panel.add(btnAgregarLibro);
            panel.add(Box.createRigidArea(new Dimension(0, 15)));
            panel.add(btnVerUsuarios);

            btnVerUsuarios.addActionListener(e -> {
                VentanaVerUsuarios ventanaUsuarios = new VentanaVerUsuarios(gestorUsuarios);
                ventanaUsuarios.setVisible(true);
            });

            btnAgregarLibro.addActionListener(e -> {
                // Aquí podrías agregar la acción para agregar libros
            });
        }

        // Acción para buscar libros
        btnBuscar.addActionListener(e -> {
            VentanaBuscarLibro ventanaBuscar = new VentanaBuscarLibro(gestorLibros);
            ventanaBuscar.setVisible(true);
        });

        // Acción para prestar libro
        btnPrestar.addActionListener(e -> {
            VentanaPrestarLibro ventanaPrestar = new VentanaPrestarLibro(gestorLibros, gestorPrestamos, usuario);
            ventanaPrestar.setVisible(true);
        });

        // Acción para devolver libro
        btnDevolver.addActionListener(e -> {
            VentanaDevolverLibro ventanaDevolver = new VentanaDevolverLibro(gestorPrestamos, usuario);
            ventanaDevolver.setVisible(true);
        });

        add(panel);
        setVisible(true);
    }
}