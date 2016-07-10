package hito1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class FabricaTest {
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
        planta = new Planta();
        planta1 = new Planta();
        planta2 = new Planta();
        modeloAuto = mock(ModeloAuto.class);
        modelo1 = mock(ModeloAuto.class);
        modelo2 = mock(ModeloAuto.class);
    }


    @Test
    public void testFabricaTieneUnConjuntoDePlantas(){
        fabrica.agregarPlanta(planta);
        assertFalse(fabrica.getPlantas().isEmpty());
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


}
