package hito1;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConcesionariaTest {
    private Concesionaria concesionaria;
    private Fabrica fabrica;
    private Planta planta;
    private Planta planta1;
    private Planta planta2;
    private ModeloAuto modeloAuto;
    private ModeloAuto modelo1;
    private ModeloAuto modelo2;

    @Before
    public void setUp(){
        fabrica = new Fabrica();
        concesionaria = new Concesionaria(fabrica);
        planta = mock(Planta.class);
        planta1 = mock(Planta.class);
        planta2 = mock(Planta.class);
        modeloAuto = mock(ModeloAuto.class);
        modelo1 = mock(ModeloAuto.class);
        modelo2 = mock(ModeloAuto.class);
    }

    @Test
    public void testConcesionarioTieneUnaUnicaFabrica() {
        assertNotNull(concesionaria.getFabrica());
    }

    @Test
    public void testConcesionariaRegistraDistanciaEntreEllaYCadaPlanta(){
        fabrica.agregarPlanta(planta);
        concesionaria.registrarDistancia(planta, 20.0);
        assertTrue(20.0 == concesionaria.distanciaA(planta));
    }

    @Test
    public void testConcesionariaConocePlantaMasCercanaQueConoceUnModeloDado(){
        fabrica.agregarPlanta(planta1);
        fabrica.agregarPlanta(planta2);
        fabrica.agregarPlanta(planta);
        when(planta1.stock(modelo1)).thenReturn(2);
        when(planta2.stock(modelo2)).thenReturn(1);
        when(planta.stock(modelo1)).thenReturn(3);
        concesionaria.registrarDistancia(planta1, 20.0);
        concesionaria.registrarDistancia(planta2, 10.0);
        concesionaria.registrarDistancia(planta, 30.0);
        when(planta1.produce(modelo1)).thenReturn(true);
        when(planta2.produce(modelo2)).thenReturn(true);
        when(planta.produce(modelo1)).thenReturn(true);
        assertEquals(planta1, concesionaria.plantaMasCercanaConModeloDisponible(modelo1).get());
    }

    @Test
    public void testConcesionariaTienePlanesDeAhorro(){
        assertNotNull(concesionaria.getPlanesDeAhorro());
    }

    @Test
    public void testConcesionariaConoceLos10PlanesConMayorCantidadDeSuscriptos_OrdenadosDescendentemente(){
        List<PlanDeAhorro> planes = new ArrayList<>();

        for (int cantidadSubscriptos=0;cantidadSubscriptos < 20; cantidadSubscriptos++){
            PlanDeAhorro planDeAhorro = mock(PlanDeAhorro.class);
            planes.add(planDeAhorro);
            when(planDeAhorro.getCantidadDeSuscriptos()).thenReturn(cantidadSubscriptos);
        }

        List<PlanDeAhorro> planesConMasSubscriptos = planes.stream().skip(10).collect(Collectors.toList());
        concesionaria.setPlanesDeAhorro(planes);
        assertTrue(concesionaria.planesConMayorCantidadDeSuscriptos(10).containsAll(planesConMasSubscriptos));
    }


    @Test
    public void testConcesionariaAseguraQueSuInformacionLocalDeStockDeModelosCoincideConLosDeLasPlantas(){
        fabrica.agregarPlanta(planta1);
        fabrica.agregarPlanta(planta2);
        when(planta1.stock(modeloAuto)).thenReturn(2).thenReturn(3);
        when(planta2.stock(modeloAuto)).thenReturn(3);

        assertThat(concesionaria.cantidadDisponibleDe(modeloAuto),equalTo(5));
        assertThat(concesionaria.cantidadDisponibleDe(modeloAuto),equalTo(6));
    }

    @Test
    public void testSiNoExistePlantaCercanaConStockDelModeloDeAuto_LanzaUnaExcepcion(){

    }


}
