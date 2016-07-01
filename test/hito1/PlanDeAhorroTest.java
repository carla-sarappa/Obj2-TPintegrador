package hito1;

import fixture.FixturePlanesDeAhorro;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PlanDeAhorroTest {
    private ModeloAuto modeloAuto;
    private PlanDeAhorro planDeAhorroCobertura;
    private PlanDeAhorro planDeAhorroAzar;
    private FormaAdjudicacionPorMayorCobertura formaAdjudicacionPorMayorCobertura;
    private FormaAdjudicacionAlAzar formaAdjudicacionAlAzar;
    private Cliente cliente1;
    private Cliente cliente2;
    private Financiamiento financiamiento;


    @Before
    public void setUp(){
        financiamiento = mock(Financiamiento.class);
        modeloAuto = mock(ModeloAuto.class);
        formaAdjudicacionPorMayorCobertura = new FormaAdjudicacionPorMayorCobertura();
        planDeAhorroCobertura = new PlanDeAhorro(1, modeloAuto, financiamiento, formaAdjudicacionPorMayorCobertura, 12);
        formaAdjudicacionAlAzar = new FormaAdjudicacionAlAzar();
        planDeAhorroAzar = new PlanDeAhorro(2, modeloAuto, financiamiento, formaAdjudicacionAlAzar, 24);
        cliente1 = new Cliente("cliente 1", "Sasfrasdk", 3383888, new DateTime("1909-03-03"), "Capital","carldsffffrappa@gmail.com", new DateTime("2014-05-01"));
        cliente2 = new Cliente("cliente 2", "Sasfrasdk", 3383888, new DateTime("1909-03-03"), "Capital","carldsffffrappa@gmail.com", new DateTime("2014-05-01"));

    }


    @Test
    public void testEjecutaLasAdjudicacionesDeClientesConMayorCobertura(){
        SolicitudDeAdjudicacion solicitudDeAdjudicacion1 = mock(SolicitudDeAdjudicacion.class);
        cliente1.setSolicitudDeAdjudicacion(solicitudDeAdjudicacion1);
        when(solicitudDeAdjudicacion1.porcentajePagado()).thenReturn(90.0);

        SolicitudDeAdjudicacion solicitudDeAdjudicacion2 = mock(SolicitudDeAdjudicacion.class);
        cliente2.setSolicitudDeAdjudicacion(solicitudDeAdjudicacion2);
        when(solicitudDeAdjudicacion2.porcentajePagado()).thenReturn(70.0);

        planDeAhorroCobertura.agregarSuscriptor(cliente1);
        planDeAhorroCobertura.agregarSuscriptor(cliente2);

        assertThat(planDeAhorroCobertura.clienteAAdjudicar(), equalTo(cliente1));
    }

    @Test
    public void testEjecutaLasAdjudicacionesDeClientesAlAzar(){
        planDeAhorroAzar.agregarSuscriptor(cliente1);
        planDeAhorroAzar.agregarSuscriptor(cliente2);
        FormaAdjudicacionAlAzar.Azar azar = mock(FormaAdjudicacionAlAzar.Azar.class);
        formaAdjudicacionAlAzar.setAzar(azar);

        when(azar.siguienteNumeroHasta(2)).thenReturn(1);

        assertThat(planDeAhorroAzar.clienteAAdjudicar(), equalTo(cliente2));
    }

    @Test
    public void testAzarRetornaNumero(){
        FormaAdjudicacionAlAzar.Azar azar = new FormaAdjudicacionAlAzar.Azar();

        assertThat(azar.siguienteNumeroHasta(1), equalTo(0));
    }

    @Test
    public void testAlicuota(){
        when(financiamiento.montoAPagarEnCuotas(modeloAuto)).thenReturn(1200.0);
        assertThat(planDeAhorroCobertura.getAlicuota(), equalTo(100.0));
    }

}
