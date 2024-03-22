package ipc1_practica2_201222010;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ColorUIResource;

/**
 *
 * @author Nelson Cún - 201222010
 */
public class Main {

    static int ventanaAbierta = 0; //0: Menu inicio, 1: Generar viaje
    static ArrayList<Ruta> rutas = new ArrayList<>();
    static ArrayList<Transporte> transportes = new ArrayList<>();
    static ArrayList<Viaje> viajes = new ArrayList<>();
    static ArrayList<ViajeRealizado> viajesRealizados = new ArrayList<>();

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.mcwin.McWinLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }

        Login login = new Login();
        addTransporte();

    }
    
    public static void addTransporte(){
        Transporte moto1 = new Transporte("Motocicleta 1", (float) 0.1,6);
        Transporte moto2 = new Transporte("Motocicleta 2", (float) 0.1,6);
        Transporte moto3 = new Transporte("Motocicleta 3", (float) 0.1,6);
        Transporte estandar1 = new Transporte("Vehículo estándar 1", (float) 0.3,10);
        Transporte estandar2 = new Transporte("Vehículo estándar 2", (float) 0.3,10);
        Transporte estandar3 = new Transporte("Vehículo estándar 3", (float) 0.3,10);
        Transporte premium1 = new Transporte("Vehículo prémium 1", (float) 0.45,12);
        Transporte premium2 = new Transporte("Vehículo prémium 2", (float) 0.45,12);
        Transporte premium3 = new Transporte("Vehículo prémium 3", (float) 0.45,12);
        transportes.add(moto1);
        transportes.add(moto2);
        transportes.add(moto3);
        transportes.add(estandar1);
        transportes.add(estandar2);
        transportes.add(estandar3);
        transportes.add(premium1);
        transportes.add(premium2);
        transportes.add(premium3);
    }

    public static void addRuta(Ruta ruta) {
        rutas.add(ruta);
    }

    //Listado de lugares
    public static String[] Lugares() {
        ArrayList<String> lugares = new ArrayList<>();
        for (int i = 0; i < rutas.size(); i++) {
            boolean agregar = true;
            for (int j = 0; j < rutas.size(); j++) {
                if ((rutas.get(i).getInicio().equals(rutas.get(j).getInicio())) && (i != j)) {
                    agregar = false;
                }
            }
            if (agregar == true) {
                lugares.add(rutas.get(i).getInicio());
            }
        }
        for (int i = 0; i < rutas.size(); i++) {
            boolean agregar = true;
            for (int j = 0; j < rutas.size(); j++) {
                if (rutas.get(i).getFin().equals(rutas.get(j).getInicio())) {
                    agregar = false;
                } else {
                    if ((rutas.get(i).getFin().equals(rutas.get(j).getFin())) && (i != j)) {
                        agregar = false;
                    }
                }
            }
            if (agregar == true) {
                lugares.add(rutas.get(i).getFin());
            }
        }
        String[] arregloLugares = new String[lugares.size() + 1];
        arregloLugares[0] = "Seleccionar";
        for (int i = 1; i < arregloLugares.length; i++) {
            arregloLugares[i] = lugares.get(i - 1);
        }
        return arregloLugares;
    }
    
    //Listado de transportes
    public static String[] Transportes(){
        String[] arregloTransportes = new String[transportes.size()+1];
        arregloTransportes[0]="Seleccionar";
        for (int i = 0; i < transportes.size(); i++) {
            arregloTransportes[i+1]=transportes.get(i).getTipoTransporte();
        }
        return arregloTransportes;
    }
    
    public static void addViaje(Viaje viaje){
        viajes.add(viaje);
    }
    
    public static void addViajeRealizado(ViajeRealizado viaje){
        viajesRealizados.add(viaje);
    }
    
    public static Object[][] Historial(){
        String[][] arregloHistorial = new String[viajesRealizados.size()][6];
        for (int i = 0; i < viajesRealizados.size(); i++) {
            arregloHistorial[i][0] = ""+viajesRealizados.get(i).getId();
            arregloHistorial[i][1] = viajesRealizados.get(i).getTiempoInicio();
            arregloHistorial[i][2] = viajesRealizados.get(i).getTiempoFin();
            arregloHistorial[i][3] = ""+viajesRealizados.get(i).getDistancia();
            arregloHistorial[i][4] = viajesRealizados.get(i).getVehiculo();
            arregloHistorial[i][5] = ""+viajesRealizados.get(i).getGasolina();
        }
        return arregloHistorial;
    }

}
