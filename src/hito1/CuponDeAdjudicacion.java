package hito1;

public class CuponDeAdjudicacion {
    private Double distancia;
    private Double montoAPagarAlMomentoDeLaAdjudicacion;
    private Double precioFletePorKM = 100.0;

    public CuponDeAdjudicacion(Double distancia, Double montoAPagarAlMomentoDeLaAdjudicacion) {
        this.distancia = distancia;
        this.montoAPagarAlMomentoDeLaAdjudicacion = montoAPagarAlMomentoDeLaAdjudicacion;
    }

    public Double getGastoDeFlete(){
        return distancia * precioFletePorKM;

    }

    public Double getMontoAPagarAlMomentoDeLaAdjudicacion() {
        return montoAPagarAlMomentoDeLaAdjudicacion;
    }
}
