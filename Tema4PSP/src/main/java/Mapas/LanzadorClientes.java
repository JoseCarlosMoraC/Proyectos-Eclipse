package Mapas;

public class LanzadorClientes {
    public static void main(String[] args) {

        // Lanza clientes en paralelo
        // Cambia el 1 por 5, 10, etc. para simular varios clientes
        for (int i = 0; i < 1; i++) {

            // Cada cliente es un hilo
            Thread clientehilo1 = new Thread(new ClienteHilo());
            clientehilo1.start();
        }
    }
}
