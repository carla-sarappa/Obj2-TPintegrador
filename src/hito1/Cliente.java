package hito1;

import org.joda.time.DateTime;

public class Cliente {
    private String nombre;
    private String apellido;
    private int DNI;
    private DateTime fNac;
    private String direccion;
    private String mail;
    private DateTime ingresoClienteConcesionaria;
    private SolicitudDeAdjudicacion solicitudDeAdjudicacion;

    public Cliente(String nombre, String apellido, int DNI, DateTime fNac, String direccion, String mail, DateTime ingresoClienteConcesionaria) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.fNac = fNac;
        this.direccion = direccion;
        this.mail = mail;
        this.ingresoClienteConcesionaria = ingresoClienteConcesionaria;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDNI() {
        return DNI;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getMail() {
        return mail;
    }

    public DateTime getfNac() {
        return fNac;
    }

    public DateTime getIngresoClienteConcesionaria() {
        return ingresoClienteConcesionaria;
    }

    public SolicitudDeAdjudicacion getSolicitudDeAdjudicacion() {
        return solicitudDeAdjudicacion;
    }

    public void setSolicitudDeAdjudicacion(SolicitudDeAdjudicacion solicitudDeAdjudicacion) {
        this.solicitudDeAdjudicacion = solicitudDeAdjudicacion;
    }

    public void pagarCuota(Concesionaria concesionaria){
        solicitudDeAdjudicacion.agregarPago(new ComprobanteDePago(solicitudDeAdjudicacion, DateTime.now(), concesionaria, this));
    }

    public Double porcentajePagado(){
        return getSolicitudDeAdjudicacion().porcentajePagado();
    }
}

