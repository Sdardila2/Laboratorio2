import java.util.Scanner;

public class LAB2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        NodoDoble ptr1 = null;
        NodoCircular ptr2 = null;
        Nodo1 A = null;
        int opc = 0;
        boolean continuar = true;

        while (continuar) {
            // Menú del Laboratorio
            System.out.println("\n--- LABORATORIO DEL PARCIAL ---");
            System.out.println("1. Punto 1");
            System.out.println("2. Punto 2");
            System.out.println("3. Punto 3");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");

            // Se obtiene la opción ingresada por el usuario a través de un método que transforma la cadena ingresada en un número
            opc = obtenerNumero(sc);

            // Se valida si la opción es 1, 2, 3 o 4
            if (opc != 1 && opc != 2 && opc != 3 && opc != 4) {
                System.out.println("Opción invalida. El programa finalizará.");
                continuar = false; // Se termina el programa
            } else if (opc == 1) {
                // Ingreso del tamaño de la matriz
                System.out.print("Ingrese el tamaño de la matriz (m x m): ");
                int m = obtenerNumero(sc);
                if (m <= 0) { // Se valida el tamaño
                    System.out.println("El tamaño debe ser mayor que cero. El programa finalizará.");
                    continuar = false;
                } else {
                    // Ingreso de los datos de la matriz 
                    System.out.println("Ingrese los valores de la matriz dispersa (fila, columna, valor):");
                    System.out.print("¿Cuántos elementos desea ingresar? ");
                    int cant = obtenerNumero(sc);

                    if (cant < 0) {  // Validar cantidad de elementos
                        System.out.println("Cantidad invalida. El programa finalizará.");
                        continuar = false;
                    } else {
                        boolean datosValidos = true; // Variable para controlar el flujo y prevenir errores
                        int i = 0;
                        while (i < cant && continuar && datosValidos) {
                            System.out.println("Elemento " + (i + 1) + ":");
                            System.out.print("Ingrese la fila: ");
                            int fila = obtenerNumero(sc);
                            if (fila < 0 || fila >= m) { // Se valida que la fila esté dentro de los límites
                                System.out.println("Fila invalida. El programa finalizará.");
                                datosValidos = false; // Deja de ingresar datos
                            } else {
                                System.out.print("Ingrese la columna: ");
                                int columna = obtenerNumero(sc);
                                if (columna < 0 || columna >= m) { // Se valida que la columna esté dentro de los límites
                                    System.out.println("Columna invalida. El programa finalizará.");
                                    datosValidos = false; // Deja de ingresar datos
                                } else {
                                    System.out.print("Ingrese el valor: ");
                                    int valor = obtenerNumero(sc);
                                    if (valor < 0) { // Se valida que el valor sea positivo
                                        System.out.println("Valor invalido. El programa finalizará.");
                                        datosValidos = false; //Deja de ingresar datos
                                    } else {
                                        // Si todo esta validado, se inserta el valor en la matriz 
                                        if (datosValidos) {
                                            A = Punto.insertarEnLista(A, fila, columna, valor);
                                        }
                                    }
                                }
                            }
                            i++;
                        }

                        // Se muestra la matriz original
                        if (continuar && datosValidos) {
                            System.out.println("\nMatriz Original:");
                            Punto.mostrarMatriz(A, m);
                        }
                    }
                }

            } else if (opc == 2) {
                // Ingreso de datos para PTR1 (lista doblemente enlazada)
                System.out.print("¿Cuántos elementos desea insertar en PTR1? ");
                int n1 = obtenerNumero(sc);
                if (n1 < 0) {
                    System.out.println("Cantidad no válida. El programa finalizará.");
                    continuar = false; // Se termina el programa si la cantidad es menor que 0
                } else {
                    for (int i = 0; i < n1 && continuar; i++) {
                        System.out.print("Ingrese el valor #" + (i + 1) + " para PTR1: ");
                        int val = obtenerNumero(sc);
                        if (val == -1) {
                            continuar = false; // Se termina el programa si se ingresa un valor no esperado
                        } else {
                            ptr1 = Punto2.insertarOrdenado(ptr1, val);
                        }
                    }
                }

                // Ingreso de datos para PTR2 (lista circular)
                if (continuar) {
                    System.out.print("¿Cuántos elementos desea insertar en PTR2? ");
                    int n2 = obtenerNumero(sc);
                    if (n2 < 0) {
                        System.out.println("Cantidad no válida. El programa finalizará.");
                        continuar = false; // Se termina el programa si la cantidad es menor que 0
                    } else {
                        for (int i = 0; i < n2 && continuar; i++) {
                            System.out.print("Ingrese el valor #" + (i + 1) + " para PTR2: ");
                            int val = obtenerNumero(sc);
                            if (val == -1) {
                                continuar = false; // Se termina el programa si se ingresa un valor no esperado
                            } else {
                                ptr2 = Punto2.insertarCircular(ptr2, val);
                            }
                        }
                    }
                }

                // Se muestran los datos de las listas antes de las operaciones
                if (continuar) {
                    System.out.println("\nListas antes del procesamiento:");
                    Punto2.mostrarDoble(ptr1);
                    Punto2.mostrarCircular(ptr2);

                    // Se llaman los metodos
                    ptr1 = Punto2.procesarListas(ptr1, ptr2);
                    ptr2 = Punto2.destruirCircular(ptr2);

                    // Se muestran los datos de las listas antes de las operaciones
                    System.out.println("\nListas después del procesamiento:");
                    Punto2.mostrarDoble(ptr1);
                    Punto2.mostrarCircular(ptr2);
                }
            } else if (opc == 3){
                System.out.print("Ingrese una expresión para validar (paréntesis, corchetes y llaves): ");
                String expresion = sc.nextLine();

                // Se llama a la función que valida la expresión balanceada
                if (Punto3.balancear(expresion)) {
                    System.out.println("La expresión está balanceada.");
                } else {
                    System.out.println("La expresión no está balanceada.");
                }
            } else {
                continuar = false;
            }
        }
    }

    // Método para obtener un número validado
    public static int obtenerNumero(Scanner sc) {
        String entrada = sc.nextLine();
        try {
            return Integer.parseInt(entrada); // Se convierte la entrada a un número entero
        } catch (NumberFormatException e) {
            System.out.println("Error: Debe ingresar un número entero. El programa finalizará.");
            return -1; // Indica que se ha producido un error y el programa debe terminar
        }
    }
}