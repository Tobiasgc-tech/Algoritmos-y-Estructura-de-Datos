package aed;

public class Tupla<P,S>{
    private P primero;
    private S segundo;

    public Tupla(P elem1, S elem2) {
        this.primero = elem1;
        this.segundo = elem2;
    }

    public P getPrimero() {
        return this.primero;
    }

    public S getSegundo() {
        return this.segundo;
    }
}
