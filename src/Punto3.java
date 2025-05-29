
public class Punto3 {
    public static boolean balancear(String expresion) {
        Nodo2 pila = null;  // La pila empieza vacía
        
        // Se recorre la expresión carácter por carácter
        for (int i = 0; i < expresion.length(); i++) {
            char simbolo = expresion.charAt(i);
            
            // Si se encuentra un símbolo de apertura, se agrega a la pila
            if (simbolo == '(' || simbolo == '[' || simbolo == '{') {
                pila = insertarPila(pila, simbolo);
            } 
            // Si se encuentra un símbolo de cierre, se verifica que coincida con el símbolo en la pila
            else if (simbolo == ')' || simbolo == ']' || simbolo == '}') {
                if (pila == null || !SimbolosCorresponden(pila.dato, simbolo)) {
                    return false;  // No hay un símbolo de apertura correspondiente
                }
                pila = pila.siguiente;  // Se quita el símbolo de la pila
            } 
            else {
                System.out.println("Valor invalido. El programa finalizará.");
                return false;
            }
        }
        
        // Si la pila está vacía al final, la expresión si está balanceada
        return pila == null;
    }

    // Método para insertar un nuevo símbolo en la pila
    public static Nodo2 insertarPila(Nodo2 pila, char simbolo) {
        Nodo2 nuevoNodo = new Nodo2(simbolo);
        nuevoNodo.siguiente = pila;  // Se apunta el siguiente del nuevo nodo al anterior
        return nuevoNodo;  // El nuevo nodo se convierte en el tope de la pila
    }

    // Método para verificar si el símbolo de apertura y cierre coinciden
    public static boolean SimbolosCorresponden(char apertura, char cierre) {
        return (apertura == '(' && cierre == ')') || 
               (apertura == '[' && cierre == ']') || 
               (apertura == '{' && cierre == '}');
    }


}
