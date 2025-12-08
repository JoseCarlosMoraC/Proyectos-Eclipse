package Tema2.Clase;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        MiHilo hilo1 = new MiHilo("Hilo 1");
        MiHilo hilo2 = new MiHilo("Hilo 2");

        long startTime = System.nanoTime(); // inicio

        hilo1.start();
        hilo2.start();

        hilo1.join(); // espera a que termine hilo1
        hilo2.join(); // espera a que termine hilo2

        long endTime = System.nanoTime(); // fin

        long duration = (endTime - startTime) / 1_000_000; // ms
        System.out.println("Tiempo total de ejecución: " + duration + " ms");
    }
}
