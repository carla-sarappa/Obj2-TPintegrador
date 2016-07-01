package hito1;


import org.joda.time.DateTime;
import org.joda.time.Period;

public class ComprobanteDePago {
    private SolicitudDeAdjudicacion solicitudDeAdjudicacion;
    private DateTime fecha;
    private Double gastosAdminstrativos;
    private Cliente cliente;
    public static final Double COSTO_GASTOS_ADMINISTRATIVOS = 20.0;

    public ComprobanteDePago(SolicitudDeAdjudicacion solicitudDeAdjudicacion, DateTime fecha, Double gastosAdminstrativos, Cliente cliente) {
        this.solicitudDeAdjudicacion = solicitudDeAdjudicacion;
        this.fecha = fecha;
        this.gastosAdminstrativos = gastosAdminstrativos;
        this.cliente = cliente;
    }

    public Cliente getParticipante() {
        return cliente;
    }

    public Double getGastosAdminstrativos() {
        return gastosAdminstrativos;
    }

    public DateTime getFecha() {
        return fecha;
    }

    public int getNumeroDeCuota(){
        return solicitudDeAdjudicacion.getPagosEmitidos().indexOf(this) + 1;
    }

    public Double getAlicuota(){
        return solicitudDeAdjudicacion.getPlanDeAhorro().getAlicuota();
    }

    public Double getSeguroDeVida(){
        int edad = new Period(cliente.getfNac(), DateTime.now()).getYears();
        Double seguro = 50.0;
        if(edad > 50){
            seguro += 10.0*(edad - 50);
        }
        return seguro + solicitudDeAdjudicacion.getValorDeVenta()*0.05;
    }
}