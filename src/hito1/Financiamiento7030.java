package hito1;

public class Financiamiento7030 implements Financiamiento {


    public Double montoAPagarEnCuotas(ModeloAuto modelo){
        return (modelo.getValorDeVenta() * 0.7);
    }

    public Double montoAPagarEnElMomentoDeAdjudicacion(ModeloAuto modelo) {
        return (modelo.getValorDeVenta() * 0.3);
    }
}
