package doubles;

import hito1.Cliente;
import hito1.FormaAdjudicacion;

import java.util.List;
import java.util.Optional;

public class FormaAdjudicacionDummy implements FormaAdjudicacion {
    @Override
    public Optional<Cliente> siguienteClienteAAdjudicar(List<Cliente> suscriptos) {
        return null;
    }
}
