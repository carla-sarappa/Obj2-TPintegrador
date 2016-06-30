package hito1;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class FormaAdjudicacionAlAzar implements FormaAdjudicacion {

    public Optional<Cliente> siguienteClienteAAdjudicar(List<Cliente> suscriptos) {
        Integer posicionRandom = new Random(1234).ints(0, suscriptos.size()).findFirst().getAsInt();
        return Optional.of(suscriptos.get(posicionRandom));
    }
}
