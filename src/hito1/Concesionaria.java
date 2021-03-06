package hito1;

import java.util.*;
import java.util.stream.Collectors;

public class Concesionaria {
    private Fabrica fabrica;
    private Map<Planta, Double> distancias = new HashMap<>();
    private List<PlanDeAhorro> planesDeAhorro = new ArrayList<>();
    private List<CuponDeAdjudicacion> cuponesDeAdjudicacion = new ArrayList<>();
    private Double gastosAdminstrativos;

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
                    return (plan2.getCantidadDeSuscriptos() - plan1.getCantidadDeSuscriptos());
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

    public void ejecutarAdjudicaciones() {
        for (PlanDeAhorro plan : planesDeAhorro) {
            this.cuponesDeAdjudicacion.add(ejecutarAdjudicacionYGenerarCupon(plan));
        }
    }

    public CuponDeAdjudicacion ejecutarAdjudicacionYGenerarCupon(PlanDeAhorro plan) {
        Planta plantaMasCercana = plantaMasCercanaConModeloDisponible(plan.getModeloAuto())
                .orElseThrow(()-> new RuntimeException("No se encontró una planta que tenga este modelo en stock"));

        return new CuponDeAdjudicacion(this.distanciaA(plantaMasCercana), plan, ejecutarAdjudicacion(plan));

    }

    public Cliente ejecutarAdjudicacion(PlanDeAhorro plan) {
        return plan.clienteAAdjudicar();

    }

    public int cantidadDisponibleDe(ModeloAuto modelo) {
        return fabrica.stockTotal(modelo);
    }


    public Double getGastosAdminstrativos() {
        return gastosAdminstrativos;
    }

    public void setGastosAdminstrativos(Double gastosAdminstrativos) {
        this.gastosAdminstrativos = gastosAdminstrativos;
    }
}