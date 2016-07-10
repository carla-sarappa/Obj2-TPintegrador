package hito1;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Financiamiento7030Test {
    private ModeloAuto modelo;
    private Financiamiento7030 financiamiento7030;

    @Before
    public void setUp(){
        modelo = mock(ModeloAuto.class);
        when(modelo.getValorDeVenta()).thenReturn(20000.0);
        financiamiento7030 = new Financiamiento7030();
    }

    @Test
    public void testFinanciamiento70_MontoAPagarEnCuotas(){

        assertTrue(14000.0 == financiamiento7030.montoAPagarEnCuotas(modelo));
    }

    @Test
    public void testFinanciamiento70_MontoAPagarEnElMomentoDeLaAdjudicacion(){
        assertThat(financiamiento7030.montoAPagarEnElMomentoDeAdjudicacion(modelo), equalTo(6000.0));
    }

}
