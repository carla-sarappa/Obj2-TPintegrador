package hito1;

import org.joda.time.DateTime;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ModeloAutoTest {

    @Test
    public void testModeloAutoProperties(){
        ModeloAuto modelo = new ModeloAuto("Fiat", new DateTime("2010-03-03"), 4, true, 20000.0);

        assertTrue(modelo.isBase());
        assertFalse(modelo.isFull());
        assertThat(modelo.getValorDeVenta(), equalTo(20000.0));
    }
}
