package Boletin2.Ejercicio0;

import java.net.Socket;

public class Cliente0 {
public static void main(String[] args) {
	int puerto = 5555;
	String host = "localhost";
	Socket cliente;
	
	try {
		Socket socket = new Socket(host, puerto);
		System.out.println("Conectado al servidor");
		
		socket.getInputStream().read();
		socket.close();
	}catch(Exception e) {
		System.err.println("Conexion finalizada");
	}
}
}
