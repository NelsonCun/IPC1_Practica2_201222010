package ipc1_practica2_201222010;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        for (int i = 0; i < Main.viajes.size(); i++) {
            Viaje viajeTemp = new Viaje(Main.viajes.get(i).getNumeroViaje(),
                    Main.viajes.get(i).getInicio(),
                    Main.viajes.get(i).getDestino(),
                    Main.viajes.get(i).getTransporte(),
                    Main.viajes.get(i).getDistancia());

            System.out.println("viaje: " + Main.viajes.get(i).getNumeroViaje()
                    + " inicio: " + Main.viajes.get(i).getInicio() + " destino: "
                    + Main.viajes.get(i).getDestino() + " transporte: "
                    + Main.viajes.get(i).getTransporte());

            if (Main.viajes.get(i).getNumeroViaje() == 0) {
                y = 0;
            } else if (Main.viajes.get(i).getNumeroViaje() == 1) {
                y = 130;
            } else if (Main.viajes.get(i).getNumeroViaje() == 2) {
                y = 260;
            }
            JPanel jpTemp = viajeTemp.getJp1();
            jpTemp.setBounds(0, y, 900, 120);
            jpTemp.setVisible(true);
            panelViajes.add(jpTemp);
        }

        panelViajes.setPreferredSize(new Dimension(900, 380));
        panelViajes.setVisible(true);
        JScrollPane sp1 = new JScrollPane(panelViajes);
        sp1.setBounds(30, 58, 900, 385);
        sp1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        this.add(sp1);
        //this.add(panelViajes);

        ////////////////////////////////// AJUSTES DE LA VENTANA ///////////////////////////////////
        this.setTitle("INICIAR VIAJES");
        this.setLocationRelativeTo(null);
        this.setSize(960, 540);
        setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnTodos) {
            for (int i = 0; i < Main.viajes.size(); i++) {
                Recorrido recorrido = new Recorrido(Main.viajes.get(i), Main.viajes.get(i).getDistancia());
                System.out.println("Se inicia recorrido");
                LocalDateTime horaInicio = LocalDateTime.now();
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                Main.viajes.get(i).fechaInicio = horaInicio.format(formato);
                System.out.println("Fecha inicio: " + Main.viajes.get(i).fechaInicio);
                recorrido.start();
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
