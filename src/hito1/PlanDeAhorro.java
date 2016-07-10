package hito1;

import java.util.ArrayList;
import java.util.List;

public class PlanDeAhorro {
    private final int numeroGrupo;
    private final ModeloAuto modeloAuto;
    private List<Cliente> suscriptosPlan = new ArrayList<>();
    private Financiamiento financiamiento;
    private FormaAdjudicacion formaAdjudicacion;
    private final int cantidadDeCuotas;

    public PlanDeAhorro(int numeroGrupo, ModeloAuto modeloAuto, Financiamiento financiamiento, FormaAdjudicacion formaAdjudicacion, int cantidadDeCuotas) {
        this.numeroGrupo = numeroGrupo;
        this.modeloAuto = modeloAuto;
        this.financiamiento = financiamiento;
        this.formaAdjudicacion = formaAdjudicacion;
        this.cantidadDeCuotas = cantidadDeCuotas;
    }

    public int getCantidadDeCuotas() {
        return cantidadDeCuotas;
    }

    public void agregarSuscriptor(Cliente cliente) {
        suscriptosPlan.add(cliente);
    }

    public ModeloAuto getModeloAuto() {
        return modeloAuto;
    }

    public Integer getCantidadDeSuscriptos(){
        return suscriptosPlan.size();
    }

    public Financiamiento getFinanciamiento() {
        return financiamiento;
    }

    public Double getAlicuota() {
        return financiamiento.montoAPagarEnCuotas(modeloAuto) / cantidadDeCuotas;
    }

    public Cliente clienteAAdjudicar() {
        return formaAdjudicacion.siguienteClienteAAdjudicar(suscriptosPlan).get();

    }
}
