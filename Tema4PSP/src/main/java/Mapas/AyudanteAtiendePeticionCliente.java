package Mapas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class AyudanteAtiendePeticionCliente extends Thread {

    // Socket del cliente
    private Socket socket;

    // Contador compartido entre todos los hilos
    private Contador contador;

    // Mapa compartido de asientos
    private MapaFlyThread mapaasientos;

    public AyudanteAtiendePeticionCliente(
            Socket socket,
            Contador contador,
            MapaFlyThread mapaasientos
    ) {
        this.socket = socket;
        this.contador = contador;
        this.mapaasientos = mapaasientos;
    }

    @Override
    public void run() {

        PrintWriter salida = null;
        BufferedReader entrada = null;

        // Incrementa el número de clientes conectados
        contador.incrementar();

        try {
            entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            salida = new PrintWriter(socket.getOutputStream(), true);

            // Envía el número de cliente
            salida.println("Eres el cliente " + contador.getNumeroconexiones());

            // Envía los asientos ya reservados
            salida.println(
                    "Dime tu vuelo (Numero de asiento y nombre). " +
                    "Los asientos reservados son: " +
                    mapaasientos.getReservaasientos().keySet()
            );

            // Lee la petición del cliente
            String asientocliente = entrada.readLine();

            // Intenta reservar el asiento
            String resultadoreserva =
                    mapaasientos.reservarasiento(asientocliente);

            // Devuelve el resultado
            salida.println(resultadoreserva);

        } catch (java.net.SocketException e) {
            System.out.println(
                    "El cliente " + contador.getNumeroconexiones() +
                    " cerró la conexión."
            );
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
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
