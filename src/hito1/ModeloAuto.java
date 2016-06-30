package hito1;

import org.joda.time.DateTime;

public class ModeloAuto {
    private String nombreModelo;
    private DateTime anioLanzamiento;
    private int cantidadPuertas;
    private String baseOFull;
    private Double valorDeVenta;

    public void setValorDeVenta(Double valorDeVenta) {
        this.valorDeVenta = valorDeVenta;
    }

    public Double getValorDeVenta() {
        return valorDeVenta;
    }
}
