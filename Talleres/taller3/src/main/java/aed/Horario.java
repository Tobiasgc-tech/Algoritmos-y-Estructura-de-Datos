package aed;

public class Horario {
    private int _hora;
    private int _minutos;
    public Horario(int hora, int minutos) {
        this._hora = hora;
        this._minutos = minutos;
    }

    public int hora() {
        return _hora;
    }

    public int minutos() {
        return _minutos;
    }

    @Override
    public String toString() {
        StringBuffer sbuffer = new StringBuffer();
        sbuffer.append(hora());
        sbuffer.append(":");
        sbuffer.append(minutos());
        return sbuffer.toString();
    }

    @Override
    public boolean equals(Object otro) {
        boolean oen = (otro == null);
        boolean cd = otro.getClass() != this.getClass();
        if (oen || cd) {
        return false;
        }
        Horario otraHora = (Horario) otro;
        return _hora == otraHora._hora 
            && _minutos == otraHora._minutos;
    }

}
