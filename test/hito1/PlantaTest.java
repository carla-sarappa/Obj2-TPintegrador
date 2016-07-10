package hito1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
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

}
