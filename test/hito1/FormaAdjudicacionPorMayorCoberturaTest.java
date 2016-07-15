package hito1;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FormaAdjudicacionPorMayorCoberturaTest {
    private Cliente cliente1;
    private Cliente cliente2;

    @Before
    public void setUp(){
        cliente1 = mock(Cliente.class);
        cliente2 = mock(Cliente.class);
    }

    @Test
    public void testCompararPorcentajePagado(){
        when(cliente1.porcentajePagado()).thenReturn(90.0);
        when(cliente2.porcentajePagado()).thenReturn(80.0);

        assertThat(new FormaAdjudicacionPorMayorCobertura.CompararPorcentajePagado().comparar(cliente1, cliente2), equalTo(10));
    }

    @Test
    public void testCompararFechaIngresoConcesionaria(){
        when(cliente1.getIngresoClienteConcesionaria()).thenReturn(new DateTime("2015-06-08"));
        when(cliente2.getIngresoClienteConcesionaria()).thenReturn(new DateTime("2015-06-07"));
        assertThat(new FormaAdjudicacionPorMayorCobertura.CompararFechaIngresoConcesionaria().comparar(cliente1, cliente2),
                equalTo((int) (new DateTime("2015-06-07").getMillis() - new DateTime("2015-06-08").getMillis())));
    }

    @Test
    public void testCompararFechaIngresoPlan(){
        SolicitudDeAdjudicacion solicitudDeAdjudicacionCliente1 = mock(SolicitudDeAdjudicacion.class);
        SolicitudDeAdjudicacion solicitudDeAdjudicacionCliente2 = mock(SolicitudDeAdjudicacion.class);
        when(solicitudDeAdjudicacionCliente1.getFechaIngresoPlan()).thenReturn(new DateTime("2015-06-08"));
        when(solicitudDeAdjudicacionCliente2.getFechaIngresoPlan()).thenReturn(new DateTime("2015-06-07"));

        when(cliente1.getSolicitudDeAdjudicacion()).thenReturn(solicitudDeAdjudicacionCliente1);
        when(cliente2.getSolicitudDeAdjudicacion()).thenReturn(solicitudDeAdjudicacionCliente2);
        assertThat(new FormaAdjudicacionPorMayorCobertura.CompararFechaIngresoPlan().comparar(cliente1, cliente2),
                equalTo((int) (new DateTime("2015-06-07").getMillis() - new DateTime("2015-06-08").getMillis())));
    }

    @Test
    public void testCompararPorPorcentajePagadoYFechaDeIngresoConcesionaria_MismoPorcentajeDistintaFecha(){
        when(cliente1.porcentajePagado()).thenReturn(80.0);
        when(cliente2.porcentajePagado()).thenReturn(80.0);
        when(cliente1.getIngresoClienteConcesionaria()).thenReturn(new DateTime("2015-06-08"));
        when(cliente2.getIngresoClienteConcesionaria()).thenReturn(new DateTime("2015-06-07"));

        assertThat(new FormaAdjudicacionPorMayorCobertura.CompararPorcentajePagado()
                .oPor(new FormaAdjudicacionPorMayorCobertura.CompararFechaIngresoConcesionaria())
                .oPor(new FormaAdjudicacionPorMayorCobertura.CompararFechaIngresoPlan())
                .entre(cliente1, cliente2), equalTo((int) (new DateTime("2015-06-07").getMillis() - new DateTime("2015-06-08").getMillis())));
    }


    @Test
    public void testCompararPorFechaDeIngresoConcesionariaYFechaDeIngresoPlan_MismoPorcentajeYFechaIngresoConcesionariaYDistintaFechaDeIngresoPlan(){
        SolicitudDeAdjudicacion solicitudDeAdjudicacionCliente1 = mock(SolicitudDeAdjudicacion.class);
        SolicitudDeAdjudicacion solicitudDeAdjudicacionCliente2 = mock(SolicitudDeAdjudicacion.class);
        when(solicitudDeAdjudicacionCliente1.getFechaIngresoPlan()).thenReturn(new DateTime("2015-09-11"));
        when(solicitudDeAdjudicacionCliente2.getFechaIngresoPlan()).thenReturn(new DateTime("2015-09-10"));

        when(cliente1.getSolicitudDeAdjudicacion()).thenReturn(solicitudDeAdjudicacionCliente1);
        when(cliente2.getSolicitudDeAdjudicacion()).thenReturn(solicitudDeAdjudicacionCliente2);
        when(cliente1.porcentajePagado()).thenReturn(80.0);
        when(cliente2.porcentajePagado()).thenReturn(80.0);
        when(cliente1.getIngresoClienteConcesionaria()).thenReturn(new DateTime("2015-05-12"));
        when(cliente2.getIngresoClienteConcesionaria()).thenReturn(new DateTime("2015-05-12"));

        assertThat(new FormaAdjudicacionPorMayorCobertura.CompararPorcentajePagado()
                .oPor(new FormaAdjudicacionPorMayorCobertura.CompararFechaIngresoConcesionaria())
                .oPor(new FormaAdjudicacionPorMayorCobertura.CompararFechaIngresoPlan())
                .entre(cliente1, cliente2), equalTo((int) (new DateTime("2015-09-10").getMillis() - new DateTime("2015-09-11").getMillis())));
    }

    @Test
    public void testSiguienteClienteAAdjudicar(){
        Cliente cliente3 = mock(Cliente.class);
        when(cliente1.porcentajePagado()).thenReturn(80.0);
        when(cliente2.porcentajePagado()).thenReturn(80.0);
        when(cliente3.porcentajePagado()).thenReturn(50.0);

        when(cliente1.getIngresoClienteConcesionaria()).thenReturn(new DateTime("2015-05-11"));
        when(cliente2.getIngresoClienteConcesionaria()).thenReturn(new DateTime("2015-05-12"));

        List<Cliente> suscriptos = new ArrayList<>();
        suscriptos.add(cliente1);
        suscriptos.add(cliente2);
        suscriptos.add(cliente3);

        assertThat(new FormaAdjudicacionPorMayorCobertura().siguienteClienteAAdjudicar(suscriptos).get(), equalTo(cliente1));

    }
}
