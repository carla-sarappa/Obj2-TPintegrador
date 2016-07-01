package hito1;

import java.util.List;
import java.util.Optional;
import java.util.Random;

public class FormaAdjudicacionAlAzar implements FormaAdjudicacion {
    private Azar azar = new Azar();

    public Optional<Cliente> siguienteClienteAAdjudicar(List<Cliente> suscriptos) {
        return Optional.of(suscriptos.get(azar.siguienteNumeroHasta(suscriptos.size())));
    }

    /**
     * Utilizo esto para inyectar la dependencia por Setter para poder mockearlo luego.
     * @param azar
     */
    public void setAzar(Azar azar) {
        this.azar = azar;
    }

    static class Azar {
        public Integer siguienteNumeroHasta(Integer numero){
            return new Random(System.nanoTime()).ints(0, numero).findFirst().getAsInt();
        }
    }
}

