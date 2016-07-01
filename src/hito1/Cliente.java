package hito1;

import org.joda.time.DateTime;

public class Cliente {
    private String nombre;
    private String apellido;
    private int DNI;
    private DateTime fNac;
    private String direccion;
    private String mail;
    private DateTime ingresoCliente;
    private SolicitudDeAdjudicacion solicitudDeAdjudicacion;

    public Cliente(String nombre, String apellido, int DNI, DateTime fNac, String direccion, String mail, DateTime ingresoCliente) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.fNac = fNac;
        this.direccion = direccion;
        this.mail = mail;
        this.ingresoCliente = ingresoCliente;
    }

    public DateTime getfNac() {
        return fNac;
    }

    public DateTime getIngresoCliente() {
        return ingresoCliente;
    }

    public SolicitudDeAdjudicacion getSolicitudDeAdjudicacion() {
        return solicitudDeAdjudicacion;
    }

    public void setSolicitudDeAdjudicacion(SolicitudDeAdjudicacion solicitudDeAdjudicacion) {
        this.solicitudDeAdjudicacion = solicitudDeAdjudicacion;
    }

    public void pagarCuota(){
        solicitudDeAdjudicacion.agregarPago(new ComprobanteDePago(solicitudDeAdjudicacion, DateTime.now(), ComprobanteDePago.COSTO_GASTOS_ADMINISTRATIVOS, this));
    }
}

