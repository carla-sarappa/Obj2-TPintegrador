package hito1;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StockTest {
    private Stock stock;
    private ModeloAuto modeloAuto;

    @Before
    public void setUp() {
        stock = new Stock();
        modeloAuto = new ModeloAuto();
    }

    @Test
    public void testAumentarStockDeModeloExistente(){
        stock.aumentar(modeloAuto, 3);

        assertTrue(3 == stock.cantidad(modeloAuto));
    }

    @Test
    public void testDisminuirStockDeModeloExistente(){
        stock.aumentar(modeloAuto, 3);
        stock.disminuir(modeloAuto, 1);

        assertTrue(2 == stock.cantidad(modeloAuto));
    }

    @Test
    public void testCuandoPideStockDeModeloNoExistente_DevuelveCero(){
        assertTrue(0 == stock.cantidad(modeloAuto));
    }


}
