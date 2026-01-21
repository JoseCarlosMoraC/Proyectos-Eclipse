package Boletin1.Ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorEjercicio2 {

    public static void main(String[] args) {

        int puerto = 6000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {

            System.out.println("Servidor escuchando en el puerto " + puerto);

            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado desde " + cliente.getInetAddress());

            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

            int numero = entrada.readInt(); // recibe el número
            System.out.println("Servidor recibe: " + numero);

            int cuadrado = numero * numero;

            salida.writeInt(cuadrado); // envía el resultado
            System.out.println("Servidor envía el cuadrado: " + cuadrado);

            cliente.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
