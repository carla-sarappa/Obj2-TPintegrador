package hito1;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Stock {
    private Map<ModeloAuto, Integer> cantidades = new HashMap<>();

    public void aumentar(ModeloAuto modeloAuto, Integer cantidad){
        this.actualizar(modeloAuto, cantidad);
    }

    public void disminuir(ModeloAuto modeloAuto, Integer cantidad){
        this.actualizar(modeloAuto, cantidad * -1);
    }

    private void actualizar(ModeloAuto modeloAuto, Integer diferencia){
        cantidades.put(modeloAuto, cantidad(modeloAuto) + diferencia);
    }

    public Integer cantidad(ModeloAuto modeloAuto){
        return cantidades.getOrDefault(modeloAuto, 0);
    }

    public List<ModeloAuto> disponibles(){
        return cantidades.entrySet().stream()
                .filter(par -> par.getValue() > 0)
                .map(par -> par.getKey())
                .collect(Collectors.toList());
    }

}
