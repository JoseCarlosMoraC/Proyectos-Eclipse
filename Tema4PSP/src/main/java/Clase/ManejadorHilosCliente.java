package Clase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ManejadorHilosCliente extends Thread {
    private Socket socket;

    // Constructor que recibe el socket del cliente
    public ManejadorHilosCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader entrada = null;
        PrintWriter salida = null;

        try {
            // Crear flujos de entrada y salida
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new PrintWriter(socket.getOutputStream(), true);
            
            // Mensaje de bienvenida
            salida.println("Bienvenido. Escribe algo (o 'fin' para salir):");
            
            String mensaje;
            // Bucle principal: leer mensajes del cliente
            while ((mensaje = entrada.readLine()) != null && !mensaje.equalsIgnoreCase("fin")) {
                System.out.println("Cliente dice: " + mensaje);
                salida.println("Servidor responde: " + mensaje.toUpperCase());
            }
            
            // Mensaje de despedida si el cliente escribió "fin"
            if (mensaje != null && mensaje.equalsIgnoreCase("fin")) {
                salida.println("Cerrando sesión. ¡Hasta pronto!");
            }
            
        } catch (IOException e) {
            System.err.println("Error en ManejadorHilosCliente: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (entrada != null) entrada.close();
                if (salida != null) salida.close();
                if (socket != null) socket.close();
                System.out.println("Conexión cerrada con el cliente");
            } catch (IOException e) {
                System.err.println("Error al cerrar recursos: " + e.getMessage());
            }
        }
    }
}