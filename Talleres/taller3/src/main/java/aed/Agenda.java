package aed;

public class Agenda {
    private Fecha _fechaActual;
    private ArregloRedimensionableDeRecordatorios _recordatorio;

    public Agenda(Fecha fechaActual) {
        this._fechaActual = fechaActual;
        this._recordatorio = new ArregloRedimensionableDeRecordatorios(); 
    }

    public void agregarRecordatorio(Recordatorio recordatorio) {
        _recordatorio.agregarAtras(recordatorio);

    }

    @Override
    public String toString() {
        StringBuffer sbuffer = new StringBuffer();
        sbuffer.append(fechaActual());
        sbuffer.append('\n' );
        sbuffer.append("=====\n");
        for (int i = 0; i < _recordatorio.longitud(); i++){
            if (_recordatorio.obtener(i).fecha().equals(_fechaActual)){
                sbuffer.append(_recordatorio.obtener(i));
                sbuffer.append("\n");
            }
        }
        return sbuffer.toString();

    }

    public void incrementarDia() {
        _fechaActual.incrementarDia();
    }

    public Fecha fechaActual() {
        return _fechaActual;
    }

}
