package hito1;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CuponDeAdjudicacionTest {
    private CuponDeAdjudicacion cupon;
    private Cliente cliente;
    private PlanDeAhorro planDeAhorro;

    @Before
    public void setUp(){
        cliente = mock(Cliente.class);
        planDeAhorro = mock(PlanDeAhorro.class);
        cupon = new CuponDeAdjudicacion(20.0, planDeAhorro, cliente);
    }

    @Test
    public void testGetMontoAPagarAlMomentoDeLaAdjudicacion() {
        when(planDeAhorro.montoAPagarEnElMomentoDeAdjudicacion()).thenReturn(100.0);
        assertThat(cupon.getMontoAPagarAlMomentoDeLaAdjudicacion(), equalTo(100.0));
    }

}
