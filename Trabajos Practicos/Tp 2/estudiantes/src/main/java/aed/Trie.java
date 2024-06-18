package aed;
import java.util.ArrayList;

public class Trie<T> implements Diccionario<T> {
    private Nodo<T> raiz;
    private int largo;

    private static class Nodo<T> {
        ArrayList<Nodo<T>> siguientes; //utilizo la clase ya dada por java ArrayList que permite trabajar con tipos T
        T valor;

        public Nodo() { //constructor de la clase Nodo
            siguientes = new ArrayList<>(256); //inicializo con el tamano del Array que voy a necesitar(ASCII)
            for (int i = 0; i < 256; i++) { //seteo en null todos sus valores(chequear)
                siguientes.add(null); 
            }
            valor = null; // Inicializa el valor como null
        }
    }

    public Trie() { //constructor de la clase Nodo
        raiz = new Nodo<>();
        largo = 0;
    }

    public int tamano(){
        return largo;
    }

    //metodo substring(index) me permite quedarme con una parte del satring comenzando desde index. Lo vamos a usar
    //para hacer la funcion recursiva quedandonos con todos los caracteres excepto el primero

    //metodo charAt(index) nos devuelve el caracter en la poscion index. Lo vamos a usar para ver el primer caracter
    
    public boolean esta(String clave) {
        Nodo<T> nodo = get(raiz, clave);
        return nodo != null && nodo.valor != null;
    }

    public void insert(String clave, T valor) {
        raiz = insert(raiz, clave, valor);
    }

    private Nodo<T> insert(Nodo<T> nodo, String clave, T valor) {
        if (nodo == null) {
            nodo = new Nodo<>();
        }
        if (clave.isEmpty()) { //termino cuando termine la clave
            if (nodo.valor == null) {
                largo++;
            }
            nodo.valor = valor;
            return nodo;
        }
        int caracter = (int) clave.charAt(0); //primera letra de clave
        nodo.siguientes.set(caracter, insert(nodo.siguientes.get(caracter), clave.substring(1), valor));
        return nodo;
    }

    public T get(String clave) {
        Nodo<T> nodo = get(raiz, clave);
        if (nodo != null && nodo.valor != null) {
            return nodo.valor;
        } else {
            return null;
        }
    }

    private Nodo<T> get(Nodo<T> nodo, String clave) {
        if (nodo == null) {
            return null;
        }
        if (clave.isEmpty()) {//para cuando termino la clave
            return nodo;
        }
        int caracter = (int) clave.charAt(0); //primera letra de clave
        nodo = nodo.siguientes.get(caracter); //voy al nodo con la primera letra(nodo siguiente)
        return get(nodo, clave.substring(1)); //recursion tomando ahora la clave sin la primer letra
    }

    public void delete(String clave) {
        raiz = delete(raiz, clave);
    }

    private Nodo<T> delete(Nodo<T> nodo, String clave) {
        if (nodo == null) {
            return null;
        }
        if (clave.isEmpty()) {
            if (nodo.valor != null) {
                largo--;
                nodo.valor = null;
            }
        } else {
            int caracter = (int) clave.charAt(0);
            nodo.siguientes.set(caracter, delete(nodo.siguientes.get(caracter), clave.substring(1)));
        }
        // Eliminar nodos vacíos innecesarios
        if (nodo.valor == null) {
            boolean tieneHijos = false;
            for (Nodo<T> siguiente : nodo.siguientes) {
                if (siguiente != null) {
                    tieneHijos = true;
                    break;
                }
            }
            if (!tieneHijos) {
                nodo = null;
            }
        }
        return nodo;
    }

    public String[] toStringArray() {
        ArrayList<String> elements = new ArrayList<>();
        toStringAux(raiz, "", elements);
        return elements.toArray(new String[0]);
    }

    private void toStringAux(Nodo<T> nodo, String prefix, ArrayList<String> elements) {
        if (nodo != null) {
            if (nodo.valor != null) {
                elements.add(prefix);
            }
            for (int i = 0; i < nodo.siguientes.size(); i++) {
                if (nodo.siguientes.get(i) != null) {
                    char ch = (char) i;
                    toStringAux(nodo.siguientes.get(i), prefix + ch, elements);
                }
            }
        }
    }
}
    