package Mapas;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMultihilo {
    public static void main(String[] args) {

        // Objeto compartido que cuenta cuántos clientes se conectan
        Contador contador = new Contador();

        // Objeto compartido que guarda los asientos reservados
        // Se comparte entre TODOS los hilos
        MapaFlyThread mapaasientos = new MapaFlyThread();

        int puerto = 4444;

        // ServerSocket se encarga de escuchar conexiones en el puerto
        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor multihilo iniciado en el puerto " + puerto);

            // El servidor nunca se apaga
            while (true) {

                // 1. Espera a que un cliente se conecte
                Socket socketCliente = servidor.accept();
                System.out.println("Nuevo cliente conectado: " + socketCliente.getInetAddress());

                // 2. Se crea un hilo NUEVO para atender a este cliente
                // Así el servidor puede seguir aceptando más clientes
                new AyudanteAtiendePeticionCliente(
                        socketCliente,
                        contador,
                        mapaasientos
                ).start();
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    }
}
