package hito1;

import java.util.List;
import java.util.Optional;

public class FormaAdjudicacionPorMayorCobertura implements FormaAdjudicacion{

    public Optional<Cliente> siguienteClienteAAdjudicar(List<Cliente> suscriptos) {
        return suscriptos.stream()
            .max((cliente1, cliente2) -> {
                int diferencia = (int) (cliente1.getSolicitudDeAdjudicacion().porcentajePagado() - cliente2.getSolicitudDeAdjudicacion().porcentajePagado());
                if (diferencia == 0){
                    diferencia = (int) (cliente1.getIngresoCliente().getMillis() - cliente2.getIngresoCliente().getMillis());
                }

                if (diferencia == 0){
                    diferencia = (int) (cliente1.getSolicitudDeAdjudicacion().getFechaIngresoPlan().getMillis() -
                            cliente2.getSolicitudDeAdjudicacion().getFechaIngresoPlan().getMillis());
                }
                return diferencia;
            });
    }
}