package ipc1_practica2_201222010;

import java.util.ArrayList;

/**
 *
 * @author nelson
 */

public class MatrizRutas {

    public MatrizRutas() {
    }
    
    public static Object[][] convertirRutas(){
        int filas = Main.rutas.size();
        String[][] arregloRutas = new String[filas][4];
        for (int i = 0; i < filas; i++) {
            String strCodigo = Integer.toString(Main.rutas.get(i).getCodigo());
            String strDistancia = Float.toString(Main.rutas.get(i).getDistancia());
            arregloRutas[i][0]=strCodigo;
            arregloRutas[i][1]=Main.rutas.get(i).getInicio();
            arregloRutas[i][2]=Main.rutas.get(i).getFin();
            arregloRutas[i][3]=strDistancia;
        }
        return arregloRutas;
    }
}
