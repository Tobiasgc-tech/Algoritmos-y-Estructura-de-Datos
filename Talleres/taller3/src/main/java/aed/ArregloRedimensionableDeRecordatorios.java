package aed;

class ArregloRedimensionableDeRecordatorios implements SecuenciaDeRecordatorios {
    private int _longitud;
    private Recordatorio[] _lista;
    private int _capacidad = 20; 
    public ArregloRedimensionableDeRecordatorios() {
        this._lista = new Recordatorio[_capacidad];
        this._longitud = 0;
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        throw new UnsupportedOperationException("No implementada aun");
    }

    public int longitud() {
        return _longitud;
    }

    public void agregarAtras(Recordatorio i) {
        _lista[_longitud] = i;
        _longitud++;
    }

    public Recordatorio obtener(int i) {
        return _lista[i];
    }

    public void quitarAtras() {
        _longitud--;
    }

    public void modificarPosicion(int indice, Recordatorio valor) {
        throw new UnsupportedOperationException("No implementada aun");

    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        throw new UnsupportedOperationException("No implementada aun");
    }

}
