package hito1;

import org.joda.time.DateTime;

public class ModeloAuto {
    private String nombreModelo;
    private DateTime anioLanzamiento;
    private int cantidadPuertas;
    private String baseOFull;
    private Double valorDeVenta;

    public ModeloAuto() {
    }

    public ModeloAuto(Double valorDeVenta) {
        this.valorDeVenta = valorDeVenta;
    }

    public void setValorDeVenta(Double valorDeVenta) {
        this.valorDeVenta = valorDeVenta;
    }

    public Double getValorDeVenta() {
        return valorDeVenta;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public DateTime getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(DateTime anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    public int getCantidadPuertas() {
        return cantidadPuertas;
    }

    public void setCantidadPuertas(int cantidadPuertas) {
        this.cantidadPuertas = cantidadPuertas;
    }

    public String getBaseOFull() {
        return baseOFull;
    }

    public void setBaseOFull(String baseOFull) {
        this.baseOFull = baseOFull;
    }
}
