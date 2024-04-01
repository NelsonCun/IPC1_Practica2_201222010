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

    private Viaje viaje;
    private int kilometrosR = 0;
    private float combustibleGastado = 0;
    private volatile boolean running = true;
    private int posX;
    private int posY;
    private float capacidadTanque = 0;
    private float gastoKm = 0;
    private JButton btnRecargar;
    private JButton btnRegresar;
    private boolean parte1 = false;

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
        this.viaje.lblGasActual = new JLabel("Gasolina Actual = " + getCapacidadTanque());
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
        if (isParte1() == false) {
            while (isRunning()) {
                try {
                    if (this.getPosX() < 610) {
                        float velocidad = 495 / this.getViaje().getDistancia();
                        int intVelocidad = (int) velocidad;
                        setPosX(getPosX() + intVelocidad);
                        setCapacidadTanque(getCapacidadTanque() - getGastoKm());
                    } else {
                        detenerRecorrido();
                        getBtnRegresar().setEnabled(true);
                    }
                    sleep(1000);

                    if (isRunning()) {
                        setKilometrosR(getKilometrosR() + 1);
                        setCombustibleGastado(getCombustibleGastado() + getGastoKm());
                    }
                    if ((getCapacidadTanque() <= 0)||(getCapacidadTanque() < getGastoKm())) {
                        detenerRecorrido();
                        getBtnRecargar().setVisible(true);
                    }
                    actualizarRecorrido();
                    this.getViaje().lblITransp.setLocation(getPosX(), getPosY());
                    this.getViaje().lblRecorrido.setLocation(getPosX() - 10, getPosY() - 25);
                    this.getViaje().lblGasActual.setLocation(getPosX() - 10, getPosY() - 12);
                    getBtnRecargar().setLocation(getPosX(), getPosY() - 45);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        } else if (this.isParte1()) {
         
            while (isRunning()) {
                try {
                    if (this.getPosX() > 125) {
                        float velocidad = 495 / this.getViaje().getDistancia();
                        int intVelocidad = (int) velocidad;
                        setPosX(getPosX() - intVelocidad);
                        setCapacidadTanque(getCapacidadTanque() - getGastoKm());
                    } else {
                        detenerRecorrido();
                        LocalDateTime horaFin = LocalDateTime.now();
                        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                        this.viaje.fechaFin = horaFin.format(formato);

                        System.out.println("Fecha inicio: " + this.getViaje().fechaFin);
                        
                        ViajeRealizado newViaje = new ViajeRealizado(this.getViaje().fechaInicio,this.getViaje().fechaFin,getKilometrosR(), this.getViaje().getTransporte(), getCombustibleGastado());
                        Main.addViajeRealizado(newViaje);
                        ViajeRealizado.numero ++;
                        
                        for (int i = 0; i < Main.viajes.size(); i++) {
                            if (this.getViaje().getTransporte().equals(Main.viajes.get(i).getTransporte())) {
                                Viaje.conteoViaje = Main.viajes.get(i).getNumeroViaje();
                                Main.viajes.remove(i);
                                Main.recorridos.remove(i);
                            }
                        }

                    }
                    sleep(1000);

                    if (isRunning()) {
                        setKilometrosR(getKilometrosR() + 1);
                        setCombustibleGastado(getCombustibleGastado() + getGastoKm());
                    }
                    if ((getCapacidadTanque() <= 0)||(getCapacidadTanque() < getGastoKm())) {
                        detenerRecorrido();
                        getBtnRecargar().setVisible(true);
                    }
                    actualizarRecorrido();
                    this.getViaje().lblITransp.setLocation(getPosX(), getPosY());
                    this.getViaje().lblRecorrido.setLocation(getPosX() - 10, getPosY() - 25);
                    this.getViaje().lblGasActual.setLocation(getPosX() - 10, getPosY() - 12);
                    getBtnRecargar().setLocation(getPosX(), getPosY() - 45);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        }
    }

    public void detenerRecorrido() {
        setRunning(false);
    }

    private void actualizarRecorrido() {
        String recorrido = String.format("%02d", getKilometrosR());
        this.getViaje().lblRecorrido.setText("Recorrido= " + recorrido + "km");
        String gasto = String.format("%.2f", getCapacidadTanque());
        this.getViaje().lblGasActual.setText("Gasolina actual= " + gasto);
        System.out.println(recorrido);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == getBtnRecargar()) {
            for (int i = 0; i < Main.transportes.size(); i++) {
                if (this.getViaje().getTransporte().equals(Main.transportes.get(i).getTipoTransporte())) {
                    setCapacidadTanque(Main.transportes.get(i).getCapacidadTanque());
                }
            }
            setRunning(true);
            System.out.println("Se recargó");
            getBtnRecargar().setVisible(false);
            Thread recorridoThread = new Thread(this);
            recorridoThread.start();
        } else if (ae.getSource() == getBtnRegresar()) {
            getBtnRegresar().setEnabled(false);
            setRunning(true);
            this.setParte1(true);
            Thread recorridoThread = new Thread(this);
            recorridoThread.start();
        }
    }

    /**
     * @return the viaje
     */
    public Viaje getViaje() {
        return viaje;
    }

    /**
     * @param viaje the viaje to set
     */
    public void setViaje(Viaje viaje) {
        this.viaje = viaje;
    }

    /**
     * @return the kilometrosR
     */
    public int getKilometrosR() {
        return kilometrosR;
    }

    /**
     * @param kilometrosR the kilometrosR to set
     */
    public void setKilometrosR(int kilometrosR) {
        this.kilometrosR = kilometrosR;
    }

    /**
     * @return the combustibleGastado
     */
    public float getCombustibleGastado() {
        return combustibleGastado;
    }

    /**
     * @param combustibleGastado the combustibleGastado to set
     */
    public void setCombustibleGastado(float combustibleGastado) {
        this.combustibleGastado = combustibleGastado;
    }

    /**
     * @return the running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * @param running the running to set
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    /**
     * @return the posX
     */
    public int getPosX() {
        return posX;
    }

    /**
     * @param posX the posX to set
     */
    public void setPosX(int posX) {
        this.posX = posX;
    }

    /**
     * @return the posY
     */
    public int getPosY() {
        return posY;
    }

    /**
     * @param posY the posY to set
     */
    public void setPosY(int posY) {
        this.posY = posY;
    }

    /**
     * @return the capacidadTanque
     */
    public float getCapacidadTanque() {
        return capacidadTanque;
    }

    /**
     * @param capacidadTanque the capacidadTanque to set
     */
    public void setCapacidadTanque(float capacidadTanque) {
        this.capacidadTanque = capacidadTanque;
    }

    /**
     * @return the gastoKm
     */
    public float getGastoKm() {
        return gastoKm;
    }

    /**
     * @param gastoKm the gastoKm to set
     */
    public void setGastoKm(float gastoKm) {
        this.gastoKm = gastoKm;
    }

    /**
     * @return the btnRecargar
     */
    public JButton getBtnRecargar() {
        return btnRecargar;
    }

    /**
     * @param btnRecargar the btnRecargar to set
     */
    public void setBtnRecargar(JButton btnRecargar) {
        this.btnRecargar = btnRecargar;
    }

    /**
     * @return the btnRegresar
     */
    public JButton getBtnRegresar() {
        return btnRegresar;
    }

    /**
     * @param btnRegresar the btnRegresar to set
     */
    public void setBtnRegresar(JButton btnRegresar) {
        this.btnRegresar = btnRegresar;
    }

    /**
     * @return the parte1
     */
    public boolean isParte1() {
        return parte1;
    }

    /**
     * @param parte1 the parte1 to set
     */
    public void setParte1(boolean parte1) {
        this.parte1 = parte1;
    }

}
