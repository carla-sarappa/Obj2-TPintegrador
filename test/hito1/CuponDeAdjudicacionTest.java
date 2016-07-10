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

    private Concesionaria concesionaria;
    private Fabrica fabrica;
    private Planta planta;

    private ModeloAuto modeloAuto;

    @Before
    public void setUp(){
        fabrica = new Fabrica();
        concesionaria = new Concesionaria(fabrica);
        planta = new Planta();
        modeloAuto = mock(ModeloAuto.class);
    }


    @Test
    public void testConcesionariaRegistraCuponesDeAdjudicacion(){
        Cliente cliente = new Cliente("cliente 1", "Sasfrasdk", 3383888, new DateTime("1909-03-03"), "Capital","carldsffffrappa@gmail.com", new DateTime("2014-05-01"));
        PlanDeAhorro planDeAhorro = mock(PlanDeAhorro.class);
        when(planDeAhorro.clienteAAdjudicar()).thenReturn(cliente);
        when(planDeAhorro.getFinanciamiento()).thenReturn(new Financiamiento100());
        when(planDeAhorro.getModeloAuto()).thenReturn(modeloAuto);
        fabrica.agregarPlanta(planta);
        planta.agregarOAumentarModelo(modeloAuto, 10);
        concesionaria.registrarDistancia(planta, 4.0);

        CuponDeAdjudicacion cupon = concesionaria.ejecutarAdjudicacionYGenerarCupon(planDeAhorro);

        assertEquals(cliente, cupon.getCliente());
        assertThat(cupon.getGastoDeFlete(), equalTo(400.0));
        assertThat(cupon.getMontoAPagarAlMomentoDeLaAdjudicacion(), equalTo(0.0));

    }
}
