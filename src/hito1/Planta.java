package hito1;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Planta {

    private Map<ModeloAuto, Integer> cantidades = new HashMap<>();

    public List<ModeloAuto> getModelos() {
        return cantidades.entrySet().stream()
                .filter(par -> par.getValue() > 0)
                .map(par -> par.getKey())
                .collect(Collectors.toList());
    }

    public void agregarOAumentarModelo(ModeloAuto modeloAuto, Integer cantidad) {
        this.actualizar(modeloAuto, cantidad);
    }

    public void disminuir(ModeloAuto modeloAuto, Integer cantidad){
        this.actualizar(modeloAuto, cantidad * -1);
    }

    private void actualizar(ModeloAuto modeloAuto, Integer diferencia){
        cantidades.put(modeloAuto, stock(modeloAuto) + diferencia);
    }

    public Integer stock(ModeloAuto modeloAuto){
        return cantidades.getOrDefault(modeloAuto, 0);
    }

    public boolean produce(ModeloAuto modeloAuto){
        return getModelos().contains(modeloAuto);
    }

}
