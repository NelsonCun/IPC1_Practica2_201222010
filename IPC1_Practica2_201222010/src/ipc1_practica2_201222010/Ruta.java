package ipc1_practica2_201222010;

/**
 *
 * @author nelson
 */

public class Ruta {
    private int codigo;
    private String inicio;
    private String fin;
    private float distancia;
    public static int codigoRuta = 001;

    public Ruta(String inicio, String fin, float distancia) {
        this.codigo = codigoRuta;
        this.inicio = inicio;
        this.fin = fin;
        this.distancia = distancia;
    }

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
     * @return the fin
     */
    public String getFin() {
        return fin;
    }

    /**
     * @param fin the fin to set
     */
    public void setFin(String fin) {
        this.fin = fin;
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

    
}
