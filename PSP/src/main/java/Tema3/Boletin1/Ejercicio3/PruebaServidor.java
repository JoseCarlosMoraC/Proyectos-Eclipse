package Tema3.Boletin1.Ejercicio3;



import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.io.PrintWriter;

import java.net.ServerSocket;

import java.net.Socket;



public class PruebaServidor {

public static void main(String[] args) {

	int puerto=6000;

	int clientespermitidos= 3;

	ServerSocket servidor= null;

	int i=1;

	try {

		servidor= new ServerSocket(puerto);
		System.out.println("Servidor iniciado en el puerto " + puerto);
        System.out.println("Esperando hasta " + clientespermitidos + " clientes...\n");


		while(i<=clientespermitidos) {

			System.out.println("Esperando cliente número " + i + "...");



		Socket cliente= servidor.accept();
		   System.out.println("Cliente " + i + " conectado desde: " + cliente.getInetAddress());

		PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

		salida.println("Eres el cliente número " + i);

		System.out.println("Mensaje enviado al cliente " + i);


		i++;
		cliente.close();
        System.out.println("Cliente " + i + " desconectado.\n");

		

	

		}
		System.out.println("Todos los clientes han sido atendidos.");
        
		

	} catch (IOException e) {

		  System.err.println("Error en el servidor: " + e.getMessage());
          e.printStackTrace();
	}finally {
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

