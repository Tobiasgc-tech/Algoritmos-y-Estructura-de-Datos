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
        _lista[indice] = valor;

    }

    public ArregloRedimensionableDeRecordatorios copiar() {
        ArregloRedimensionableDeRecordatorios _copia = new ArregloRedimensionableDeRecordatorios();
        Recordatorio[] _copiaLista = new Recordatorio[_longitud];
        _copia._longitud = _longitud;
        _copia._capacidad = _capacidad;
        for(int i = 0; i < _longitud; i++){
            _copiaLista[i] = _lista[i];
        } 
        return _copia;
    }

}
