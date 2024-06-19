package aed;
import java.util.ArrayList;

public class Trie<T> {
    private Nodo<T> raiz;
    private int largo;

    private static class Nodo<T> {
        ArrayList<Nodo<T>> siguientes;
        T valor;

        public Nodo() {
            siguientes = new ArrayList<>(256);
            for (int i = 0; i < 256; i++) {
                siguientes.add(null); 
            }
            valor = null;
        }
    }

    public Trie() {
        raiz = null;
        largo = 0;
    }

    public int tamaño(){
        return largo;
    }
    
    public boolean esta(String clave) {
        Nodo<T> nodo = obtenerNodo(raiz, clave);
        return nodo != null && nodo.valor != null;
    }

    public void insertar(String clave, T valor) {
        raiz = insertar(raiz, clave, valor);
    }

    private Nodo<T> insertar(Nodo<T> nodo, String clave, T valor) {
        if (nodo == null) {
            nodo = new Nodo<>();
        }
        if (clave.isEmpty()) {
            if (nodo.valor == null) {
                largo++;
            }
            nodo.valor = valor;
            return nodo;
        }
        int caracter = (int) clave.charAt(0);
        nodo.siguientes.set(caracter, insertar(nodo.siguientes.get(caracter), clave.substring(1), valor));
        return nodo;
    }

    public T obtenerValor(String clave) {
        Nodo<T> nodo = obtenerNodo(raiz, clave);
        if (nodo != null && nodo.valor != null) {
            return nodo.valor;
        } else {
            return null;
        }
    }

    private Nodo<T> obtenerNodo(Nodo<T> nodo, String clave) {
        if (nodo == null) {
            return null;
        }
        if (clave.isEmpty()) {
            return nodo;
        }
        int caracter = (int) clave.charAt(0);
        nodo = nodo.siguientes.get(caracter);
        return obtenerNodo(nodo, clave.substring(1));
    }

    public void borrar(String clave) {
        raiz = borrar(raiz, clave);
    }

    private Nodo<T> borrar(Nodo<T> nodo, String clave) {
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
            nodo.siguientes.set(caracter, borrar(nodo.siguientes.get(caracter), clave.substring(1)));
        }
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
    
    public String[] obtenerClaves() {
        ArrayList<String> claves = new ArrayList<>();
        obtenerClaves(raiz, "", claves);
        return claves.toArray(new String[0]);
    }

    private void obtenerClaves(Nodo<T> nodo, String prefijo, ArrayList<String> claves) {
        if (nodo == null) {
            return;
        }
        if (nodo.valor != null) {
            claves.add(prefijo);
        }
        for (int i = 0; i < 256; i++) {
            Nodo<T> siguiente = nodo.siguientes.get(i);
            if (siguiente != null) {
                obtenerClaves(siguiente, prefijo + (char) i, claves);
            }
        }
    }
}
