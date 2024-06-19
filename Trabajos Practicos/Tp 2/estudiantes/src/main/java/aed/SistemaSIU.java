package aed;

import java.util.ArrayList;

public class SistemaSIU {

    private Trie<Estudiante> estudiantes;
    private Trie<Carrera> carreras;

    enum CargoDocente{
        PROF,
        JTP,
        AY1,
        AY2
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias) {
        this.estudiantes = new Trie<>();
        for (String libreta : libretasUniversitarias) {
            estudiantes.insertar(libreta, new Estudiante());
        }
        this.carreras = new Trie<>();
        for (InfoMateria infoDeMismaMateria : infoMaterias) {
            ParCarreraMateria[] materiaPorCarrera = infoDeMismaMateria.getParesCarreraMateria();
            Materia infoMismaMateriaDistintoNombre = new Materia(materiaPorCarrera.length, libretasUniversitarias.length);
            for (ParCarreraMateria par : materiaPorCarrera) {
                Carrera carrera = carreras.obtenerValor(par.getCarrera());
                if (carrera == null) {
                    carrera = new Carrera(par.getCarrera());
                    carreras.insertar(par.getCarrera(), carrera);
                }
                infoMismaMateriaDistintoNombre.agregarMateriasEquivalentes(par.getNombreMateria(), carrera.getMaterias());
                carrera.agregarMateria(par.getNombreMateria(), infoMismaMateriaDistintoNombre);
            }
        }
    }
    

    public void inscribir(String estudiante, String carrera, String materia) { 
        Carrera c = carreras.obtenerValor(carrera);
        Materia m = c.getMaterias().obtenerValor(materia);
        m.inscribirEstudiante(estudiante);
        Estudiante e = estudiantes.obtenerValor(estudiante);
        e.incrementarMaterias();                             
    }
    

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){           
        Carrera c = carreras.obtenerValor(carrera);
        Materia m = c.obtenerMateria(materia);
        m.agregarDocentes(cargo);                                           
    }

    public int[] plantelDocente(String materia, String carrera){ 
        Carrera c = carreras.obtenerValor(carrera);
        Materia m = c.obtenerMateria(materia);
        return m.obtenerDocentes();                                                                       
    }

    public void cerrarMateria(String materia, String carrera){
        Carrera c = carreras.obtenerValor(carrera);
        Materia m = c.obtenerMateria(materia);
        for (String estudiante : m.listaEstudiantes()) {
            Estudiante e = estudiantes.obtenerValor(estudiante);
            e.decrementarMaterias();
        }
        c.borrarMateria(materia);
        for (Tupla<String, Trie<Materia>> tupla : m.materiaYReferencia()) {
            if (!tupla.getPrimero().equals(materia) || !tupla.getSegundo().equals(c.getMaterias())) {
                tupla.getSegundo().borrar(tupla.getPrimero());
            }
        }
    }

    public int inscriptos(String materia, String carrera){                                  
        Carrera c = carreras.obtenerValor(carrera);
        Materia m = c.obtenerMateria(materia);
        return m.estudiantesInscriptos();                                                       
    }

    public boolean excedeCupo(String materia, String carrera){                                
        Carrera c = carreras.obtenerValor(carrera);
        Materia m = c.obtenerMateria(materia);
        return m.excedeCupo();                                          
    }

    public String[] carreras(){    
        return carreras.obtenerClaves();                                 
    }                                                                  
                         
    public String[] materias(String carrera){ 
        Carrera c = carreras.obtenerValor(carrera);
        return c.listarMaterias();	              
    }

    public int materiasInscriptas(String estudiante){
        Estudiante e = estudiantes.obtenerValor(estudiante);
        return e.getMateriasInscriptas();  
    }
}