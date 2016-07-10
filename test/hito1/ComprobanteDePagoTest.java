package hito1;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ComprobanteDePagoTest {
    private ComprobanteDePago comprobanteDePago;
    private SolicitudDeAdjudicacion solicitudDeAdjudicacion;
    private Cliente cliente;
    private PlanDeAhorro planDeAhorro;
    private ModeloAuto modelo;
    private Financiamiento100 financiamiento100;
    private FormaAdjudicacionAlAzar formaAdjAlAzar;


    @Before
    public void setUp(){
        solicitudDeAdjudicacion = mock(SolicitudDeAdjudicacion.class);
        cliente = mock(Cliente.class);
        comprobanteDePago = new ComprobanteDePago(solicitudDeAdjudicacion, new DateTime("2016-06-29"), 20.0, cliente);
        modelo = mock(ModeloAuto.class);
        financiamiento100 = new Financiamiento100();
        formaAdjAlAzar = new FormaAdjudicacionAlAzar();
        planDeAhorro = new PlanDeAhorro(1, modelo, financiamiento100, formaAdjAlAzar, 12);
    }

    @Test
    public void testGetParticipante(){
        assertEquals(cliente, comprobanteDePago.getParticipante());
    }

    @Test
    public void testGetGastosAdministrativos(){
        assertThat(comprobanteDePago.getGastosAdminstrativos(), equalTo(20.0));
    }

    @Test
    public void testGetFecha(){
        assertEquals(new DateTime("2016-06-29"), comprobanteDePago.getFecha());
    }

    @Test
    public void testGetNumeroDeCuota(){
        List<ComprobanteDePago> comprobantes = new ArrayList<>();
        comprobantes.add(comprobanteDePago);

        when(solicitudDeAdjudicacion.getPagosEmitidos()).thenReturn(comprobantes);

        assertThat(comprobanteDePago.getNumeroDeCuota(), equalTo(1));
    }

    @Test
    public void testGetAlicuota(){
        PlanDeAhorro planDeAhorro = mock(PlanDeAhorro.class);
        when(planDeAhorro.getAlicuota()).thenReturn(123.0);
        when(solicitudDeAdjudicacion.getPlanDeAhorro()).thenReturn(planDeAhorro);

        assertThat(comprobanteDePago.getAlicuota(), equalTo(123.0));
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
