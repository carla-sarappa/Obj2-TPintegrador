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

    @Before
    public void setUp(){
        solicitudDeAdjudicacion = mock(SolicitudDeAdjudicacion.class);
        cliente = mock(Cliente.class);
        comprobanteDePago = new ComprobanteDePago(solicitudDeAdjudicacion, new DateTime("2016-06-29"), 20.0, cliente);
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
}
