package hito1;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SolicitudDeAdjudicacionTest {
    private List<ComprobanteDePago> pagosEmitidos = new ArrayList<>();
    private PlanDeAhorro planDeAhorro;
    private ComprobanteDePago comprobanteDePago1;
    private ComprobanteDePago comprobanteDePago2;
    private ComprobanteDePago comprobanteDePago3;
    private SolicitudDeAdjudicacion solicitudDeAdjudicacion;
    private ModeloAuto modelo;

    @Before
    public void setUp(){
        comprobanteDePago1 = mock(ComprobanteDePago.class);
        comprobanteDePago2 = mock(ComprobanteDePago.class);
        comprobanteDePago3 = mock(ComprobanteDePago.class);

        planDeAhorro = mock(PlanDeAhorro.class);
        solicitudDeAdjudicacion = new SolicitudDeAdjudicacion(planDeAhorro, new DateTime("2016-06-30"));
        solicitudDeAdjudicacion.agregarPago(comprobanteDePago1);
        solicitudDeAdjudicacion.agregarPago(comprobanteDePago2);
        solicitudDeAdjudicacion.agregarPago(comprobanteDePago3);
        modelo = mock(ModeloAuto.class);
        when(modelo.getValorDeVenta()).thenReturn(40.0);

    }

    @Test
    public void testMontoPagadoHastaElMomento(){
        when(planDeAhorro.getAlicuota()).thenReturn(10.0);

        assertThat(solicitudDeAdjudicacion.montoPagadoHastaElMomento(), equalTo(30.0));
    }

    @Test
    public void testPorcentajePagado(){
        when(planDeAhorro.getAlicuota()).thenReturn(10.0);
        when(planDeAhorro.getModeloAuto()).thenReturn(modelo);

        assertThat(solicitudDeAdjudicacion.porcentajePagado(), equalTo(75.0));
    }

    @Test
    public void testGetFechaIngresoPlan() {
        assertThat(solicitudDeAdjudicacion.getFechaIngresoPlan(), equalTo(new DateTime("2016-06-30")));
    }

    @Test
    public void testGetPagosEmitidos(){
        assertThat(solicitudDeAdjudicacion.getPagosEmitidos(), hasItems(comprobanteDePago1, comprobanteDePago2, comprobanteDePago3));
    }

    @Test
    public void testAgregarPago(){
        ComprobanteDePago comprobanteDePago = mock(ComprobanteDePago.class);
        solicitudDeAdjudicacion.agregarPago(comprobanteDePago);
        assertThat(solicitudDeAdjudicacion.getPagosEmitidos(), hasItems(comprobanteDePago));
    }

    @Test
    public void testGetPlanDeAhorro() {
        assertThat(solicitudDeAdjudicacion.getPlanDeAhorro(), equalTo(planDeAhorro));
    }

    @Test
    public void testGetValorDeVenta(){
        when(planDeAhorro.getModeloAuto()).thenReturn(modelo);

        assertThat(solicitudDeAdjudicacion.getValorDeVenta(), equalTo(40.0));

    }
}



