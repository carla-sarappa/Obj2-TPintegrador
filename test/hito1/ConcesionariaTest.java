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
        planta = new Planta();
        planta1 = new Planta();
        planta2 = new Planta();
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
        planta1.agregarOAumentarModelo(modelo1, 2);
        planta2.agregarOAumentarModelo(modelo2, 1);
        planta.agregarOAumentarModelo(modelo1, 3);
        concesionaria.registrarDistancia(planta1, 20.0);
        concesionaria.registrarDistancia(planta2, 10.0);
        concesionaria.registrarDistancia(planta, 30.0);
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
    public void  testConcesionariaAseguraQueSuInformacionLocalDeStockDeModelosCoincideConLosDeLasPlantas(){
        planta1.agregarOAumentarModelo(modeloAuto, 2);
        planta2.agregarOAumentarModelo(modeloAuto, 3);
        fabrica.agregarPlanta(planta1);
        fabrica.agregarPlanta(planta2);

        assertThat(concesionaria.cantidadDisponibleDe(modeloAuto),equalTo(5));

        planta1.agregarOAumentarModelo(modeloAuto, 1);

        assertThat(concesionaria.cantidadDisponibleDe(modeloAuto),equalTo(6));
    }



}
