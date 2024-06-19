package aed;

public class Carrera {
    private String nombre;
    private Trie<Materia> materias;

    public Carrera(String nombre) {
        this.nombre = nombre;
        this.materias = new Trie<>();
    }

    public String getNombre() {
        return nombre;
    }

    public Trie<Materia> getMaterias() {
        return materias;
    }

    public void agregarMateria(String nombreMateria, Materia materia) {
        materias.insertar(nombreMateria, materia);
    }

    public Materia obtenerMateria(String nombreMateria) {
        return materias.obtenerValor(nombreMateria);
    }

    public void borrarMateria(String nombreMateria) {
        materias.borrar(nombreMateria);
    }

    public String[] listarMaterias() {
        return materias.obtenerClaves();
    }
}
