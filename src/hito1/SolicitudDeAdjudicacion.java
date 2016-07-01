package hito1;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

public class SolicitudDeAdjudicacion {
    private List<ComprobanteDePago> pagosEmitidos = new ArrayList<>();
    private PlanDeAhorro planDeAhorro;
    private DateTime fechaIngresoPlan;

    public SolicitudDeAdjudicacion(PlanDeAhorro planDeAhorro, DateTime fechaIngresoPlan) {
        this.planDeAhorro = planDeAhorro;
        this.fechaIngresoPlan  = fechaIngresoPlan;
    }

    public Double montoPagadoHastaElMomento(){
        return planDeAhorro.getAlicuota() * pagosEmitidos.size();
    }

    public Double porcentajePagado(){
        return (montoPagadoHastaElMomento() * 100 / planDeAhorro.getModeloAuto().getValorDeVenta());
    }

    public DateTime getFechaIngresoPlan() {
        return fechaIngresoPlan;
    }

    public List<ComprobanteDePago> getPagosEmitidos() {
        return pagosEmitidos;
    }

    public void agregarPago(ComprobanteDePago comprobanteDePago){
        if (planDeAhorro.getCantidadDeCuotas() <= pagosEmitidos.size()){
            throw new RuntimeException("No quedan cuotas a pagar");
        }
        pagosEmitidos.add(comprobanteDePago);
    }

    public PlanDeAhorro getPlanDeAhorro() {
        return planDeAhorro;
    }

    public Double getValorDeVenta(){
        return planDeAhorro.getModeloAuto().getValorDeVenta();
    }
}
