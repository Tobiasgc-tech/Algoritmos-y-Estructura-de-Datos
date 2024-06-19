package aed;

public class Estudiante {
    private int materiasInscriptas;

    public Estudiante(){
        this.materiasInscriptas = 0;
    }

    public void incrementarMaterias() {
        materiasInscriptas++;
    }

    public void decrementarMaterias() {
        materiasInscriptas--;
    }

    public int getMateriasInscriptas() {
        return materiasInscriptas;
    }

}
