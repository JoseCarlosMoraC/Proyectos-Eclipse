package Clase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente2 {
	public static void main(String[] args) {
		String host = "localhost"; // host servidor con el que el cliente quiere conectarse
		int puerto = 6000;// puerto remoto en el servidor que el cliente conoce
		Socket cliente = null;
		try {
			System.out.println("Conectando al servidor en " + host + "por el puerto " + puerto + "...");
			cliente = new Socket(host, puerto);
			System.out.println("¡Conexión establecida!");
			
			PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);
			BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("Escribe un mensaje para el servidor: ");
			String mensajeParaServidor = sc.nextLine();
			
			salida.println(mensajeParaServidor);
			
			
			String respuesta = entrada.readLine();
			System.out.println("Respuesta del servidor " + respuesta);
			
	}catch (UnknownHostException e) {
		System.err.println("No se puede encontrar el host: " + host);
	}catch (IOException e) {
		System.err.println("Error de entrada/salida: " + e.getMessage());
	}finally {
		try {
			if(cliente !=null) {
				cliente.close();
				System.out.println("Conexión cerrada");
			}
			
		}catch(IOException e) {
			System.err.println("Error al cerrar el cliente " + e.getMessage());
	
}
}
}
}