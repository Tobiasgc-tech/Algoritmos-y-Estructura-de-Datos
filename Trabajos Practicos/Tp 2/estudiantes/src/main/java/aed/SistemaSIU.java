package aed;

import java.util.ArrayList;

public class SistemaSIU {

    private Trie<Integer> estudiantes;
    private Trie<Trie<Materia>> carrerasYMaterias;

    enum CargoDocente{
        PROF,
        JTP,
        AY1,
        AY2
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias) {
        this.estudiantes = new Trie<>();       // O(1)                                                                         
        for (String libreta : libretasUniversitarias) {  // O(|libretasUniversitarias|) -> que es lo mismo que E, la cantidad total de Estudiantes } estas 3 sería O(1)+O(E)*O(1) = O(E)
            estudiantes.insert(libreta, 0);        // O(1) porque las libretas están acotadas, entonces insertar es O(1)             
        }
        this.carrerasYMaterias = new Trie<>();            // O(1)
        for (InfoMateria infoDeMismaMateria : infoMaterias) {       // O(|infoMaterias|) -> el cardinal de todas las materias M (adentro tiene subconjuntos de materias que son equivalentes)
            ParCarreraMateria[] materiaPorCarrera = infoDeMismaMateria.getParesCarreraMateria(); // O(1)
            Materia infoMismaMateriaDistintoNombre = new Materia(materiaPorCarrera.length, libretasUniversitarias.length); // O(1) creo una instancia nueva de Materia
            for (ParCarreraMateria par : materiaPorCarrera) { // O(|materiaPorCarrera|) -> el cardinal de los nombres posibles de una misma materia, Nm, y en todas las carreras que aparece
                Trie<Materia> materiasDeLaCarrera = carrerasYMaterias.get(par.getCarrera()); // O(|c|) buscar en el Trie de carreras, al no estar acotada la carrera, depende del largo
                if (materiasDeLaCarrera == null) {                                                             // O(1) si la carrera no tiene materias
                    materiasDeLaCarrera = new Trie<>();                                              // O(1) creo el trie de materias
                    carrerasYMaterias.insert(par.getCarrera(), materiasDeLaCarrera);                           // O(|c|) insertar en el Trie de carreras la carrera con su trie de materias
                }
                infoMismaMateriaDistintoNombre.agregarMateriasEquivalentes(par.getNombreMateria(), materiasDeLaCarrera); // O(1) 
                materiasDeLaCarrera.insert(par.getNombreMateria(), infoMismaMateriaDistintoNombre);                      // O(|mc|) insertar en el Trie de materias de la carrera 
            }                                                                                                            // esto ultimo se termina ejecutando 1 vez por cada materia por cada carrera
        }
    }
    

    public void inscribir(String estudiante, String carrera, String materia) {                // O(|c| + |m| + O(1) + O(1) + O(1) + O(1)) = O(|c| + |m|)
        Trie<Materia> materiasDeLaCarrera = carrerasYMaterias.get(carrera);                   // O(|c|) buscar en un trie
        Materia informacionMateria = materiasDeLaCarrera.get(materia);                        // O(|m|) buscar en un trie
        informacionMateria.inscribirEstudiante(estudiante);                                   // O(1)
    
        Integer materiasDelEstudiante = estudiantes.get(estudiante);                          // O(1) estudiante esta acotado
        materiasDelEstudiante++;                                                              // O(1)
        estudiantes.insert(estudiante, materiasDelEstudiante);                                // O(1)
    }
    

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){            // O(|c| + |m| + O(1)) = O(|c| + |m|)
        Trie<Materia> materiasDeLaCarrera = carrerasYMaterias.get(carrera);                    // O(|c|)
        Materia informacionMateria = materiasDeLaCarrera.get(materia);                         // O(|m|) 
        informacionMateria.agregarDocentes(cargo);                                             // O(1)
    }

    public int[] plantelDocente(String materia, String carrera){                               // O(|c| + |m| + O(1) + O(1)) = O(|c| + |m|)
        Trie<Materia> materiasDeLaCarrera = carrerasYMaterias.get(carrera);                    // O(|c|)
        Materia informacionMateria = materiasDeLaCarrera.get(materia);                         // O(|m|)
        int [] docentes = informacionMateria.obtenerDocentes();                                // O(1)          
        return docentes;                                                                       // O(1)
    }

    public void cerrarMateria(String materia, String carrera){
        Trie<Materia> materiasDelaCarrera = carrerasYMaterias.get(carrera);                   // O(|c|)
        Materia informacionMateria = materiasDelaCarrera.get(materia);                        // O(|m|)
        int cantidadDeEstudiantes = informacionMateria.estudiantesInscriptos();               // O(1)
        int i = 0;                                                                            // O(1)
        while (i < cantidadDeEstudiantes) {                                                   // O(|cantidadDeEstudiantes|) -> que es lo mismo que la cantidad de estudiantes de la materia, Em -> O(Em)
            String LUdelEstudiante = informacionMateria.listaEstudiantes().get(i);            // O(1) obtengo la libreta 
            Integer materiasDelEstudiante = estudiantes.get(LUdelEstudiante);                 // O(1) obtengo la cantidad de materias del estudiante (LUdelEstudiante es acotada)   } todo el while adentro es O(1) -> O(Em * 1) = O(Em)
            materiasDelEstudiante--;                                                          // O(1) resto una materia
            estudiantes.insert(LUdelEstudiante, materiasDelEstudiante);                       // O(1) lo vuelvo a insertar con una materia menos (lo piso) -> al estar acotada, es O(1)
            i++;                                                                              // O(1)
        } // Sumando las complejidades de arriba y de abajo, queda O(|c|+|m|+ sumatoria |n| de los elementos que pertenecen a Nm + Em) Igual le tengo miedo por lo de la sumatoria, no se si sumatoria del largo de cada elemento n de Nm es igual a |Nm|*|n|
        materiasDelaCarrera.delete(materia);                                                  // O(|m|)
        int j = 0;                                                                            // O(1)
        int cantidadDeVecesQueEstaLaMateria = informacionMateria.largoEquivalentes();         // O(1)
        while (j < cantidadDeVecesQueEstaLaMateria) {                                         // O(|cantidadDeVecesQueEstaLaMateria|) que es el cardinal de Nm      -> todo este while es la sumatoria de borrar todas las n de cada Trie
            ArrayList<Tupla<String, Trie<Materia>>> listado = informacionMateria.materiaYReferencia();    // O(1) obtengo el listado de pares de nombre, referencia
            String materiaEnOtraCarrera = listado.get(j).getPrimero();                                                      // O(1) obtengo como se llama en la otra carrera
            Trie<Materia> trieDeLaMateria = listado.get(j).getSegundo();                                  // O(1) obtengo en qué diccionario está (por ende, en qué carrera)
            if (materiaEnOtraCarrera == materia && materiasDelaCarrera.equals(trieDeLaMateria)) j++;                        // O(1) a este metodo equals le tengo miedo, no por complejidad sino por si efectivamente es el que necesitamos o habría que reescribirlo (o buscar una nueva forma)
            else {
                trieDeLaMateria.delete(materiaEnOtraCarrera);                                                               // O(|n|) borro la materia con otro nombre de su trie correspondiente
                j++;                                                                                                        // O(1)
            }
        }
        }

    public int inscriptos(String materia, String carrera){                                    // O(|c| + |m| + O(1) + O(1)) = O(|c| + |m|)
        Trie<Materia> materiasDeLaCarrera = carrerasYMaterias.get(carrera); // O(|c|)
        Materia informacionMateria = materiasDeLaCarrera.get(materia);                        // O(|m|)
        int cantidadDeEstudiantes = informacionMateria.estudiantesInscriptos();               // O(1)
        return cantidadDeEstudiantes;                                                         // O(1)
    }

    public boolean excedeCupo(String materia, String carrera){                                // O(|c| + |m| + O(1)) = O(|c| + |m|)
        Trie<Materia> materiasDeLaCarrera = carrerasYMaterias.get(carrera); // O(|c|)
        Materia informacionMateria = materiasDeLaCarrera.get(materia);                        // O(|m|)
        return informacionMateria.excedeCupo();	                                              // O(1)
    }

    public String[] carreras(){    // O(1 + sumatoria de |c| para cada carrera de C) -> O(sumatoria de todas las |c| de C)
        String[] listaCarreras = carrerasYMaterias.toStringArray();   // La complejidad de esto es la sumatoria de largo de las llaves
        return listaCarreras;                                   //O(1)
    }                                                                  
                          //EN AMBOS HAY QUE EXPLICAR MEJOR LA COMPLEJIDAD DEL GET KEYS, SE PUEDE EXPLICAR EN LA IMPLEMENTACION DE TRIE
    public String[] materias(String carrera){ // O(|c| + la sumatoria del largo de cada llave del Trie de Mc)
        Trie<Materia> listaMaterias = carrerasYMaterias.get(carrera);  //O(|c|)
        String[] materias = listaMaterias.toStringArray();                                    // la sumatoria del largo de cada llave del Trie de Mc
        return materias;	                                                            // O(1)                 
    }

    public int materiasInscriptas(String estudiante){
        int materiasDelEstudiante = estudiantes.get(estudiante);  //O(1) xq buscas por libreta la cual esta acotada
        return materiasDelEstudiante;   
    }
}