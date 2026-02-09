package Mapas;

public class Contador {

    // Guarda el número de clientes conectados
    private int numeroconexiones;

    public Contador() {
        // Empieza en 0 por defecto
        this.numeroconexiones = 0;
    }

    public int getNumeroconexiones() {
        return numeroconexiones;
    }

    public void setNumeroconexiones(int numeroconexiones) {
        this.numeroconexiones = numeroconexiones;
    }

    @Override
    public String toString() {
        return "Contador [numeroconexiones=" + numeroconexiones + "]";
    }

    // synchronized → evita que dos hilos incrementen a la vez
    public synchronized void incrementar() {
        numeroconexiones++;
    }
}
