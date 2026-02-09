package Mapas;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteHilo extends Thread {

    @Override
    public void run() {

        int puerto = 4444;
        String host = "localhost";

        PrintWriter salida = null;
        BufferedReader entrada = null;

        // Para leer lo que escribe el usuario por teclado
        Scanner sc = new Scanner(System.in);

        try {
            // Se conecta al servidor
            Socket socket = new Socket(host, puerto);

            // Flujo de salida hacia el servidor
            salida = new PrintWriter(socket.getOutputStream(), true);

            // Flujo de entrada desde el servidor
            entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            // Lee el mensaje del servidor (número de cliente)
            String mensaje = entrada.readLine();
            System.out.println("Servidor dice: " + mensaje);

            // Lee el segundo mensaje (asientos disponibles)
            String segundomensaje = entrada.readLine();
            System.out.println("Servidor dice: " + segundomensaje);

            // El usuario escribe el asiento y su nombre
            String asientoparaservidor = sc.nextLine();
            salida.println(asientoparaservidor);

            // Recibe el resultado de la reserva
            String resultadoreserva = entrada.readLine();
            System.out.println(resultadoreserva);

            // Cierra la conexión
            socket.close();

        } catch (Exception e) {
            System.err.println("Conexión finalizada.");
        }
    }
}
