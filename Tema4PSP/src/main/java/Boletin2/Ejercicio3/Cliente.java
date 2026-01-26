package Boletin2.Ejercicio3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {
	public static void main(String[] args) {
		int puerto = 44444;
		String host = "localhost";
		Socket cliente;
		PrintWriter salida = null;
		BufferedReader entrada = null;

		try {
			Socket socket = new Socket(host, puerto);
			System.out.println("Conectado al servidor");
			salida = new PrintWriter(socket.getOutputStream(), true);
			entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String mensaje = entrada.readLine();
			System.out.println(mensaje);
			socket.getInputStream().read();

		} catch (Exception e) {
			System.err.println("Conexion finalizada");
		}
	}
}
