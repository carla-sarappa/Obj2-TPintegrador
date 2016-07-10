package hito1;

public class CuponDeAdjudicacion {
    private Double distancia;
    private Double precioFletePorKM = 100.0;
    private PlanDeAhorro planDeAhorro;
    private Cliente cliente;

    public CuponDeAdjudicacion(Double distancia, PlanDeAhorro planDeAhorro, Cliente cliente) {
        this.distancia = distancia;
        this.planDeAhorro = planDeAhorro;
        this.cliente = cliente;
    }

    public Double getGastoDeFlete(){
        return distancia * precioFletePorKM;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Double getMontoAPagarAlMomentoDeLaAdjudicacion() {

        return planDeAhorro.montoAPagarEnElMomentoDeAdjudicacion();
    }
}
