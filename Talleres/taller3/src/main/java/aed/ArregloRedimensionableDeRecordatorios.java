package aed;

class ArregloRedimensionableDeRecordatorios implements SecuenciaDeRecordatorios {
    private int _longitud;
    private Recordatorio[] _lista;
    public ArregloRedimensionableDeRecordatorios() {
        this._longitud = 0;
        this._lista = new Recordatorio[1];
    }

    public ArregloRedimensionableDeRecordatorios(ArregloRedimensionableDeRecordatorios vector) {
        this._longitud = vector._longitud;
        this._lista = new Recordatorio[vector._lista.length];
        for (int i = 0; i < vector._longitud; i++) {
            this._lista[i] = vector._lista[i];
        }

    }

    public int longitud() {
        return _longitud;
    }

    public void agregarAtras(Recordatorio i) {
        if (_longitud == _lista.length) {
            int _nuevaCapacidad = _lista.length * 2;
            Recordatorio[] listaNueva = new Recordatorio[_nuevaCapacidad];
            for (int n = 0; n < _longitud; n++) {
                listaNueva[n] = _lista[n];
            }
            _lista = listaNueva;    
        }
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
        ArregloRedimensionableDeRecordatorios copia = new ArregloRedimensionableDeRecordatorios();
        copia._longitud = _longitud;
        copia._lista = new Recordatorio[_longitud];
        for (int i = 0; i < _longitud; i++){
            copia._lista[i] = _lista[i];
            }
        return copia;
        }
}
