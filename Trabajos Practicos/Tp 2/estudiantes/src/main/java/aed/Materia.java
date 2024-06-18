package aed;

import java.util.ArrayList;

import aed.SistemaSIU.CargoDocente;

public class Materia {
    private int [] docentes;
    private ArrayList<String> estudiantesInscriptos;
    private ArrayList<Tupla<String, Trie<Materia>>> nombresEquivalentes; 

    public Materia(int cantidadMismasMaterias, int cantidadDeEstudiantes) {
        this.docentes = new int[4];
        this.estudiantesInscriptos = new ArrayList<>(cantidadDeEstudiantes);
        this.nombresEquivalentes = new ArrayList<>(cantidadMismasMaterias);
    }

    public void inscribirEstudiante(String estudiante) {
        estudiantesInscriptos.add(estudiante);
    }

    public void agregarMateriasEquivalentes(String materia, Trie<Materia> referencia) {
        nombresEquivalentes.add(new Tupla<>(materia, referencia));
    }

    public int estudiantesInscriptos() {
        return estudiantesInscriptos.size();
    }

    public ArrayList<String> listaEstudiantes() {
        return estudiantesInscriptos;
    }

    public ArrayList<Tupla<String, Trie<Materia>>> materiaYReferencia() {
        return nombresEquivalentes;
    }

    public int largoEquivalentes() {
        return nombresEquivalentes.size();
    }

    public void agregarDocentes(CargoDocente cargo) {
        docentes[cargo.ordinal()]++;
    }

    public int[] obtenerDocentes() {
        return docentes;
    }

    public boolean excedeCupo() {
        return docentes[0]*250 < estudiantesInscriptos.size() ||
               docentes[1]*100 < estudiantesInscriptos.size() ||
               docentes[2]*20 < estudiantesInscriptos.size() ||
               docentes[3]*30 < estudiantesInscriptos.size();
    }
}
