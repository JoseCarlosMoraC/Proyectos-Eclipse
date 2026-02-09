package Boletin1.Ejercicio3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClienteEjercicio3 {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 6000;
        Socket cliente = null;
        
        try {
            System.out.println("Conectando al servidor en " + host + " por el puerto " + puerto + "...");
            cliente = new Socket(host, puerto);
            System.out.println("¡Conexión establecida!\n");
            
            // Recibir el mensaje del servidor
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            String respuesta = entrada.readLine();
            
            System.out.println("Mensaje del servidor: " + respuesta);
            
        } catch (UnknownHostException e) {
            System.err.println("No se puede encontrar el host: " + host);
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        } finally {
            try {
                if (cliente != null && !cliente.isClosed()) {
                    cliente.close();
                    System.out.println("\nConexión cerrada.");
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el cliente: " + e.getMessage());
            }
        }
    }
}