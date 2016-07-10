package hito1;

import java.util.List;
import java.util.Optional;

public class FormaAdjudicacionPorMayorCobertura implements FormaAdjudicacion{

    public Optional<Cliente> siguienteClienteAAdjudicar(List<Cliente> suscriptos) {
        return suscriptos.stream()
            .max((cliente1, cliente2) -> {
                return new CompararPorcentajePagado()
                        .oPor(new CompararFechaIngresoConcesionaria())
                        .oPor(new CompararFechaIngresoPlan())
                        .entre(cliente1, cliente2);
            });
    }

    private static abstract class CompararCliente{
        private CompararCliente siguienteComparador;
        public abstract int comparar(Cliente cliente1, Cliente cliente2);
        public CompararCliente oPor(CompararCliente compararCliente){
            compararCliente.setSiguienteComparador(this);
            return compararCliente;
        }

        public void setSiguienteComparador(CompararCliente siguienteComparador) {
            this.siguienteComparador = siguienteComparador;
        }

        public int entre(Cliente cliente1, Cliente cliente2){
            if (siguienteComparador == null){
                return comparar(cliente1, cliente2);
            }
            int diferencia = siguienteComparador.entre(cliente1, cliente2);
            if (diferencia == 0 ){
                diferencia = comparar(cliente1, cliente2);
            }
            return diferencia;
        }
    }

    public static class CompararPorcentajePagado extends CompararCliente{

        public int comparar(Cliente cliente1, Cliente cliente2){
            return (int) (cliente1.getSolicitudDeAdjudicacion().porcentajePagado() - cliente2.getSolicitudDeAdjudicacion().porcentajePagado());
        }

    }

    public static class CompararFechaIngresoConcesionaria extends CompararCliente{
        public int comparar(Cliente cliente1, Cliente cliente2){
            return (int) (cliente1.getIngresoCliente().getMillis() - cliente2.getIngresoCliente().getMillis());
        }
    }

    public static class CompararFechaIngresoPlan extends CompararCliente{
        public int comparar(Cliente cliente1, Cliente cliente2){
            return (int) (cliente1.getSolicitudDeAdjudicacion().getFechaIngresoPlan().getMillis() -
                    cliente2.getSolicitudDeAdjudicacion().getFechaIngresoPlan().getMillis());
        }
    }
}