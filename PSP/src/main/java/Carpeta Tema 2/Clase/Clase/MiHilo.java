package Tema2.Clase;

public class MiHilo extends Thread {
    private String nombreHilo;
    private volatile boolean running = true; // flag de control

    public MiHilo(String nombreHilo) {
        super();
        this.nombreHilo = nombreHilo;
    }

    @Override
    public void run() {
        System.out.println(this.nombreHilo + " estado " + this.getState());
        int segundos = 0;
        while (running && segundos < 10) { // simula trabajo de 10 segundos
            try {
                Thread.sleep(1000); // duerme 1 segundo
                segundos++;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Termina hilo: " + this.nombreHilo);
    }

    public void detenerHilo() {
        running = false; // padre llama a esto para detener el hilo
    }
}
