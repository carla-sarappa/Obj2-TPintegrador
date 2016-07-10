package hito1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class PlantaTest {
    private Planta planta;
    private ModeloAuto modeloAuto;

    @Before
    public void setUp(){
        planta = new Planta();
        modeloAuto = mock(ModeloAuto.class);
    }

    @Test
    public void testPlantaTieneUnConjuntoDeModelosDeAutos(){
        planta.agregarOAumentarModelo(modeloAuto, 1);
        assertFalse(planta.getModelos().isEmpty());
    }

    @Test
    public void testAumentarStockDeModeloExistente(){
        planta.agregarOAumentarModelo(modeloAuto, 3);

        assertTrue(3 == planta.stock(modeloAuto));
    }

    @Test
    public void testDisminuirStockDeModeloExistente(){
        planta.agregarOAumentarModelo(modeloAuto, 3);
        planta.disminuir(modeloAuto, 1);

        assertTrue(2 == planta.stock(modeloAuto));
    }

    @Test
    public void testCuandoPideStockDeModeloNoExistente_DevuelveCero(){
        assertTrue(0 == planta.stock(modeloAuto));
    }



}
