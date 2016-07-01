package hito1;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.notNull;
import static org.mockito.Mockito.*;

public class ClienteTest {

    private Cliente cliente;
    private PlanDeAhorro planDeAhorro;

    @Before
    public void setUp() {
        cliente = new Cliente("cliente 1", "Sasfrasdk", 3383888, new DateTime("1909-03-03"), "Capital","carldsffffrappa@gmail.com", new DateTime("2014-05-01"));
        planDeAhorro = mock(PlanDeAhorro.class);
        when(planDeAhorro.getCantidadDeCuotas()).thenReturn(5);
    }

    @Test
    public void testEfectuarPagosDeCuotas(){
        SolicitudDeAdjudicacion solicitudDeAdjudicacion = spy(new SolicitudDeAdjudicacion(planDeAhorro, DateTime.now()));
        ArgumentCaptor<ComprobanteDePago> comprobanteDePagoArgumentCaptor = ArgumentCaptor.forClass(ComprobanteDePago.class);
        cliente.setSolicitudDeAdjudicacion(solicitudDeAdjudicacion);
        cliente.pagarCuota();

        verify(solicitudDeAdjudicacion).agregarPago(comprobanteDePagoArgumentCaptor.capture());

        assertNotNull(comprobanteDePagoArgumentCaptor.getValue());
    }

}
