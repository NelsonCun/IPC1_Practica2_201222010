package ipc1_practica2_201222010;

/**
 *
 * @author nelson
 */
class Transporte {
    private String tipoTransporte;
    private float gastoCombustible;
    private float capacidadTanque;
    private float cantidadCombustible;

    public Transporte(String tipoTransporte, float gastoCombustible, float capacidadTanque) {
        this.tipoTransporte = tipoTransporte;
        this.gastoCombustible = gastoCombustible;
        this.capacidadTanque = capacidadTanque;
        this.cantidadCombustible = capacidadTanque;
    }

    /**
     * @return the tipoTransporte
     */
    public String getTipoTransporte() {
        return tipoTransporte;
    }

    /**
     * @param tipoTransporte the tipoTransporte to set
     */
    public void setTipoTransporte(String tipoTransporte) {
        this.tipoTransporte = tipoTransporte;
    }

    /**
     * @return the gastoCombustible
     */
    public float getGastoCombustible() {
        return gastoCombustible;
    }

    /**
     * @param gastoCombustible the gastoCombustible to set
     */
    public void setGastoCombustible(float gastoCombustible) {
        this.gastoCombustible = gastoCombustible;
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
     * @return the cantidadCombustible
     */
    public float getCantidadCombustible() {
        return cantidadCombustible;
    }

    /**
     * @param cantidadCombustible the cantidadCombustible to set
     */
    public void setCantidadCombustible(float cantidadCombustible) {
        this.cantidadCombustible = cantidadCombustible;
    }
    
    
}
