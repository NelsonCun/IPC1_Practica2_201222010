package ipc1_practica2_201222010;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author nelson
 */
class Recorrido extends Thread implements ActionListener, Serializable {

    Viaje viaje;
    private int kilometrosR = 0;
    private float combustibleGastado = 0;
    private volatile boolean running = true;
    int posX, posY;
    float capacidadTanque = 0;
    float gastoKm = 0;
    JButton btnRecargar;
    JButton btnRegresar;
    private volatile boolean parte1 = false;

    public Recorrido(Viaje viaje, float Distancia) {
        System.out.println("Sí nos llegó señal");
        this.viaje = viaje;
        this.viaje.lblRecorrido = new JLabel("Recorrido = 0 km");
        this.viaje.lblRecorrido.setForeground(Color.BLACK);
        this.viaje.lblRecorrido.setFont(new Font("Arial", Font.BOLD, 10));
        posX = this.viaje.lblITransp.getX();
        posY = this.viaje.lblITransp.getY();
        System.out.println("x= " + posX + " , y= " + posY);
        this.viaje.lblRecorrido.setBounds(posX, posY - 25, 150, 30);
        this.viaje.lblRecorrido.setVisible(true);
        this.viaje.jp1.add(this.viaje.lblRecorrido);

        for (int i = 0; i < Main.transportes.size(); i++) {
            if (this.viaje.getTransporte().equals(Main.transportes.get(i).getTipoTransporte())) {
                capacidadTanque = Main.transportes.get(i).getCapacidadTanque();
                gastoKm = Main.transportes.get(i).getGastoCombustible();
            }
        }
        this.viaje.lblGasActual = new JLabel("Gasolina Actual = " + capacidadTanque);
        this.viaje.lblGasActual.setFont(new Font("Arial", Font.BOLD, 10));
        this.viaje.lblGasActual.setBounds(posX - 10, posY - 12, 150, 30);
        this.viaje.lblGasActual.setVisible(true);
        this.viaje.jp1.add(this.viaje.lblGasActual);

        btnRecargar = new JButton("Recargar");
        btnRecargar.setBounds(posX, posY - 45, 100, 25);
        btnRecargar.setVisible(false);
        btnRecargar.setBackground(Color.orange);
        btnRecargar.setForeground(Color.white);
        btnRecargar.addActionListener(this);
        this.viaje.jp1.add(btnRecargar);

        //Botón Regresar
        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(695, 80, 100, 25);
        btnRegresar.setBackground(Color.BLUE);
        btnRegresar.setForeground(Color.white);
        btnRegresar.setVisible(true);
        btnRegresar.setEnabled(false);
        btnRegresar.addActionListener(this);
        this.viaje.jp1.add(btnRegresar);

        this.viaje.jp1.repaint();
    }

    @Override
    public void run() {
        if (parte1 == false) {
            while (running) {
                try {
                    if (this.posX < 610) {
                        float velocidad = 495 / this.viaje.getDistancia();
                        int intVelocidad = (int) velocidad;
                        posX = posX + intVelocidad;
                        capacidadTanque = capacidadTanque - gastoKm;
                    } else {
                        detenerRecorrido();
                        btnRegresar.setEnabled(true);
                    }
                    sleep(1000);

                    if (running) {
                        kilometrosR++;
                        combustibleGastado = combustibleGastado+gastoKm;
                    }
                    if (capacidadTanque <= 0) {
                        detenerRecorrido();
                        btnRecargar.setVisible(true);
                    }
                    actualizarRecorrido();
                    this.viaje.lblITransp.setLocation(posX, posY);
                    this.viaje.lblRecorrido.setLocation(posX - 10, posY - 25);
                    this.viaje.lblGasActual.setLocation(posX - 10, posY - 12);
                    btnRecargar.setLocation(posX, posY - 45);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else if (parte1) {
         
            while (running) {
                try {
                    if (this.posX > 125) {
                        float velocidad = 495 / this.viaje.getDistancia();
                        int intVelocidad = (int) velocidad;
                        posX = posX - intVelocidad;
                        capacidadTanque = capacidadTanque - gastoKm;
                    } else {
                        detenerRecorrido();
                        LocalDateTime horaFin = LocalDateTime.now();
                        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                        this.viaje.fechaFin = horaFin.format(formato);

                        System.out.println("Fecha inicio: " + this.viaje.fechaFin);
                        
                        ViajeRealizado newViaje = new ViajeRealizado(this.viaje.fechaInicio,this.viaje.fechaFin,kilometrosR,this.viaje.getTransporte(),combustibleGastado);
                        Main.addViajeRealizado(newViaje);
                        ViajeRealizado.numero ++;
                        
                        for (int i = 0; i < Main.viajes.size(); i++) {
                            if (this.viaje.getTransporte().equals(Main.viajes.get(i).getTransporte())) {
                                Viaje.conteoViaje = Main.viajes.get(i).getNumeroViaje();
                                Main.viajes.remove(i);
                            }
                        }

                    }
                    sleep(1000);

                    if (running) {
                        kilometrosR++;
                        combustibleGastado = combustibleGastado+gastoKm;
                    }
                    if (capacidadTanque <= 0) {
                        detenerRecorrido();
                        btnRecargar.setVisible(true);
                    }
                    actualizarRecorrido();
                    this.viaje.lblITransp.setLocation(posX, posY);
                    this.viaje.lblRecorrido.setLocation(posX - 10, posY - 25);
                    this.viaje.lblGasActual.setLocation(posX - 10, posY - 12);
                    btnRecargar.setLocation(posX, posY - 45);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    public void detenerRecorrido() {
        running = false;
    }

    private void actualizarRecorrido() {
        String recorrido = String.format("%02d", kilometrosR);
        this.viaje.lblRecorrido.setText("Recorrido= " + recorrido + "km");
        String gasto = String.format("%.2f", capacidadTanque);
        this.viaje.lblGasActual.setText("Gasolina actual= " + gasto);
        System.out.println(recorrido);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == btnRecargar) {
            for (int i = 0; i < Main.transportes.size(); i++) {
                if (this.viaje.getTransporte().equals(Main.transportes.get(i).getTipoTransporte())) {
                    capacidadTanque = Main.transportes.get(i).getCapacidadTanque();
                }
            }
            running = true;
            System.out.println("Se recargó");
            btnRecargar.setVisible(false);
            Thread recorridoThread = new Thread(this);
            recorridoThread.start();
        } else if (ae.getSource() == btnRegresar) {
            btnRegresar.setEnabled(false);
            running = true;
            parte1 = true;
            Thread recorridoThread = new Thread(this);
            recorridoThread.start();
        }
    }

}
