package fixture;

import doubles.FormaAdjudicacionDummy;
import hito1.*;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FixturePlanesDeAhorro {
    public final List<PlanDeAhorro> planesDeAhorroConMayorCantidadDeSuscriptores = new ArrayList<>();
    public final Concesionaria concesionaria = new Concesionaria(new Fabrica());

    public FixturePlanesDeAhorro() {
        List<PlanDeAhorro> todosLosPlanesDeAhorro = new ArrayList<>();
        todosLosPlanesDeAhorro.add(nuevoPlanDeAhorro(1, 5));
        guardarPlanConMuchosSuscriptores(todosLosPlanesDeAhorro, nuevoPlanDeAhorro(2, 10));
        guardarPlanConMuchosSuscriptores(todosLosPlanesDeAhorro, nuevoPlanDeAhorro(3, 32));
        todosLosPlanesDeAhorro.add(nuevoPlanDeAhorro(4, 1));
        todosLosPlanesDeAhorro.add(nuevoPlanDeAhorro(5, 0));
        todosLosPlanesDeAhorro.add(nuevoPlanDeAhorro(6, 3));
        todosLosPlanesDeAhorro.add(nuevoPlanDeAhorro(7, 2));
        todosLosPlanesDeAhorro.add(nuevoPlanDeAhorro(8, 6));
        guardarPlanConMuchosSuscriptores(todosLosPlanesDeAhorro, nuevoPlanDeAhorro(9, 80));
        todosLosPlanesDeAhorro.add(nuevoPlanDeAhorro(10, 9));
        guardarPlanConMuchosSuscriptores(todosLosPlanesDeAhorro, nuevoPlanDeAhorro(11, 22));
        guardarPlanConMuchosSuscriptores(todosLosPlanesDeAhorro, nuevoPlanDeAhorro(12, 40));
        guardarPlanConMuchosSuscriptores(todosLosPlanesDeAhorro, nuevoPlanDeAhorro(13, 55));
        todosLosPlanesDeAhorro.add(nuevoPlanDeAhorro(14, 4));
        todosLosPlanesDeAhorro.add(nuevoPlanDeAhorro(15, 7));
        guardarPlanConMuchosSuscriptores(todosLosPlanesDeAhorro, nuevoPlanDeAhorro(16, 11));
        guardarPlanConMuchosSuscriptores(todosLosPlanesDeAhorro, nuevoPlanDeAhorro(17, 15));
        todosLosPlanesDeAhorro.add(nuevoPlanDeAhorro(18, 8));
        guardarPlanConMuchosSuscriptores(todosLosPlanesDeAhorro, nuevoPlanDeAhorro(19, 92));
        guardarPlanConMuchosSuscriptores(todosLosPlanesDeAhorro, nuevoPlanDeAhorro(20, 14));

        concesionaria.setPlanesDeAhorro(todosLosPlanesDeAhorro);
    }

    public void guardarPlanConMuchosSuscriptores(List<PlanDeAhorro> planes, PlanDeAhorro plan){
        planesDeAhorroConMayorCantidadDeSuscriptores.add(plan);
        planes.add(plan);
    }

    public PlanDeAhorro nuevoPlanDeAhorro(Integer ID, Integer cantidadSuscriptores){
        PlanDeAhorro planDeAhorro = new PlanDeAhorro(ID, new ModeloAuto(), new Financiamiento100(), new FormaAdjudicacionDummy(), 9);
        planDeAhorro.getSuscriptosPlan().addAll(nuevosClientes(cantidadSuscriptores));
        return planDeAhorro;
    }

    public List<Cliente> nuevosClientes(Integer cantidad){
        return IntStream.range(0, cantidad)
                .mapToObj(id -> new Cliente("cliente " + id, "Sasfrasdk", 3383888, new DateTime("1909-03-03"), "Capital","carldsffffrappa@gmail.com", new DateTime("2014-05-01"))
        ).collect(Collectors.toList());
    }
}
