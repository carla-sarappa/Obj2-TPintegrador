package hito1;


import java.util.ArrayList;
import java.util.List;

public class Planta {
    private Stock stock = new Stock();

    public Stock getStock() {
        return stock;
    }

    public List<ModeloAuto> getModelos() {
        return stock.disponibles();
    }

    public void agregarOAumentarModelo(ModeloAuto modeloAuto, Integer cantidad) {
        stock.aumentar(modeloAuto, cantidad);
    }


}
