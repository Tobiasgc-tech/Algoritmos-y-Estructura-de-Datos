package aed;

class Funciones {
    int cuadrado(int x) {
        int res;
        res = x * x;
        return res;
    }

    double distancia(double x, double y) {
        double res;
        res = Math.sqrt (x*x + y*y);
        return res;
    }

    boolean esPar(int n) {
        if (n % 2 == 0){
            return true;
        }
        return false;
    }


    boolean esBisiesto(int n) {
        return ((n % 4 == 0 && n % 100 != 0) || n % 400 == 0);
    }


    int factorialIterativo(int n) {
        int res = 1;
        for (int i = 1; i <=n; i++){
            res = i * res;
        }
        return res;
    }

    int factorialRecursivo(int n) {
        int res = 1;
        if (n == 0){
            return 1;
        } else {
            res = n * factorialRecursivo(n-1);
        }

        return res;
    }

    boolean esPrimo(int n) {
        int cantDivisores = 0;
        for (int i = 1; i <= n; i++){
            if (n % i == 0){
                cantDivisores = cantDivisores + 1;
            }
        } if (cantDivisores == 2){
            return true;
        }
        return false;
    }

    int sumatoria(int[] numeros) {
        int res = 0;
        for (int numero : numeros){
            res = res + numero;
        }
        return res;
    }

    int busqueda(int[] numeros, int buscado) {
        int res = 0;
        for (int posicion = 0; posicion < numeros.length; posicion++){
            if (numeros[posicion] == buscado){
                res =  posicion;
            } 
        }
        return res;
    }


    boolean tienePrimo(int[] numeros) {
        for (int numero : numeros){
            int cantDivisores = 0;
            for (int i = 1; i <= numero; i++){
                if (numero % i == 0){
                cantDivisores = cantDivisores + 1;
                }
        } if (cantDivisores == 2){
            return true;
            }
        }
        return false;
    }

    boolean todosPares(int[] numeros) {
        for (int numero : numeros){
            if (numero % 2 != 0){
                return false;
            }
        }
        return true;
    }

    boolean esPrefijo(String s1, String s2) {
        for (int posicion = 0; posicion < s1.length(); posicion++){
            if (s1.charAt(posicion) != s2.charAt(posicion) || s1.length() > s2.length()){
                return false;
            }
        }
        return true;
    }

    boolean esSufijo(String s1, String s2) {
        String s1Invertida = "";
        String s2Invertida = "";
		for (int indice = s1.length() - 1; indice >= 0; indice--) {
			s1Invertida = s1Invertida + s1.charAt(indice);
        }for (int indice = s2.length() - 1; indice >= 0; indice--) {
			s2Invertida = s2Invertida + s2.charAt(indice);
        }for (int posicion = 0; posicion < s1Invertida.length(); posicion++){
            if (s1Invertida.charAt(posicion) != s2Invertida.charAt(posicion) || s1Invertida.length() > s2Invertida.length()){
                return false;
            }
        }
        return true;
    }
}
