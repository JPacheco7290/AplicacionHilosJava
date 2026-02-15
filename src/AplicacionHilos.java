import java.util.Scanner;

// Hilo para conteo regresivo
class HiloConteo extends Thread {
    private int numero;

    public HiloConteo(String nombre, int numero) {
        super(nombre);
        this.numero = numero;
    }

    @Override
    public void run() {
        try {
            for (int i = numero; i >= 0; i--) {
                System.out.println("[" + getName() + "] Conteo: " + i);
                Thread.sleep(500); // 500 ms
            }
            System.out.println("Trabajo del hilo " + getName() + " terminado");
        } catch (InterruptedException e) {
            System.out.println("Hilo interrumpido");
        }
    }
}

// Hilo para mostrar letras
class HiloLetras extends Thread {
    private char letraFinal;

    public HiloLetras(String nombre, char letraFinal) {
        super(nombre);
        this.letraFinal = letraFinal;
    }

    @Override
    public void run() {
        try {
            for (char c = 'A'; c <= letraFinal; c++) {
                System.out.println("[" + getName() + "] Letra: " + c);
                Thread.sleep(600); // 600 ms
            }
            System.out.println("Trabajo del hilo " + getName() + " terminado");
        } catch (InterruptedException e) {
            System.out.println("Hilo interrumpido");
        }
    }
}

public class AplicacionHilos {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int numero;
        char letra;

        // 🔹 Validación número entero positivo
        while (true) {
            System.out.print("Ingrese un número entero positivo para el conteo regresivo: ");
            if (scanner.hasNextInt()) {
                numero = scanner.nextInt();
                if (numero >= 0) {
                    break;
                } else {
                    System.out.println("Error: Debe ser un número positivo.");
                }
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                scanner.next(); // limpiar buffer
            }
        }

        // 🔹 Validación letra A-Z
        while (true) {
            System.out.print("Ingrese una letra mayúscula (A-Z): ");
            String entrada = scanner.next().toUpperCase();

            if (entrada.length() == 1) {
                letra = entrada.charAt(0);
                if (letra >= 'A' && letra <= 'Z') {
                    break;
                }
            }
            System.out.println("Error: Debe ingresar una letra válida entre A y Z.");
        }

        System.out.println("\n--- INICIO DE LOS HILOS ---\n");

        // Crear hilos
        HiloConteo hilo1 = new HiloConteo("ConteoRegresivo", numero);
        HiloLetras hilo2 = new HiloLetras("Alfabeto", letra);

        // Iniciar hilos simultáneamente
        hilo1.start();
        hilo2.start();

        scanner.close();
    }
}
