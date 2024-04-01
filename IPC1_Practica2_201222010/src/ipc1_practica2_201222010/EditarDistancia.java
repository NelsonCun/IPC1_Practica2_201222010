package ipc1_practica2_201222010;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

/**
 *
 * @author nelson
 */

public class EditarDistancia extends JFrame implements ActionListener, KeyListener {
    JLabel code,distancia;
    JTextField codeField,distanciaField;
    JButton btnCancelar, btnAceptar;

    public EditarDistancia() {
        initComplements();
    }
    
    public void initComplements(){
        
        //Label código de la ruta
        code = new JLabel("Ingrese el código de la ruta");
        code.setBounds(24, 15, 200, 30);
        code.setFont(new Font("Arial",Font.BOLD,12));
        code.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(code);
        
        //Label nueva distancia de la ruta
        distancia = new JLabel("Ingrese la nueva distancia");
        distancia.setBounds(24,90,200,30);
        distancia.setFont(new Font("Arial",Font.BOLD,12));
        distancia.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(distancia);
        
        //Campo para ingresar el código de la ruta
        codeField = new JTextField();
        codeField.addKeyListener(this);
        codeField.setBounds(35,45,180,30);
        codeField.setBackground(new Color(255,244,201));
        this.add(codeField);
        
        //Campo para ingresar la nueva distancia
        distanciaField = new JTextField();
        distanciaField.addKeyListener(this);
        distanciaField.setBounds(35,120,180,30);
        distanciaField.setBackground(new Color(255,244,201));
        this.add(distanciaField);
        
        //Botón para cancelar
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(15,170,100,30);
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setBackground(new Color(75,206,208));
        btnCancelar.addActionListener(this);
        this.add(btnCancelar);
        
        //Botón para aceptar
        btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(135,170,100,30);
        btnAceptar.setForeground(Color.WHITE);
        btnAceptar.setBackground(new Color(75,206,208));
        btnAceptar.addActionListener(this);
        this.add(btnAceptar);
        
        ///////////////////////// Config de ventana /////////////////////////
        this.setTitle("EDITAR DISTANCIA");
        this.setLocationRelativeTo(null);
        this.setSize(250, 250);
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
        if (ae.getSource()==btnAceptar) {
            String codigoRuta = codeField.getText();
            String dist = distanciaField.getText();
            if (codigoRuta.isEmpty() || dist.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar los dos campos", "ERROR", 0);
            } else  {
                int intCodigoRuta = Integer.parseInt(codigoRuta);
                float fltDistancia = Float.parseFloat(dist);
                int indiceRuta = -1;
                for (int i = 0; i < Main.rutas.size(); i++) {
                    if (intCodigoRuta == Main.rutas.get(i).getCodigo()) {
                        indiceRuta = i;
                    }
                }
                if (indiceRuta == -1) {
                    JOptionPane.showMessageDialog(this, "No se encontró la ruta especificada", "ERROR", 0);
                } else {
                    Main.rutas.get(indiceRuta).setDistancia(fltDistancia);
                    JOptionPane.showMessageDialog(null, "Distancia actualizada exitosamente",
                "INFORMATION_MESSAGE", JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    MenuInicio newMenu = new MenuInicio();
                }
            }
            //Buscar ruta
            
            
            
        } else if (ae.getSource()==btnCancelar) {
            this.dispose();
            MenuInicio newMenu = new MenuInicio();
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        char entrada = ke.getKeyChar();
        discriminarCaracter(ke, entrada);
       }

    @Override
    public void keyPressed(KeyEvent e) {
        }

    @Override
    public void keyReleased(KeyEvent e) {
       }
    
    private void discriminarCaracter(KeyEvent ke, char entrada) {
        JTextField datoIngresado = (JTextField) ke.getSource();

        if (datoIngresado == codeField) {
        if (!(entrada>=48 && entrada<=57)) {
            ke.consume();
        }
    } else if (datoIngresado == distanciaField) {
        if (!((entrada >= 48 && entrada <= 57) || entrada == 46)) {
                ke.consume();
            }
    }
    }
    
}
