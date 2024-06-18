package aed;

interface Diccionario<T>{
    public boolean esta(String clave);
    public void insert(String clave, T valor);
    public T get(String clave);
    public void delete(String clave);
    public int tamano();
    public String toString();
}
