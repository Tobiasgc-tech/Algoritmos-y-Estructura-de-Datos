package aed;

public class Fecha {
    private int _dia;
    private int _mes;
    public Fecha(int dia, int mes) {
        this._dia = dia;
        this._mes = mes;
    }


    public Integer dia() {
        return _dia;
    }

    public Integer mes() {
        return _mes;
    }

    @Override
    public String toString() {
        StringBuffer sbuffer = new StringBuffer();
        sbuffer.append(dia());
        sbuffer.append("/");
        sbuffer.append(mes());
        return sbuffer.toString();
    }

    @Override
    public boolean equals(Object otra) {
            boolean oen = (otra == null);
            boolean cd = otra.getClass() != this.getClass();
            if (oen || cd) {
            return false;
            }
            Fecha otraFecha = (Fecha) otra;
            return _dia == otraFecha._dia 
                && _mes == otraFecha._mes;
    }

    public void incrementarDia() {
         int diasEnEsteMes = diasEnMes(_mes);
         if (_dia == diasEnEsteMes) {
            _dia = 1;
            _mes++;
            if (_mes > 12){
                _mes = 1;
            }
        } else{
            _dia++;
        }
    }

    private int diasEnMes(int mes) {
        int dias[] = {
                // ene, feb, mar, abr, may, jun
                31, 28, 31, 30, 31, 30,
                // jul, ago, sep, oct, nov, dic
                31, 31, 30, 31, 30, 31
        };
        return dias[mes - 1];
    }

}