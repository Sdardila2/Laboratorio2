//Punto 1
public class Punto {
    //Metodo que multiplica las matrices 
    public static Nodo1 multiplicarMatrices(Nodo1 A, Nodo1 B) {
        Nodo1 resultado = null;

        Nodo1 filaA = A;
        while (filaA != null) {
            Nodo1 columnaB = B;
            while (columnaB != null) {
                if (filaA.columna == columnaB.fila) {
                    // Se realiza la multiplicación y suma de valores
                    int nuevoValor = filaA.valor * columnaB.valor;
                    //Se inserta el valor en una lista ordenada
                    resultado = insertarEnLista(resultado, filaA.fila, columnaB.columna, nuevoValor);
                }
                columnaB = columnaB.siguiente;
            }
            filaA = filaA.siguiente;
        }

        return resultado;
    }

    // Método para insertar un nuevo nodo en la lista enlazada manteniendo el orden
    public static Nodo1 insertarEnLista(Nodo1 cabeza, int fila, int columna, int valor) {
        Nodo1 nuevo = new Nodo1(fila, columna, valor);
        if (cabeza == null || (cabeza.fila > fila) || (cabeza.fila == fila && cabeza.columna > columna)) {
            nuevo.siguiente = cabeza;
            return nuevo;
        }

        Nodo1 actual = cabeza;
        while (actual.siguiente != null && (actual.siguiente.fila < fila || (actual.siguiente.fila == fila && actual.siguiente.columna < columna))) {
            actual = actual.siguiente;
        }

        nuevo.siguiente = actual.siguiente;
        actual.siguiente = nuevo;

        return cabeza;
    }

    // Método para multiplicar la matriz A por sí misma n veces
    public static Nodo1 potenciaMatriz(Nodo1 A, int n) {
        Nodo1 resultado = A;
        
        for (int i = 1; i < n; i++) {
            resultado = multiplicarMatrices(resultado, A);
        }

        return resultado;
    }

    // Método para mostrar la matriz dispersa
    public static void mostrarMatriz(Nodo1 A, int tamaño) {
        Nodo1 aux = A;
        int[][] matriz = new int[tamaño][tamaño];  // Creamos una matriz para representar el resultado

        // Inicializar la matriz en cero
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                matriz[i][j] = 0;
            }
        }

        // Se rellena la matriz con los valores de la lista enlazada
        while (aux != null) {
            matriz[aux.fila][aux.columna] = aux.valor;
            aux = aux.siguiente;
        }

        // Se muestra la matriz
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

}
