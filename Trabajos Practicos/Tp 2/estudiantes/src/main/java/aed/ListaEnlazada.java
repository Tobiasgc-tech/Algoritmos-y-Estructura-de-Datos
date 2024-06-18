package aed;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo primero;
    private Nodo ultimo; 
    private int largo;

    private class Nodo {
        private T valor;
        private Nodo sig;
        private Nodo ante; 
        public Nodo(T v) {
            this.valor = v;
            this.sig = null;
            this.ante = null;
        }
    }    
    public ListaEnlazada() {
        primero = null;
        ultimo = null;
        largo = 0;
    }

    public int longitud() {
        return largo;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (largo == 0) {
         primero = nuevo;
         ultimo = nuevo;
        } else {
         nuevo.sig = primero;
         primero.ante = nuevo;
         primero = nuevo;
        }
        largo++;
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (largo == 0) {
         primero = nuevo;
         ultimo = nuevo;
        } else {
         nuevo.ante = ultimo;
         ultimo.sig = nuevo;
         ultimo = nuevo;
        }
        largo++;
    }

    public T obtener(int i) {
        Nodo nodo = buscarNodoIesimo(i);
        return nodo.valor;
    }

    public void eliminar(int i) {
        Nodo actual = buscarNodoIesimo(i);
        if (i == 0) {
            primero = actual.sig;
        } else {
            actual.ante.sig = actual.sig;
        }
        if (i == largo -1) {
           ultimo = actual.ante;
        } else {
            actual.sig.ante = actual.ante;
        }
        largo--;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo actual = buscarNodoIesimo(indice);
        actual.valor = elem;
    }

    public ListaEnlazada<T> copiar() {
        return new ListaEnlazada<>(this);
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        int i = 0;
        while (i < lista.longitud()) {
            this.agregarAtras(lista.obtener(i));
            i++;
        }
    }
    
    @Override
    public String toString() {
        int i = 0;
        StringBuffer listaEscrita = new StringBuffer();
        listaEscrita.append("[");
        while (i < largo - 1) {
            listaEscrita.append(obtener(i) + ", ");
            i++;
        }
        listaEscrita.append((obtener(i)) + "]");
        return listaEscrita.toString();
    }

    private Nodo buscarNodoIesimo(int i) {
        Nodo actual = primero;
        for (int j = 0; j < i; j++) {
            actual = actual.sig;  
        }
        return actual;
    }

    private class ListaIterador implements Iterador<T> {
    	private Nodo nodo;
        private T valor;
        
        public ListaIterador(){
            this.nodo = null;
            this.valor = null;
        }

        public boolean haySiguiente() {
	        boolean primeraPosNoVacia = nodo == null && primero != null;
            boolean HaySiguiente = nodo != null && nodo.sig != null;
            return primeraPosNoVacia || HaySiguiente;
        }
        
        public boolean hayAnterior() {
	        if (nodo == null) return false;
            if (valor == null) return false;
            return nodo.valor != null;
        }

        public T siguiente() {
           if (nodo == null) {
            nodo = primero;
            valor = nodo.valor;
            return valor;
           } else if (nodo.sig != null) {
            nodo = nodo.sig;
            valor = nodo.valor;
            return valor;
           }
           return null;
        }
        

        public T anterior() {
            if (nodo.valor == valor) {
                nodo = nodo.ante;
                return valor;
            } else {
              valor = nodo.valor;
              nodo = nodo.ante;
            }
            return valor;
    }
    }
    public Iterador<T> iterador() {
       return new ListaIterador();
    }

}
