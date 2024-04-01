package ipc1_practica2_201222010;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author nelson
 */
public class MenuInicio extends JFrame implements ActionListener {

    JLabel titulo;
    JTable tablaRutas;
    JButton btnCargarRutas, btnEditarDistancia, btnGenerarViaje, btnIniciarViaje, btnHistorialViajes, btnSalir;
    JScrollPane scrollTabla;
    private ImagenFondo iLogin = new ImagenFondo();

    public MenuInicio() {
        iniciarComponentes();
    }

    public void iniciarComponentes() {
        ////////////////////////////////// Fondo //////////////////////////////
        this.setContentPane(iLogin);

        /////////////////////////////////////// TÍTULO //////////////////////////////////////////
        titulo = new JLabel("CATÁLOGO DE VIAJES");
        titulo.setFont(new Font("Arial", Font.BOLD, 25));
        titulo.setBounds(25, 65, 600, 35);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titulo);

        /////////////////////////////////////// BOTONES ///////////////////////////////////////////
        btnCargarRutas = new JButton("Cargar rutas (.csv)");
        btnCargarRutas.setBounds(40, 30, 150, 30);
        btnCargarRutas.setForeground(Color.WHITE);
        btnCargarRutas.setBackground(Color.blue);
        btnCargarRutas.addActionListener(this);
        this.add(btnCargarRutas);

        btnEditarDistancia = new JButton("Editar distancia");
        btnEditarDistancia.setBounds(718, 75, 135, 30);
        btnEditarDistancia.setForeground(Color.WHITE);
        btnEditarDistancia.setBackground(new Color(103, 160, 0));
        btnEditarDistancia.addActionListener(this);
        this.add(btnEditarDistancia);

        btnGenerarViaje = new JButton("Generar viaje");
        btnGenerarViaje.setBounds(718, 110, 135, 30);
        btnGenerarViaje.setForeground(Color.WHITE);
        btnGenerarViaje.setBackground(new Color(103, 160, 0));
        btnGenerarViaje.addActionListener(this);
        this.add(btnGenerarViaje);

        btnIniciarViaje = new JButton("Iniciar viaje");
        btnIniciarViaje.setBounds(718, 145, 135, 30);
        btnIniciarViaje.setForeground(Color.WHITE);
        btnIniciarViaje.setBackground(new Color(103, 160, 0));
        btnIniciarViaje.addActionListener(this);
        this.add(btnIniciarViaje);

        btnHistorialViajes = new JButton("Historial de viajes");
        btnHistorialViajes.setBounds(718, 180, 135, 30);
        btnHistorialViajes.setForeground(Color.WHITE);
        btnHistorialViajes.setBackground(new Color(103, 160, 0));
        btnHistorialViajes.addActionListener(this);
        this.add(btnHistorialViajes);

        btnSalir = new JButton("Salir");
        btnSalir.setBounds(800, 450, 100, 30);
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setBackground(new Color(188, 38, 38));
        btnSalir.addActionListener(this);
        this.add(btnSalir);

        //////////////////////////////////// TABLA DE VIAJES ///////////////////////////////////////
        //Tabla Doctores
        String[] titulos_r = {"Codigo", "Inicio", "Fin", "Distancia(km)"};
        tablaRutas = new JTable(MatrizRutas.convertirRutas(), titulos_r);
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < titulos_r.length; i++) {
            tablaRutas.getColumnModel().getColumn(i).setCellRenderer(Alinear);
        }
        tablaRutas.setEnabled(false);
        this.tablaRutas.setOpaque(false);
        resizeColumnWidth(tablaRutas);
        scrollTabla = new JScrollPane(tablaRutas);
        scrollTabla.setBounds(25, 100, 600, 215);
        this.scrollTabla.setOpaque(false);
        scrollTabla.setVisible(true);
        this.add(scrollTabla);

        ////////////////////////////////// AJUSTES DE LA VENTANA ///////////////////////////////////
        this.setTitle("CARGAR RUTAS");
        this.setLocationRelativeTo(null);
        this.setSize(960, 540);
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
            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnCargarRutas) {
            //CargarRutas cargarRutas = new CargarRutas();
            CargarRutas.LecturaCSV(this);
            this.dispose();
        } else if (ae.getSource()==btnEditarDistancia) {
            EditarDistancia editar = new EditarDistancia();
            this.dispose();
        } else if (ae.getSource()==btnGenerarViaje) {
            GenerarViaje generar = new GenerarViaje();
            this.dispose();
        } else if (ae.getSource()==btnIniciarViaje) {
            IniciarViaje iniciar = new IniciarViaje();
            this.dispose();
            Main.ventanaAbierta = 1;
        } else if (ae.getSource()==btnHistorialViajes) {
            HistorialViajes historial = new HistorialViajes();
        } else if (ae.getSource()==btnSalir) {
            this.dispose();
            Login login = new Login();
        }

    }

    public void resizeColumnWidth(JTable table) {
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 15; // Min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }

    class ImagenFondo extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/ipc1_practica2_201222010/Images/iMenuInicio.png")).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            setOpaque(false);
            super.paint(g);

        }
    }
    
}
