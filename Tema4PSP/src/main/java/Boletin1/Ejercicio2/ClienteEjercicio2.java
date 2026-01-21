package Boletin1.Ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteEjercicio2 {

    public static void main(String[] args) {

        String host = "localhost";
        int puerto = 6000;

        try (Socket cliente = new Socket(host, puerto)) {

            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());

            Scanner sc = new Scanner(System.in);
            System.out.print("Introduce un número entero: ");
            int numero = sc.nextInt();

            salida.writeInt(numero); // envía el número al servidor

            int resultado = entrada.readInt(); // recibe el cuadrado
            System.out.println("El cuadrado es: " + resultado);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
