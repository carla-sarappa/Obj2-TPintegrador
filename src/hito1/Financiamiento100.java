package hito1;

public class Financiamiento100 implements Financiamiento {

    public Double montoAPagarEnCuotas(ModeloAuto modelo){
        return (modelo.getValorDeVenta());
    }


    public Double montoAPagarEnElMomentoDeAdjudicacion(ModeloAuto modelo) {
        return 0.0;
    }
}
