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
        throw new UnsupportedOperationException("No implementada aun");
    }

    @Override
    public boolean equals(Object otro) {
        throw new UnsupportedOperationException("No implementada aun");
    }

}
