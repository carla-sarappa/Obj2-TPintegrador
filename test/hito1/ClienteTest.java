package hito1;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class ClienteTest {

    private Cliente cliente;
    private PlanDeAhorro planDeAhorro;
    private Concesionaria concesionaria;

    @Before
    public void setUp() {
        cliente = new Cliente("nombre", "apellido", 3383888, new DateTime("1909-03-03"), "Capital","carla@il.com", new DateTime("2014-05-01"));
        planDeAhorro = mock(PlanDeAhorro.class);
        when(planDeAhorro.getCantidadDeCuotas()).thenReturn(5);
        concesionaria = mock(Concesionaria.class);
    }

    @Test
    public void testEfectuarPagosDeCuotas(){
        SolicitudDeAdjudicacion solicitudDeAdjudicacion = spy(new SolicitudDeAdjudicacion(planDeAhorro, DateTime.now()));
        ArgumentCaptor<ComprobanteDePago> comprobanteDePagoArgumentCaptor = ArgumentCaptor.forClass(ComprobanteDePago.class);
        cliente.setSolicitudDeAdjudicacion(solicitudDeAdjudicacion);
        cliente.pagarCuota(concesionaria);

        verify(solicitudDeAdjudicacion).agregarPago(comprobanteDePagoArgumentCaptor.capture());

        assertNotNull(comprobanteDePagoArgumentCaptor.getValue());
    }

    @Test
    public void testGetNombre() {
        assertThat(cliente.getNombre(), equalTo("nombre"));
    }

    @Test
    public void testGetApellido() {
        assertThat(cliente.getApellido(), equalTo("apellido"));
    }

    @Test
    public void testGetDNI() {
        assertThat(cliente.getDNI(), equalTo(3383888));
    }

    @Test
    public void testGetDireccion() {
        assertThat(cliente.getDireccion(), equalTo("Capital"));
    }

    @Test
    public void testGetMail() {
        assertThat(cliente.getMail(), equalTo("carla@il.com"));
    }

    @Test
    public void testGetfNac() {
        assertThat(cliente.getfNac(), equalTo(new DateTime("1909-03-03")));
    }

    @Test
    public void testGetIngresoCliente() {
        assertThat(cliente.getIngresoCliente(), equalTo(new DateTime("2014-05-01")));
    }

}
