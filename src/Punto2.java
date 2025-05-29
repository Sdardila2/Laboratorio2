public class Punto2 {
    // Insertar ordenadamente en PTR1
    public static NodoDoble insertarOrdenado(NodoDoble first, int valor) {
        NodoDoble newNode = new NodoDoble(valor);

        if (first == null) {
            return newNode;
        }

        if (valor < first.valor) {
            newNode.siguiente = first;
            first.anterior = newNode;
            return newNode;
        }

        NodoDoble aux = first;
        while (aux.siguiente != null && aux.siguiente.valor < valor) {
            aux = aux.siguiente;
        }

        newNode.siguiente = aux.siguiente;
        newNode.anterior = aux;

        if (aux.siguiente != null) {
            aux.siguiente.anterior = newNode;
        }
        aux.siguiente = newNode;

        return first;
    }

    // Buscar si existe un valor en PTR1
    public static boolean existeDobles(NodoDoble first, int valor) {
        NodoDoble aux = first;
        while (aux != null) {
            if (aux.valor == valor) {
                return true;
            }
            aux = aux.siguiente;
        }
        return false;
    }

    // Eliminar un nodo por valor en PTR1
    public static NodoDoble eliminarDoble(NodoDoble first, int valor) {
        NodoDoble aux = first;

        while (aux != null) {
            if (aux.valor == valor) {
                if (aux.anterior != null) {
                    aux.anterior.siguiente = aux.siguiente;
                } else {
                    first = aux.siguiente;
                }
                if (aux.siguiente != null) {
                    aux.siguiente.anterior = aux.anterior;
                }
                aux = null;
                return first;
            }
            aux = aux.siguiente;
        }
        return first;
    }

    // Eliminar un nodo por valor en PTR2
    public static NodoCircular eliminarCircular(NodoCircular first, int valor) {
        if (first == null) return null;

        NodoCircular aux = first;
        NodoCircular anterior = null;
        boolean terminado = false;

        while (!terminado) {
            if (aux.valor == valor) {
                if (aux == first) {
                    if (first.siguiente == first) {
                        return null;
                    }
                    NodoCircular ultimo = first;
                    while (ultimo.siguiente != first) {
                        ultimo = ultimo.siguiente;
                    }
                    first = first.siguiente;
                    ultimo.siguiente = first;
                    aux = null;
                    return first;
                } else {
                    anterior.siguiente = aux.siguiente;
                    aux = null;
                    return first;
                }
            }
            anterior = aux;
            aux = aux.siguiente;
            if (aux == first) terminado = true;
        }
        return first;
    }

    // Insertar los elementos únicos de PTR2 en PTR1 y eliminar los repetidos de ambas
    public static NodoDoble procesarListas(NodoDoble ptr1, NodoCircular ptr2) {
        if (ptr2 == null) return ptr1;

        NodoCircular first = ptr2;
        NodoCircular aux = ptr2;
        boolean terminado = false;

        while (!terminado) {
            NodoCircular siguiente = aux.siguiente;

            if (existeDobles(ptr1, aux.valor)) {
                ptr1 = eliminarDoble(ptr1, aux.valor);
                ptr2 = eliminarCircular(ptr2, aux.valor);
            } else {
                ptr1 = insertarOrdenado(ptr1, aux.valor);
            }

            aux = siguiente;
            if (aux == first) terminado = true;
        }

        return ptr1;
    }

    // Se destruye la lista circular
    public static NodoCircular destruirCircular(NodoCircular first) {
        if (first == null) return null;

        NodoCircular aux = first.siguiente;
        while (aux != first) {
            NodoCircular temp = aux;
            aux = aux.siguiente;
            temp = null;
        }
        first = null;
        return null;
    }

    // Mostrar PTR1
    public static void mostrarDoble(NodoDoble first) {
        NodoDoble aux = first;
        System.out.print("PTR1: ");
        while (aux != null) {
            System.out.print(aux.valor + " ");
            aux = aux.siguiente;
        }
        System.out.println();
    }

    // Mostrar PTR2
    public static void mostrarCircular(NodoCircular first) {
        if (first == null) {
            System.out.println("PTR2: vacía");
            return;
        }
        NodoCircular aux = first;
        System.out.print("PTR2: ");
        do {
            System.out.print(aux.valor + " ");
            aux = aux.siguiente;
        } while (aux != first);
        System.out.println();
    }

    // Se agregan los datos a PTR2 (circular)
    public static NodoCircular insertarCircular(NodoCircular first, int valor) {
        NodoCircular newNode = new NodoCircular(valor);

        if (first == null) {
            newNode.siguiente = newNode;
            return newNode;
        }

        NodoCircular aux = first;
        while (aux.siguiente != first) {
            aux = aux.siguiente;
        }

        aux.siguiente = newNode;
        newNode.siguiente = first;
        return first;
    }
}
