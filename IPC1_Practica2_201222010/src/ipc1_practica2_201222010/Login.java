package ipc1_practica2_201222010;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author nelson
 */
public class Login extends JFrame implements ActionListener, FocusListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JCheckBox cb1;
    private ImagenFondo iLogin = new ImagenFondo();

    public Login() {
        iniciarComponentes();
    }

    public void iniciarComponentes() {
        ////////////////////////////////// Fondo //////////////////////////////
        this.setContentPane(iLogin);

        /////////////////////////////////////// TÍTULO //////////////////////////////////////////
        JLabel titleLabel = new JLabel("Servicio de viajes");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(0, 30, 500, 30);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel);

        JLabel titleLabel2 = new JLabel("\"AL CHILAZO\"");
        titleLabel2.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel2.setBounds(0, 60, 500, 30);
        titleLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel2);

        ///////////////////////// USUARIO Y CONTRASEÑA /////////////////////////////////////////
        //Etiqueta: Nombre de usuario
        JLabel usernameLabel = new JLabel("Usuario");
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 12));
        usernameLabel.setBounds(70, 120, 80, 25);
        usernameLabel.setForeground(Color.WHITE);
        this.add(usernameLabel);

        //Campo para nombre de usuario
        usernameField = new JTextField("Usuario");
        usernameField.setBounds(130, 120, 260, 25);
        usernameField.addFocusListener(this);
        this.add(usernameField);

        //Etiqueta contraseña
        JLabel passwordLabel = new JLabel("Contraseña");
        passwordLabel.setFont(new Font("Arial", Font.BOLD, 12));
        passwordLabel.setBounds(43, 175, 88, 25);
        passwordLabel.setForeground(Color.WHITE);
        this.add(passwordLabel);

        //Campo para la contraseña
        passwordField = new JPasswordField("Contraseña");
        passwordField.setEchoChar((char) 0);
        passwordField.setBounds(130, 175, 260, 25);
        passwordField.addFocusListener(this);
        this.add(passwordField);

        //Ver contraseña
        cb1 = new JCheckBox("Ver contraseña");
        cb1.setFont(new Font("Arial", Font.BOLD, 10));
        cb1.setBounds(130, 205, 150, 25);
        cb1.setVisible(true);
        cb1.addActionListener(this);
        this.add(cb1);

        //Botón de iniciar sesión
        loginButton = new JButton("Iniciar Sesión");
        loginButton.setBounds(175, 260, 150, 35);
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(243, 190, 102));
        loginButton.addActionListener(this);
        this.add(loginButton);

        ////////////////////////////////// AJUSTES DE LA VENTANA ///////////////////////////////////
        this.setTitle("Inicio de sesión");
        this.setLocationRelativeTo(null);
        this.setSize(500, 400);
        setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Acciones a realizar cuando se cierra la ventana
                System.out.println("La ventana se está cerrando...");
                Main.EscribirArchivoHistorial();
                Main.EscribirArchivoViajes();
                Main.EscribirArchivoRecorridos();
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == cb1) {
            if (cb1.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('\u25CF');
            }
        } else if (ae.getSource() == loginButton) {
            String username = usernameField.getText();
            char[] password = passwordField.getPassword();
            String pwd = new String(password);
            String demoUser = System.getenv().getOrDefault("IPC1_TRAVEL_ADMIN_USER", "admin");
            String demoPassword = System.getenv().getOrDefault("IPC1_TRAVEL_ADMIN_PASSWORD", "admin");
            if (username.equals(demoUser) && pwd.equals(demoPassword)) {
                MenuInicio newInicio = new MenuInicio();
                this.dispose();
                JOptionPane.showMessageDialog(null, "Bienvenido Administrador");
                System.out.println("Bienvenido administrador");
            } else {
                
                JOptionPane.showMessageDialog(this, "El usuario o la contraseña son incorrectos", "ERROR", 0);
                        
            }

        }
    }

    @Override
    public void focusGained(FocusEvent fe) {
        if (fe.getSource() == usernameField) {
            usernameField.setText("");
        } else if (fe.getSource() == passwordField) {
            if (cb1.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('\u25CF');
            }
            passwordField.setText("");
        }

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    class ImagenFondo extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/ipc1_practica2_201222010/Images/iLogin.gif")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);

        }
    }
}
