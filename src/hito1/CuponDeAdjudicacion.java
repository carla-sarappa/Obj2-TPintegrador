package hito1;

public class CuponDeAdjudicacion {
    private Double distancia;
    private Double montoAPagarAlMomentoDeLaAdjudicacion;
    private Double precioFletePorKM = 100.0;
    private Cliente cliente;

    public CuponDeAdjudicacion(Double distancia, Double montoAPagarAlMomentoDeLaAdjudicacion, Cliente cliente) {
        this.distancia = distancia;
        this.montoAPagarAlMomentoDeLaAdjudicacion = montoAPagarAlMomentoDeLaAdjudicacion;
        this.cliente = cliente;
    }

    public Double getGastoDeFlete(){
        return distancia * precioFletePorKM;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Double getMontoAPagarAlMomentoDeLaAdjudicacion() {
        return montoAPagarAlMomentoDeLaAdjudicacion;
    }
}
