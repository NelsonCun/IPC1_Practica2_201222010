package ipc1_practica2_201222010;

/**
 *
 * @author nelson
 */
//Librerías
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

public class GenerarViaje extends JFrame implements ActionListener {

    JLabel lblInicio, lblDestino, lblTransporte, lblDisponibilidad;
    JComboBox<String> cbxInicio, cbxDestino, cbxTransporte;
    JButton btnGenerar, btnCancelar;

    public GenerarViaje() {
        initComplements();
    }

    public void initComplements() {

        ////////////////////////////// TÍTULOS /////////////////////////////
        //Label Inicio de ruta
        lblInicio = new JLabel("Seleccionar punto inicial");
        lblInicio.setBounds(24, 15, 200, 30);
        lblInicio.setFont(new Font("Arial", Font.BOLD, 12));
        lblInicio.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblInicio);

        //Label Final de ruta
        lblDestino = new JLabel("Seleccionar destino");
        lblDestino.setBounds(24, 90, 200, 30);
        lblDestino.setFont(new Font("Arial", Font.BOLD, 12));
        lblDestino.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblDestino);

        //Label Tipo de transporte
        lblTransporte = new JLabel("Seleccionar tipo de transporte");
        lblTransporte.setBounds(275, 15, 200, 30);
        lblTransporte.setFont(new Font("Arial", Font.BOLD, 12));
        lblTransporte.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(lblTransporte);

        //Label Disponibilidad pilotos
        lblDisponibilidad = new JLabel("No hay pilotos disponibles");
        lblDisponibilidad.setBounds(220, 110, 260, 30);
        lblDisponibilidad.setForeground(Color.red);
        lblDisponibilidad.setFont(new Font("Arial", Font.BOLD, 19));
        lblDisponibilidad.setHorizontalAlignment(SwingConstants.CENTER);
        if (Main.viajes.size() < 3) {
            lblDisponibilidad.setVisible(false);
        } else {
            lblDisponibilidad.setVisible(true);
        }
        this.add(lblDisponibilidad);

        /////////////////////////////// COMBOBOX ////////////////////////////
        cbxInicio = new JComboBox<>(Main.Lugares());
        cbxInicio.setBounds(50, 50, 150, 30);
        this.add(cbxInicio);

        cbxDestino = new JComboBox<>(Main.Lugares());
        cbxDestino.setBounds(50, 125, 150, 30);
        this.add(cbxDestino);

        cbxTransporte = new JComboBox<>(Main.Transportes());
        cbxTransporte.setBounds(300, 50, 150, 30);
        this.add(cbxTransporte);

        /////////////////////////////// BOTONES /////////////////////////////
        //Botón para cancelar
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(75, 170, 150, 30);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBackground(new Color(75, 206, 208));
        btnCancelar.addActionListener(this);
        this.add(btnCancelar);

        //Botón generar viaje
        btnGenerar = new JButton("Generar viaje");
        btnGenerar.setBounds(275, 170, 150, 30);
        btnGenerar.setForeground(Color.WHITE);
        btnGenerar.setBackground(new Color(75, 206, 208));
        if (Main.viajes.size() < 3) {
            btnGenerar.setEnabled(true);
        } else {
            btnGenerar.setEnabled(false);
        }
        btnGenerar.addActionListener(this);

        this.add(btnGenerar);

        ///////////////////////// Config de ventana /////////////////////////
        this.setTitle("GENERAR VIAJE");
        this.setLocationRelativeTo(null);
        this.setSize(500, 250);
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
        if (ae.getSource() == btnGenerar) {
            String inicio = (String) cbxInicio.getSelectedItem();
            String fin = (String) cbxDestino.getSelectedItem();
            String transporte = (String) cbxTransporte.getSelectedItem();
            if (inicio.equals("Seleccionar") || fin.equals("Seleccionar") || transporte.equals("Seleccionar")) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar una opción en cada campo", "ERROR", 0);
            } else {
                boolean encontrado = false;
                for (int i = 0; i < Main.rutas.size(); i++) {
                    if ((Main.rutas.get(i).getInicio().equals(inicio) && Main.rutas.get(i).getFin().equals(fin))
                            || (Main.rutas.get(i).getInicio().equals(fin) && Main.rutas.get(i).getFin().equals(inicio))) {
                        Viaje newViaje = new Viaje(Viaje.conteoViaje, inicio, fin, transporte,Main.rutas.get(i).getDistancia());
                        Main.addViaje(newViaje);
                        JOptionPane.showMessageDialog(null, "Viaje generado exitosamente",
                                "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                        this.dispose();
                        if (Main.ventanaAbierta == 0) {
                            MenuInicio newMenu = new MenuInicio();
                        } else if (Main.ventanaAbierta == 1) {
                            IniciarViaje newInicio = new IniciarViaje();
                        }
                        Viaje.conteoViaje = Viaje.conteoViaje + 1;
                        encontrado = true;
                        break;
                    }
                }
                if (encontrado==false) {
                    JOptionPane.showMessageDialog(this, "Esta ruta no existe en nuestro catálogo", "ERROR", 0);
                }

            }

        } else if (ae.getSource() == btnCancelar) {
            this.dispose();
            if (Main.ventanaAbierta == 0) {
                MenuInicio newMenu = new MenuInicio();
            } else if (Main.ventanaAbierta == 1) {
                IniciarViaje newInicio = new IniciarViaje();
            }

        }
    }
}
