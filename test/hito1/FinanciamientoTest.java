package hito1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class FinanciamientoTest {
    private ModeloAuto modelo;
    private Financiamiento100 financiamiento100;
    private Financiamiento7030 financiamiento7030;

    @Before
    public void setUp(){
        modelo = new ModeloAuto();
        financiamiento100 = new Financiamiento100();
        financiamiento7030 = new Financiamiento7030();
    }

    @Test
    public void testFinanciamiento100_MontoAPagarEnCuotas(){
        modelo.setValorDeVenta(20000.0);
        assertTrue(20000.0 == financiamiento100.montoAPagarEnCuotas(modelo));
    }

    @Test
    public void testFinanciamiento70_MontoAPagarEnCuotas(){
        modelo.setValorDeVenta(20000.0);
        assertTrue(14000.0 == financiamiento7030.montoAPagarEnCuotas(modelo));
    }

}
