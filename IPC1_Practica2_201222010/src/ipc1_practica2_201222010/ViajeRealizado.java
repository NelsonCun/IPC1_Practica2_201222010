package ipc1_practica2_201222010;

import java.io.Serializable;

/**
 *
 * @author nelson
 */
class ViajeRealizado implements Serializable {
    private int id;
    private String tiempoInicio;
    private String tiempoFin;
    private int distancia;
    private String vehiculo;
    private float Gasolina;
    static int numero= 1;

    public ViajeRealizado(String tiempoInicio, String tiempoFin, int distancia, String vehiculo, float Gasolina) {
        this.id = numero;
        this.tiempoInicio = tiempoInicio;
        this.tiempoFin = tiempoFin;
        this.distancia = distancia;
        this.vehiculo = vehiculo;
        this.Gasolina = Gasolina;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the tiempoInicio
     */
    public String getTiempoInicio() {
        return tiempoInicio;
    }

    /**
     * @param tiempoInicio the tiempoInicio to set
     */
    public void setTiempoInicio(String tiempoInicio) {
        this.tiempoInicio = tiempoInicio;
    }

    /**
     * @return the tiempoFin
     */
    public String getTiempoFin() {
        return tiempoFin;
    }

    /**
     * @param tiempoFin the tiempoFin to set
     */
    public void setTiempoFin(String tiempoFin) {
        this.tiempoFin = tiempoFin;
    }

    /**
     * @return the distancia
     */
    public int getDistancia() {
        return distancia;
    }

    /**
     * @param distancia the distancia to set
     */
    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    /**
     * @return the vehiculo
     */
    public String getVehiculo() {
        return vehiculo;
    }

    /**
     * @param vehiculo the vehiculo to set
     */
    public void setVehiculo(String vehiculo) {
        this.vehiculo = vehiculo;
    }

    /**
     * @return the Gasolina
     */
    public float getGasolina() {
        return Gasolina;
    }

    /**
     * @param Gasolina the Gasolina to set
     */
    public void setGasolina(float Gasolina) {
        this.Gasolina = Gasolina;
    }

    /**
     * @return the numero
     */
    public static int getNumero() {
        return numero;
    }

    /**
     * @param aNumero the numero to set
     */
    public static void setNumero(int aNumero) {
        numero = aNumero;
    }
    
    
}
