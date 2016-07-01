package hito1;

import org.joda.time.DateTime;

public class ModeloAuto {
    private String nombreModelo;
    private DateTime anioLanzamiento;
    private int cantidadPuertas;
    private boolean base;
    private Double valorDeVenta;

    public ModeloAuto(String nombreModelo, DateTime anioLanzamiento, int cantidadPuertas, boolean base, Double valorDeVenta) {
        this.nombreModelo = nombreModelo;
        this.anioLanzamiento = anioLanzamiento;
        this.cantidadPuertas = cantidadPuertas;
        this.base = base;
        this.valorDeVenta = valorDeVenta;
    }

    public Double getValorDeVenta() {
        return valorDeVenta;
    }

    public boolean isBase(){
        return base;
    }

    public boolean isFull(){
        return !base;
    }


}
