package hito1;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PagoTest {
    private Cliente cliente;
    private ComprobanteDePago comprobante;
    private PlanDeAhorro planDeAhorro;
    private ModeloAuto modelo;
    private Financiamiento100 financiamiento100;
    private FormaAdjudicacionAlAzar formaAdjAlAzar;

    @Before
    public void setUp() {
        modelo = new ModeloAuto();
        financiamiento100 = new Financiamiento100();
        formaAdjAlAzar = new FormaAdjudicacionAlAzar();
        planDeAhorro = new PlanDeAhorro(1, modelo, financiamiento100, formaAdjAlAzar, 12);
        cliente = new Cliente("Carlis", "Sarasa", 3949094, new DateTime("1950-11-11"), "sarasa", "sarasa", new DateTime("2016-06-16"));
        comprobante = new ComprobanteDePago((new SolicitudDeAdjudicacion(planDeAhorro, new DateTime("2016-06-01"))), new DateTime("2016-06-30"), 20.0, cliente);
    }

    @Test
    public void testSeguroDeVida_SiEsMenorA50() {
        Cliente cliente = mock(Cliente.class);
        when(cliente.getfNac()).thenReturn(new DateTime("1992-06-01"));

        SolicitudDeAdjudicacion solicitudDeAdjudicacion = mock(SolicitudDeAdjudicacion.class);
        when(solicitudDeAdjudicacion.getValorDeVenta()).thenReturn(1000.0);

        ComprobanteDePago comprobante = new ComprobanteDePago(solicitudDeAdjudicacion, new DateTime("2016-06-30"), 20.0, cliente);
        assertThat(comprobante.getSeguroDeVida(), equalTo(100.0));
    }

    @Test
    public void testSeguroDeVida_SiEsMayorA50() {
        Cliente cliente = mock(Cliente.class);
        when(cliente.getfNac()).thenReturn(new DateTime("1965-06-01"));

        SolicitudDeAdjudicacion solicitudDeAdjudicacion = mock(SolicitudDeAdjudicacion.class);
        when(solicitudDeAdjudicacion.getValorDeVenta()).thenReturn(1000.0);

        ComprobanteDePago comprobante = new ComprobanteDePago(solicitudDeAdjudicacion, new DateTime("2016-06-30"), 20.0, cliente);
        assertThat(comprobante.getSeguroDeVida(), equalTo(110.0));
    }


}
