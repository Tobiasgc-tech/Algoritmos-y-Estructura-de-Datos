package aed;

import java.util.*;

// Todos los tipos de datos "Comparables" tienen el método compareTo()
// elem1.compareTo(elem2) devuelve un entero. Si es mayor a 0, entonces elem1 > elem2
public class ABB<T extends Comparable<T>> implements Conjunto<T> {
    Nodo _raiz;
    private int _cardinal;

    private class Nodo {
        T valor;
        Nodo left;
        Nodo right;
        Nodo up;

        Nodo (T v) {
            valor = v;
            left = null;
            right = null;
            up  =null;
        }
    }

    public ABB() {
        _raiz = null;
        _cardinal = 0;
    }

    public int cardinal() {
        return _cardinal;
    }

    public T minimo(){
        Nodo actual =  _raiz;
        if (actual == null) {
            return null;
        }
        while (actual.left != null) {
            actual = actual.left;
        }
        return actual.valor;
    }

    public T maximo(){
        Nodo actual =  _raiz;
        if (actual == null) {
            return  null;
        }
        while (actual.right != null) {
            actual = actual.right;
        }
        return actual.valor;
    }

    public void insertar(T elem){
        Nodo actual = _raiz;
        Nodo up = null;
        while (actual != null) {
            up = actual;
            if (elem.compareTo(actual.valor) < 0) {
                actual = actual.left;
            } else if (elem.compareTo(actual.valor) > 0){
                actual = actual.right;
            } else {
                return;
            }
        }
        Nodo nuevoNodo = new Nodo(elem);
        nuevoNodo.up = up;
        if (up == null) {
            _raiz = nuevoNodo;
        } else {
            if (elem.compareTo(up.valor) < 0) {
                up.left = nuevoNodo;
            } else {
                up.right = nuevoNodo;
            }
        }
        _cardinal ++;
    }

    public boolean pertenece(T elem){
        Nodo actual = _raiz;
        while (actual != null) {
            if (elem.compareTo(actual.valor) == 0) {
                return true;
            } else if (elem.compareTo(actual.valor) < 0) {
                actual = actual.left;
            } else {
                actual = actual.right;
            }
        }
        return false;
    }

    public void eliminar(T elem) {
        Nodo actual = _raiz;
        while (actual != null && elem.compareTo(actual.valor) != 0) {
            if (elem.compareTo(actual.valor) < 0) {
                actual = actual.left;
            } else {
                actual = actual.right;
            }
        }
        if (actual == null) {
            return;
        }
        if (actual.left == null && actual.right == null) {
            if (actual == _raiz) {
                _raiz = null;
            } else if (actual.up.left == actual) {
                actual.up.left = null;
            } else {
                actual.up.right = null;
            }
        } else if (actual.right == null) {
            if (actual == _raiz) {
                _raiz = actual.left;
            } else if (actual.up.left == actual) {
                actual.up.left = actual.left;
            } else {
                actual.up.right = actual.left;
            }
            actual.left.up = actual.up;
        } else if (actual.left == null) {
            if (actual == _raiz) {
                _raiz = actual.right;
            } else if (actual.up.left == actual) {
                actual.up.left = actual.right;
            } else {
                actual.up.right = actual.right;
            }
            actual.right.up = actual.up;
        } else {
            Nodo reemplazo = obtenerNodoReemplazo(actual);
            if (reemplazo.up != actual) {
                reemplazo.up.left = reemplazo.right;
                if (reemplazo.right != null) {
                    reemplazo.right.up = reemplazo.up;
                }
                reemplazo.right = actual.right;
                actual.right.up = reemplazo;
            }
            reemplazo.left = actual.left;
            actual.left.up = reemplazo;
            if (actual == _raiz) {
                _raiz = reemplazo;
            } else if (actual.up.left == actual) {
                actual.up.left = reemplazo;
            } else {
                actual.up.right = reemplazo;
            }
            reemplazo.up = actual.up;
        }
        _cardinal--;
    }

    private Nodo obtenerNodoReemplazo(Nodo nodoReemplazar) {
        Nodo reemplazoPadre = nodoReemplazar;
        Nodo reemplazo = nodoReemplazar.right;
        while (reemplazo.left != null) {
            reemplazoPadre = reemplazo;
            reemplazo = reemplazo.left;
        }
        if (reemplazo != nodoReemplazar.right) {
            reemplazoPadre.left = reemplazo.right;
            if (reemplazo.right != null) {
                reemplazo.right.up = reemplazoPadre;
            }
        }
        return reemplazo;
    }

    

    public String toString(){
        StringBuilder sbuffer = new StringBuilder();
        sbuffer.append("{");

        Iterador<T> iterador = iterador();
        while (iterador.haySiguiente()) {
        sbuffer.append(iterador.siguiente());
        if (iterador.haySiguiente()) {
            sbuffer.append(",");
            }
        }
        sbuffer.append("}");
        return sbuffer.toString();
    }

    private class ABB_Iterador implements Iterador<T> {
        private Nodo actual = obtenerNodoMasIzquierdo(_raiz);

        public boolean haySiguiente() {
            return actual != null;
        }

        public T siguiente() {
            T valor = actual.valor;
            actual = siguienteNodoInorden(actual);
            return valor;
        }

        private Nodo obtenerNodoMasIzquierdo(Nodo nodo) {
            while (nodo != null && nodo.left != null) {
                nodo = nodo.left;
            }
            return nodo;
        }

        private Nodo siguienteNodoInorden(Nodo nodo) {
            if (nodo.right != null) {
                return obtenerNodoMasIzquierdo(nodo.right);
            } else {
                Nodo padre = nodo.up;
                Nodo hijo = nodo;
                while (padre != null && hijo == padre.right) {
                    hijo = padre;
                    padre = padre.up;
                }
                return padre;
            }
        }
    }

    public Iterador<T> iterador() {
        return new ABB_Iterador();
    }

}
