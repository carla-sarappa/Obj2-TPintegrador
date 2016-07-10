package hito1;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Financiamiento100Test {
    private ModeloAuto modelo;
    private Financiamiento100 financiamiento100;

    @Before
    public void setUp(){
        modelo = mock(ModeloAuto.class);
        when(modelo.getValorDeVenta()).thenReturn(20000.0);
        financiamiento100 = new Financiamiento100();
    }

    @Test
    public void testFinanciamiento100_MontoAPagarEnCuotas(){

        assertTrue(20000.0 == financiamiento100.montoAPagarEnCuotas(modelo));
    }


    @Test
    public void testFinanciamiento100_MontoAPagarEnElMomentoDeLaAdjudicacion(){
        assertThat(financiamiento100.montoAPagarEnElMomentoDeAdjudicacion(modelo), equalTo(0.0));
    }

}
