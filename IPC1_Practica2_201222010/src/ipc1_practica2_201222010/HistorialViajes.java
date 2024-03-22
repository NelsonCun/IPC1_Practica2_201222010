package ipc1_practica2_201222010;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author nelson
 */

public class HistorialViajes extends JFrame implements ActionListener {
    JLabel title;
    JTable historial;
    JScrollPane sc1;
    JButton btnSalir;
    
    public HistorialViajes(){
        initComponents();
    }
    
    public void initComponents(){
        //////////////////////////////////// TÍTULO ////////////////////////////////////
        title = new JLabel("Historial de viajes");
        title.setBounds(50,30,700,50);
        title.setFont(new Font("Arial",Font.BOLD,25));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(title);
        
        ////////////////////////////////// TABLA VIAJES ///////////////////////////////
        String[] titulos_v = {"Código", "Fecha y hora inicio","Fecha y hora fin","Distancia (km)","Vehículo","Gasolina consumida"};
        //String[][] viajes = {{"001","03-05-2024 10:00","03-05-2024 12:00","35","Estándar","2"},
          //  {"002","06-05-2024 08:00","06-05-2024 10:00","13","Moto","3"},
        //{"003","06-05-2024 12:00","06-05-2024 13:00","5","Premium","1"},
        //{"004","07-05-2024 13:00","07-05-2024 17:00","20","Moto","12"},
        //{"005","07-05-2024 14:00","07-05-2024 20:00","27","Premium","15"}};
        historial = new JTable(Main.Historial(),titulos_v);
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        for (int i = 0; i < titulos_v.length; i++) {
            historial.getColumnModel().getColumn(i).setCellRenderer(Alinear);
        }
        historial.setEnabled(false);
        this.historial.setOpaque(false);
        resizeColumnWidth(historial);
        sc1 = new JScrollPane(historial);
        sc1.setBounds(50,80,700,250);
        this.sc1.setOpaque(false);
        sc1.setVisible(true);
        this.add(sc1);
        
        ////////////////////////////// BOTÓN SALIR ////////////////////////////////////
        btnSalir = new JButton("Cerrar");
        btnSalir.setBounds(650,350,100,30);
        btnSalir.setForeground(Color.WHITE);
        btnSalir.setBackground(new Color(188, 38, 38));
        btnSalir.addActionListener(this);
        this.add(btnSalir);
        
        
        ///////////////////////// CONFIGURACIÓN DE VENTANA /////////////////////////////
        this.setTitle("Historial de viajes");
        this.setLocationRelativeTo(null);
        this.setSize(800, 450);
        setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==btnSalir) {
            this.dispose();
        }
    }
    
    public void resizeColumnWidth (JTable table){
        final TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 40; //min width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer,row,column);
                width = Math.max(comp.getPreferredSize().width+1, width);
            }
            if (width>300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width);
        }
    }
}
