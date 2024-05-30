package aed;

import java.util.*;

public class ListaEnlazada<T> implements Secuencia<T> {
    private Nodo primero;
    private Nodo ultimo;
    private int longitud;

    private class Nodo {
        T valor;
        Nodo siguiente;
        Nodo anterior;
        Nodo (T v) { 
            this.valor = v; 
            this.siguiente = null;
            this.anterior = null;
        }
    }

    public ListaEnlazada() {
        this.primero = null;
        this.ultimo = null;
        this.longitud = 0;
    }
    
    public int longitud() {
        return longitud;
    }

    public void agregarAdelante(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (longitud == 0){
            nuevo.siguiente = null;
            nuevo.anterior = null;
            ultimo = nuevo;
            primero = nuevo;
        }
        else{
            nuevo.siguiente = primero;
            primero.anterior = nuevo;
            nuevo.anterior = null;
            primero = nuevo;}
        longitud++;
    }

    public void agregarAtras(T elem) {
        Nodo nuevo = new Nodo(elem);
        if (longitud == 0){
            nuevo.siguiente = null;
            nuevo.anterior = null;
            primero = nuevo;
            ultimo = nuevo;
        }else{
            ultimo.siguiente = nuevo;
            nuevo.anterior = ultimo;
            nuevo.siguiente = null;
            ultimo = nuevo;
        }
        longitud++;
    }

    public T obtener(int i) {
        Nodo actual = primero;
        for (int j = 0; j < i; j++){
            actual= actual.siguiente;
        }
        return actual.valor;
    }

    public void eliminar(int i) {
        Nodo actual = primero;
        for(int j = 0; j < i; j++) {
            actual = actual.siguiente;
        }
        if (i == 0){
            primero = actual.siguiente;
            if (primero != null ) {
                primero.anterior = null;
            } else{
                ultimo = null;
            }
        }else{
            if (actual.siguiente != null){
                actual.siguiente.anterior = actual.anterior;
            } else {
                ultimo = actual.anterior;
            }
            actual.anterior.siguiente = actual.siguiente;            
        }
        longitud--;
    }

    public void modificarPosicion(int indice, T elem) {
        Nodo actual = primero;
        Nodo nuevo = new Nodo(elem);
        for(int j = 0; j < indice; j++) {
            actual = actual.siguiente;
        }
        if (indice == 0) {
            nuevo.siguiente = actual.siguiente;
            if (actual.siguiente != null) {
                actual.siguiente.anterior = nuevo;
            }
            nuevo.anterior = null;
            primero = nuevo;
        } else {
            if (actual.siguiente != null) {
                nuevo.siguiente = actual.siguiente;
                nuevo.anterior = actual.anterior;
                actual.siguiente.anterior = nuevo;
                actual.anterior.siguiente = nuevo;
            } else {
                nuevo.siguiente = null;
                nuevo.anterior = actual.anterior;
                actual.anterior.siguiente = nuevo;
                ultimo = nuevo;
            }
        }
}

    public ListaEnlazada<T> copiar() {
        ListaEnlazada<T> copia = new ListaEnlazada<>();
        if (primero == null){
            return copia;
        }
        Nodo actual = primero;
        Nodo nuevoNodo = new Nodo(actual.valor);
        copia.primero = nuevoNodo;
        Nodo copiaActual = copia.primero;
        actual = actual.siguiente;
        copia.longitud++;
        while (actual != null) {
            nuevoNodo = new Nodo(actual.valor);
            copiaActual.siguiente = nuevoNodo;
            nuevoNodo.anterior = copiaActual;
            copiaActual = nuevoNodo;
            actual = actual.siguiente;
            copia.longitud++;
        }
        copia.ultimo = copiaActual;
        return copia;
    }

    public ListaEnlazada(ListaEnlazada<T> lista) {
        if (lista.primero == null) {
            return;
        }
        Nodo actual = lista.primero;
        Nodo nuevoNodo = new Nodo(actual.valor);
        this.primero = nuevoNodo;
        Nodo copiaActual = this.primero;
        this.longitud = lista.longitud;
        actual = actual.siguiente;
        while (actual != null) {
            nuevoNodo = new Nodo(actual.valor);
            copiaActual.siguiente = nuevoNodo;
            nuevoNodo.anterior = copiaActual;
            copiaActual = nuevoNodo;
            actual = actual.siguiente;
        }
        this.ultimo = copiaActual;
    }
    
    @Override
    public String toString() {
        StringBuffer sbuffer = new StringBuffer();
        sbuffer.append("[");
        Nodo actual = primero;
        sbuffer.append(actual.valor);
        sbuffer.append(",");
        while(actual.siguiente != ultimo ){
            sbuffer.append(" ");
            sbuffer.append(actual.siguiente.valor);
            sbuffer.append(",");
            actual= actual.siguiente;
        }
        sbuffer.append(" ");
        sbuffer.append(ultimo.valor);
        sbuffer.append("]");
        return sbuffer.toString();
    }

    private class ListaIterador implements Iterador<T> {
    	private int posicion;
        private Nodo actual = primero;
        
        public boolean haySiguiente() {
	        return posicion != longitud;
        }
        
        public boolean hayAnterior() {
	        return posicion != 0;
        }

        public T siguiente() {
            if (actual.siguiente == null) {
                T valor = actual.valor;
                posicion++;
                return valor;
            }
            T valor = actual.valor;
            actual = actual.siguiente;
            posicion++;
            return valor;
        }
        

        public T anterior() {
            if (posicion == longitud) {
                T valor = actual.valor;
                posicion--;
                return valor;
            }
            actual = actual.anterior;
            T valor = actual.valor;
            posicion--;
            return valor;
        }
    }

    public Iterador<T> iterador() {
	    return new ListaIterador();
    }

}
