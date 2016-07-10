package hito1;


import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class FormaAdjudicacionAlAzarTest {

    @Test
    public void testAzarRetornaNumero(){
        FormaAdjudicacionAlAzar.Azar azar = new FormaAdjudicacionAlAzar.Azar();

        assertThat(azar.siguienteNumeroHasta(1), equalTo(0));
    }
}
