package Tema3.Boletin1.Ejercicio3;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEjercicio3 {
    public static void main(String[] args) {
        int puerto = 6000;
        ServerSocket servidor = null;
        int clientesPermitidos = 3;
        
        try {
            servidor = new ServerSocket(puerto);
            System.out.println("Servidor escuchando en el puerto " + puerto);
            System.out.println("Esperando hasta " + clientesPermitidos + " clientes...\n");
            
            // Atender hasta 3 clientes, uno a la vez
            for (int i = 1; i <= clientesPermitidos; i++) {
                System.out.println("Esperando cliente " + i + "...");
                Socket cliente = servidor.accept();
                
                System.out.println("Cliente " + i + " conectado desde: " + cliente.getInetAddress());
                
                // Enviar mensaje al cliente indicando su número
                PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
                salida.println("Eres el cliente número " + i);
                
                System.out.println("Mensaje enviado al cliente " + i + "\n");
                
                // Cerrar la conexión con este cliente
                cliente.close();
            }
            
            System.out.println("Se han atendido los " + clientesPermitidos + " clientes. Servidor finalizando.");
            
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (servidor != null && !servidor.isClosed()) {
                    servidor.close();
                    System.out.println("Servidor cerrado.");
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el servidor: " + e.getMessage());
            }
        }
    }
}