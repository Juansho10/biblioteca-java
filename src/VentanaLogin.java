import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * VentanaLogin es la ventana inicial que permite a los usuarios registrarse o iniciar sesión en el sistema de biblioteca.
 * 
 * Usa pestañas (JTabbedPane) para separar el login y el registro.
 */

public class VentanaLogin extends JFrame {
    private GestorUsuarios gestorUsuarios;
    
    public VentanaLogin(GestorUsuarios gestorUsuarios) {
        this.gestorUsuarios = gestorUsuarios;
        setTitle("📚 Sistema de Biblioteca - Login / Registro");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabs = new JTabbedPane();

        tabs.addTab("Iniciar Sesión", crearPanelLogin());
        tabs.addTab("Registrarse", crearPanelRegistro());

        add(tabs);
        setVisible(true);
    }

    @SuppressWarnings("unused")
    private JPanel crearPanelLogin() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));

        JTextField campoUsuario = new JTextField();
        JPasswordField campoPassword = new JPasswordField();

        JButton btnLogin = new JButton("Iniciar Sesión");
        JLabel mensaje = new JLabel("", SwingConstants.CENTER);

        btnLogin.addActionListener((ActionEvent e) -> {
            String usuario = campoUsuario.getText();
            String pass = new String(campoPassword.getPassword());

            Usuario u = gestorUsuarios.login(usuario, pass);
            if (u != null) {
                mensaje.setText("✅ Bienvenido, " + u.getNombre());
                dispose(); // Cierra esta ventana
                new VentanaPrincipal(u, gestorUsuarios); // Abre la principal
            } else {
                mensaje.setText("❌ Usuario o contraseña incorrectos");
            }
        });

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(new JLabel("Usuario:"));
        panel.add(campoUsuario);
        panel.add(new JLabel("Contraseña:"));
        panel.add(campoPassword);
        panel.add(btnLogin);
        panel.add(mensaje);

        return panel;
    }

    private JPanel crearPanelRegistro() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));

        JTextField campoNombre = new JTextField();
        JTextField campoUsuario = new JTextField();
        JPasswordField campoPassword = new JPasswordField();

        JButton btnRegistrar = new JButton("Registrar");
        JLabel mensaje = new JLabel("", SwingConstants.CENTER);

        btnRegistrar.addActionListener((ActionEvent e) -> {
            String nombre = campoNombre.getText();
            String usuario = campoUsuario.getText();
            String pass = new String(campoPassword.getPassword());

            boolean exito = gestorUsuarios.registrarUsuario(nombre, usuario, pass);
            if (exito) {
                mensaje.setText("✅ Registro exitoso. Ahora inicia sesión.");
            } else {
                mensaje.setText("❌ El usuario ya existe.");
            }
        });

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(new JLabel("Nombre:"));
        panel.add(campoNombre);
        panel.add(new JLabel("Usuario:"));
        panel.add(campoUsuario);
        panel.add(new JLabel("Contraseña:"));
        panel.add(campoPassword);
        panel.add(btnRegistrar);
        panel.add(mensaje);

        return panel;
    }
}
