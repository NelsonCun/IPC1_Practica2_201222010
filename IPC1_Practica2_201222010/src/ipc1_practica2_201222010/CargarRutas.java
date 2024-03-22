package ipc1_practica2_201222010;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author nelson
 */

public class CargarRutas extends JFrame {
    static JFileChooser subirArchivo;

    public CargarRutas() {
        initComponents();
    }
    
    public void initComponents(){
        subirArchivo = new JFileChooser();
        subirArchivo.setBounds(9, 0, 555, 375);
        this.add(subirArchivo);
        
        this.setTitle("Subir archivo");
        this.setLocationRelativeTo(null);
        this.setSize(550, 400);
        setLocationRelativeTo(null);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setVisible(true);
    }
    
    public static void LecturaCSV(JFrame frame){
        subirArchivo = new JFileChooser();
        
            FileNameExtensionFilter filter = new FileNameExtensionFilter ("Archivos CSV", "csv");
        subirArchivo.setFileFilter(filter);
        
        int result = subirArchivo.showOpenDialog(frame);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = subirArchivo.getSelectedFile();
            System.out.println("=============================================================");
            System.out.println("Archivo seleccionado: "+ selectedFile.getAbsolutePath());
            
            try {
                Scanner scanner = new Scanner(selectedFile);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(",");
                    try {
                        String inicio = parts[0];
                        String fin = parts[1];
                        float km = Float.parseFloat(parts[2]);
                        Ruta ruta = new Ruta(inicio,fin,km);
                        Main.addRuta(ruta);
                        Ruta.codigoRuta++;
                    
                    } catch (Exception e) {
                    }
                        
                    System.out.println();
                }
                scanner.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
            
        }
        MenuInicio newInicio = new MenuInicio();
    }
    
}

