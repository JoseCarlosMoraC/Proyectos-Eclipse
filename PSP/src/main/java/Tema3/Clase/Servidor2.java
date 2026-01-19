package Tema3.Clase;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor2 {
    public static void main(String[] args) {
        int puerto = 6000;
        ServerSocket servidor = null;

        try {
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor escuchando en el puerto " + puerto);

            // Bucle para aceptar múltiples clientes
            while (true) {
                Socket clienteConectado = servidor.accept();
                System.out.println("Cliente conectado desde: " + 
                        clienteConectado.getInetAddress());

                // Crear un hilo para manejar al cliente
                ManejadorHilosCliente manejador = new ManejadorHilosCliente(clienteConectado);
                manejador.start();
            }

        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (servidor != null)
                    servidor.close();
                System.out.println("Servidor finalizado.");
            } catch (IOException e) {
                System.err.println("Error al cerrar servidor: " + e.getMessage());
            }
        }
    }
}