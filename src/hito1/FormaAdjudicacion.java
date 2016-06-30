package hito1;

import java.util.List;
import java.util.Optional;

public interface FormaAdjudicacion {

    Optional<Cliente> siguienteClienteAAdjudicar(List<Cliente> suscriptos);

}
