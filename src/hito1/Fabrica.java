package hito1;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Fabrica {
    private List<Planta> plantas = new ArrayList<>();


    public List<Planta> getPlantas() {
        return plantas;
    }

    public List<ModeloAuto> getModelosDisponibles() {
        return plantas.stream()
                .flatMap(planta -> planta.getModelos().stream())
                .distinct()
                .collect(Collectors.toList());
    }

    public void agregarPlanta(Planta planta){
        plantas.add(planta);
    }

    public int stockTotal(ModeloAuto modelo) {
        return plantas.stream()
                .mapToInt(planta -> planta.stock(modelo))
                .sum();
    }

    public List<Planta> plantasQueProducenModelo(ModeloAuto modeloAuto){
        return plantas.stream()
                .filter(planta -> planta.produce(modeloAuto))
                .collect(Collectors.toList());
    }

}
