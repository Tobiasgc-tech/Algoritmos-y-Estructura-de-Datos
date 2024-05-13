package aed;

public class Recordatorio {
    private String _mensaje;
    private Fecha _fecha;
    private Horario _horario;
    public Recordatorio(String mensaje, Fecha fecha, Horario horario) {
        this._mensaje = mensaje;
        this._fecha = new Fecha(fecha.dia(), fecha.mes());
        this._horario = new Horario(horario.hora(), horario.minutos());
    }

    public Horario horario() {
        return new Horario(_horario.hora(), _horario.minutos());
    }

    public Fecha fecha() {
        return new Fecha(_fecha.dia(), _fecha.mes());
    }

    public String mensaje() {
        return _mensaje;
    }

    @Override
    public String toString() {
        StringBuffer sbuffer = new StringBuffer();
        sbuffer.append(mensaje());
        sbuffer.append(" @ ");
        sbuffer.append(fecha());
        sbuffer.append(" ");
        sbuffer.append(horario());
        return sbuffer.toString();
    }

    @Override
    public boolean equals(Object otro) {
        boolean oen = (otro == null);
        boolean cd = otro.getClass() != this.getClass();
        if (oen || cd) {
        return false;
        }
        Recordatorio otroRecordatorio = (Recordatorio) otro;
        return _mensaje.equals(otroRecordatorio._mensaje)
        && _fecha.equals(otroRecordatorio._fecha)
        && _horario.equals(otroRecordatorio._horario);
    }

}
