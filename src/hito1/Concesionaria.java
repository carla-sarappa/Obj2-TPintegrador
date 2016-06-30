package hito1;

import java.util.*;
import java.util.stream.Collectors;

public class Concesionaria {
    private Fabrica fabrica;
    private Map<Planta, Double> distancias = new HashMap<>();
    private List<PlanDeAhorro> planesDeAhorro = new ArrayList<>();

    public Concesionaria(Fabrica fabrica) {
        this.fabrica = fabrica;
    }

    public Fabrica getFabrica() {
        return fabrica;
    }

    public void registrarDistancia(Planta planta, Double distanciaVial) {
        distancias.put(planta, distanciaVial);
    }

    public Double distanciaA(Planta planta) {
        return distancias.get(planta);
    }

    public List<PlanDeAhorro> planesConMayorCantidadDeSuscriptos(Integer cantidadPlanes) {
        return planesDeAhorro.stream()
            .sorted((plan1, plan2) -> { // se compara al reves para que quede de mayor a menor
                return (plan2.getSuscriptosPlan().size() - plan1.getSuscriptosPlan().size());
            })
            .limit(cantidadPlanes)
            .collect(Collectors.toList());
    }

    public Optional<Planta> plantaMasCercanaConModeloDisponible(ModeloAuto modeloAuto) {
        return fabrica.plantasQueProducenModelo(modeloAuto).stream()
                .min((planta1, planta2) -> {
                    return (distanciaA(planta1).intValue() - distanciaA(planta2).intValue());
                });
    }

    public List<PlanDeAhorro> getPlanesDeAhorro() {
        return planesDeAhorro;
    }

    public void setPlanesDeAhorro(List<PlanDeAhorro> planesDeAhorro) {
        this.planesDeAhorro = planesDeAhorro;
    }
}