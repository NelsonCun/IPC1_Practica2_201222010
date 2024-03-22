package ipc1_practica2_201222010;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author nelson
 */
class Viaje extends JFrame implements ActionListener {

    private String transporte;
    private float distancia;
    private String destino;
    private String inicio;
    private int numeroViaje;
    private JPanel jp1;
    private JLabel lblTransporte;
    private JLabel lblDistancia;
    private JLabel lblDestino;
    private JLabel lblInicio;
    private JLabel lblRecorrido;
    private JLabel lblGasActual;
    private JLabel lblPista,lblITransp;
    private JButton btnIniciar, btnRegresar;
    public static int conteoViaje = 0;

    public Viaje(int numeroViaje, String inicio, String destino, String transporte,float distancia) {
        this.inicio = inicio;
        this.destino = destino;
        this.transporte = transporte;
        this.numeroViaje = numeroViaje;
        this.distancia = distancia;
        iniciarComponentes();
    }

    public void iniciarComponentes() {
        // Panel
        jp1=new JPanel();
        jp1.setLayout(null);
        //jp1.setBackground(Color.YELLOW);
        jp1.setPreferredSize(new Dimension(900, 120));

        //Label transporte
        lblTransporte = new JLabel(this.getTransporte());
        lblTransporte.setFont(new Font("Arial", Font.BOLD, 12));
        lblTransporte.setBounds(5, 5, 150, 20);
        lblTransporte.setForeground(Color.BLACK);
        lblTransporte.setVerticalAlignment(SwingConstants.CENTER);
        jp1.add(lblTransporte);
        
        //Label Distancia
        lblDistancia = new JLabel("Distancia: " + this.getDistancia() + "km");
        lblDistancia.setFont(new Font("Arial",Font.BOLD,12));
        lblDistancia.setBounds(5,25,150,20);
        lblDistancia.setForeground(Color.BLACK);
        lblDistancia.setVerticalAlignment(SwingConstants.CENTER);
        jp1.add(lblDistancia);
        
        //Label Destino
        lblDestino = new JLabel("Destino: " + this.getDestino());
        lblDestino.setFont(new Font("Arial",Font.BOLD,12));
        lblDestino.setBounds(5,45,200,20);
        lblDestino.setForeground(Color.BLACK);
        lblDestino.setVerticalAlignment(SwingConstants.CENTER);
        jp1.add(lblDestino);
        
        //Label Inicio
        lblInicio = new JLabel("Inicio: " + this.getInicio());
        lblInicio.setFont(new Font("Arial",Font.BOLD,12));
        lblInicio.setBounds(695,30,200,20);
        lblInicio.setForeground(Color.BLACK);
        lblInicio.setVerticalAlignment(SwingConstants.CENTER);
        jp1.add(lblInicio);
        
        //Botón iniciar
        btnIniciar = new JButton("Iniciar");
        btnIniciar.setBounds(5,80,100,25);
        btnIniciar.setBackground(Color.BLUE);
        btnIniciar.setForeground(Color.white);
        btnIniciar.setEnabled(true);
        jp1.add(btnIniciar);
        
        //Botón Regresar
        btnRegresar = new JButton("Regresar");
        btnRegresar.setBounds(695,60,100,25);
        btnRegresar.setBackground(Color.BLUE);
        btnRegresar.setForeground(Color.white);
        btnRegresar.setEnabled(true);
        jp1.add(btnRegresar);
        
        //Pista
        lblPista = new JLabel();
        lblPista.setBounds(125,105,565,10);
        ImageIcon img = new ImageIcon(getClass().getResource("./images/iCarretera.png"));
        Image nuevo = img.getImage().getScaledInstance(565,10,Image.SCALE_SMOOTH);
        lblPista.setHorizontalAlignment(SwingConstants.CENTER);
        lblPista.setVerticalAlignment(SwingConstants.CENTER);
        ImageIcon render = new ImageIcon(nuevo);
        lblPista.setIcon(render);
        lblPista.setVisible(true);
        jp1.add(lblPista);
        
        //Imagen Transporte
        lblITransp = new JLabel();
        lblITransp.setBounds(125,50,75,56);
        ImageIcon imgT = new ImageIcon(getClass().getResource("./images/imoto1.png"));
        if (this.getTransporte().equals("Motocicleta 1")) {
            imgT = new ImageIcon(getClass().getResource("./images/imoto1.png"));
        } else if (this.getTransporte().equals("Motocicleta 2")) {
            imgT = new ImageIcon(getClass().getResource("./images/imoto2.png"));
        } else if (this.getTransporte().equals("Motocicleta 3")) {
            imgT = new ImageIcon(getClass().getResource("./images/imoto3.png"));
        } else if (this.getTransporte().equals("Vehículo estándar 1")) {
            imgT = new ImageIcon(getClass().getResource("./images/iestandar1.png"));
        } else if (this.getTransporte().equals("Vehículo estándar 2")) {
            imgT = new ImageIcon(getClass().getResource("./images/iestandar2.png"));
        } else if (this.getTransporte().equals("Vehículo estándar 3")) {
            imgT = new ImageIcon(getClass().getResource("./images/iestandar3.png"));
        } else if (this.getTransporte().equals("Vehículo prémium 1")) {
            imgT = new ImageIcon(getClass().getResource("./images/ipremium1.png"));
        } else if (this.getTransporte().equals("Vehículo prémium 2")) {
            imgT = new ImageIcon(getClass().getResource("./images/ipremium2.png"));
        } else if (this.getTransporte().equals("Vehículo prémium 3")) {
            imgT = new ImageIcon(getClass().getResource("./images/ipremium3.png"));
        }
        
        Image nuevoT = imgT.getImage().getScaledInstance(75,56,Image.SCALE_SMOOTH);
        lblITransp.setHorizontalAlignment(SwingConstants.CENTER);
        lblITransp.setVerticalAlignment(SwingConstants.CENTER);
        ImageIcon renderT = new ImageIcon(nuevoT);
        lblITransp.setIcon(renderT);
        lblITransp.setVisible(true);
        jp1.add(lblITransp);
        

        jp1.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }

    /**
     * @return the transporte
     */
    public String getTransporte() {
        return transporte;
    }

    /**
     * @param transporte the transporte to set
     */
    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    /**
     * @return the distancia
     */
    public float getDistancia() {
        return distancia;
    }

    /**
     * @param distancia the distancia to set
     */
    public void setDistancia(float distancia) {
        this.distancia = distancia;
    }

    /**
     * @return the destino
     */
    public String getDestino() {
        return destino;
    }

    /**
     * @param destino the destino to set
     */
    public void setDestino(String destino) {
        this.destino = destino;
    }

    /**
     * @return the inicio
     */
    public String getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    /**
     * @return the jp1
     */
    public JPanel getJp1() {
        return jp1;
    }

    /**
     * @param jp1 the jp1 to set
     */
    public void setJp1(JPanel jp1) {
        this.jp1 = jp1;
    }

    /**
     * @return the lblRecorrido
     */
    public JLabel getLblRecorrido() {
        return lblRecorrido;
    }

    /**
     * @param lblRecorrido the lblRecorrido to set
     */
    public void setLblRecorrido(JLabel lblRecorrido) {
        this.lblRecorrido = lblRecorrido;
    }

    /**
     * @return the lblGasActual
     */
    public JLabel getLblGasActual() {
        return lblGasActual;
    }

    /**
     * @param lblGasActual the lblGasActual to set
     */
    public void setLblGasActual(JLabel lblGasActual) {
        this.lblGasActual = lblGasActual;
    }

    /**
     * @return the numeroViaje
     */
    public int getNumeroViaje() {
        return numeroViaje;
    }

    /**
     * @param numeroViaje the numeroViaje to set
     */
    public void setNumeroViaje(int numeroViaje) {
        this.numeroViaje = numeroViaje;
    }

}
