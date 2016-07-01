package hito1;

import fixture.FixturePlanesDeAhorro;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class ConcesionarioTest {
    private Concesionaria concesionario;
    private Fabrica fabrica;
    private Planta planta;
    private Planta planta1;
    private Planta planta2;
    private ModeloAuto modeloAuto;
    private ModeloAuto modelo1;
    private ModeloAuto modelo2;
    private FixturePlanesDeAhorro fixture;

    @Before
    public void setUp(){
        fabrica = new Fabrica();
        concesionario = new Concesionaria(fabrica);
        planta = new Planta();
        planta1 = new Planta();
        planta2 = new Planta();
        modeloAuto = new ModeloAuto();
        modelo1 = new ModeloAuto();
        modelo2 = new ModeloAuto();
        fixture = new FixturePlanesDeAhorro();
    }

    @Test
    public void testConcesionarioTieneUnaUnicaFabrica() {
        assertNotNull(concesionario.getFabrica());
    }

    @Test
    public void testFabricaTieneUnConjuntoDePlantas(){
        fabrica.agregarPlanta(planta);
        assertFalse(fabrica.getPlantas().isEmpty());
    }

    @Test
    public void testPlantaTieneUnConjuntoDeModelosDeAutos(){
        planta.agregarOAumentarModelo(modeloAuto, 1);
        assertFalse(planta.getModelos().isEmpty());
    }

    @Test
    public void testUnaFabricaSabeDecirConQueModelosTrabaja(){
        fabrica.agregarPlanta(planta);
        planta.agregarOAumentarModelo(modeloAuto, 1);
        assertFalse(fabrica.getModelosDisponibles().isEmpty());
    }

    @Test
    public void testLosModelosDeLaPlantaSonUnSubconjuntoDeLaFabrica(){
        fabrica.agregarPlanta(planta1);
        planta1.agregarOAumentarModelo(modelo1, 1);
        fabrica.agregarPlanta(planta2);
        planta2.agregarOAumentarModelo(modelo2, 2);

        assertTrue(fabrica.getModelosDisponibles().containsAll(fabrica.getPlantas().get(0).getModelos()));
    }

    @Test
    public void testDadaUnaFabricaYUnModelo_LePidoElStock(){
        fabrica.agregarPlanta(planta1);
        planta1.agregarOAumentarModelo(modelo1, 5);

        assertEquals(5, fabrica.stockTotal(modelo1));
    }

    @Test
    public void testConcesionariaRegistraDistanciaEntreEllaYCadaPlanta(){
        fabrica.agregarPlanta(planta);
        concesionario.registrarDistancia(planta, 20.0);
        assertTrue(20.0 == concesionario.distanciaA(planta));
    }

    @Test
    public void testConcesionariaConocePlantaMasCercanaQueConoceUnModeloDado(){
        fabrica.agregarPlanta(planta1);
        fabrica.agregarPlanta(planta2);
        fabrica.agregarPlanta(planta);
        planta1.agregarOAumentarModelo(modelo1, 2);
        planta2.agregarOAumentarModelo(modelo2, 1);
        planta.agregarOAumentarModelo(modelo1, 3);
        concesionario.registrarDistancia(planta1, 20.0);
        concesionario.registrarDistancia(planta2, 10.0);
        concesionario.registrarDistancia(planta, 30.0);
        assertEquals(planta1, concesionario.plantaMasCercanaConModeloDisponible(modelo1).get());
    }

    @Test
    public void testConcesionariaTienePlanesDeAhorro(){
        assertNotNull(concesionario.getPlanesDeAhorro());
    }

    @Test
    public void testConcesionariaConoceLos10PlanesConMayorCantidadDeSuscriptos_OrdenadosDescendentemente(){
        assertTrue(fixture.concesionaria.planesConMayorCantidadDeSuscriptos(10).containsAll(fixture.planesDeAhorroConMayorCantidadDeSuscriptores));
    }


    @Test
    public void  testConcesionariaAseguraQueSuInformacionLocalDeStockDeModelosCoincideConLosDeLasPlantas(){
        planta1.agregarOAumentarModelo(modeloAuto, 2);
        planta2.agregarOAumentarModelo(modeloAuto, 3);
        fabrica.agregarPlanta(planta1);
        fabrica.agregarPlanta(planta2);

        assertThat(concesionario.cantidadDisponibleDe(modeloAuto),equalTo(5));

        planta1.agregarOAumentarModelo(modeloAuto, 1);

        assertThat(concesionario.cantidadDisponibleDe(modeloAuto),equalTo(6));
    }

    @Test
    public void testConcesionariaEfectuaPagosDeCuotas(){

    }

    @Test
    public void testConcesionariaRegistraCuponesDeAdjudicacion(){

    }

}
