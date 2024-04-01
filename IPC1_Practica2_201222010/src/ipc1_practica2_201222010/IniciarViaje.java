package ipc1_practica2_201222010;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.*;

/**
 *
 * @author nelson
 */
public class IniciarViaje extends JFrame implements ActionListener {
    
    JButton btnTodos, btnGenerarViaje, btnHistorial, btnSalir;
    
    public IniciarViaje() {
        initComponents();
    }
    
    public void initComponents() {

        /////////////////////////////////////// BOTONES ///////////////////////////////////////////
        btnTodos = new JButton("Iniciar todos");
        btnTodos.setBounds(800, 20, 120, 30);
        btnTodos.setForeground(Color.WHITE);
        btnTodos.setBackground(Color.blue);
        btnTodos.addActionListener(this);
        this.add(btnTodos);
        
        btnGenerarViaje = new JButton("Generar otro viaje");
        btnGenerarViaje.setBounds(450, 450, 150, 30);
        btnGenerarViaje.setForeground(Color.WHITE);
        btnGenerarViaje.setBackground(new Color(103, 160, 0));
        btnGenerarViaje.addActionListener(this);
        this.add(btnGenerarViaje);
        
        btnHistorial = new JButton("Ver historial");
        btnHistorial.setBounds(620, 450, 150, 30);
        btnHistorial.setForeground(Color.WHITE);
        btnHistorial.setBackground(new Color(103, 160, 0));
        btnHistorial.addActionListener(this);
        this.add(btnHistorial);
        
        btnSalir = new JButton("Salir");
        btnSalir.setBounds(800, 450, 100, 30);
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setBackground(new Color(188, 38, 38));
        btnSalir.addActionListener(this);
        this.add(btnSalir);

        //////////////////////////////////// PANELES DINÁMICOS /////////////////////////////////////
        JPanel panelViajes = new JPanel();
        panelViajes.setLayout(null);
        int y = 0;

        // Iterar sobre los viajes existentes en el ArrayList
        for (Viaje viaje : Main.viajes) {
            JPanel jpTemp = viaje.getJp1();
            jpTemp.setBounds(0, y, 900, 120);
            jpTemp.setVisible(true);
            panelViajes.add(jpTemp);

            // Ajustar la posición Y para el siguiente panel
            y += 130;
        }
        
        panelViajes.setPreferredSize(new Dimension(900, 130 * Main.viajes.size()));
        JScrollPane sp1 = new JScrollPane(panelViajes);
        sp1.setBounds(30, 58, 900, 385);
        sp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(sp1);

        ////////////////////////////////// AJUSTES DE LA VENTANA ///////////////////////////////////
        this.setTitle("INICIAR VIAJES");
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
                Main.EscribirArchivoRecorridos();
            }
        });
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnTodos) {
            for (int i = 0; i < Main.viajes.size(); i++) {
                if (Main.viajes.get(i).isViajeIniciado() == false) {
                    Recorrido recorrido = new Recorrido(Main.viajes.get(i), Main.viajes.get(i).getDistancia());
                    Main.addRecorrido(recorrido);
                    Main.viajes.get(i).setViajeIniciado(true);
                    System.out.println("Se inicia recorrido");
                    LocalDateTime horaInicio = LocalDateTime.now();
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                    Main.viajes.get(i).fechaInicio = horaInicio.format(formato);
                    System.out.println("Fecha inicio: " + Main.viajes.get(i).fechaInicio);
                    Main.viajes.get(i).btnIniciar.setEnabled(false);
                    recorrido.start();
                }
            }
        } else if (ae.getSource() == btnGenerarViaje) {
            GenerarViaje newViaje = new GenerarViaje();
            this.dispose();
        } else if (ae.getSource() == btnHistorial) {
            HistorialViajes newHistorial = new HistorialViajes();
        } else if (ae.getSource() == btnSalir) {
            this.dispose();
            Main.ventanaAbierta = 0;
            MenuInicio newInicio = new MenuInicio();
        }
    }
    
}
