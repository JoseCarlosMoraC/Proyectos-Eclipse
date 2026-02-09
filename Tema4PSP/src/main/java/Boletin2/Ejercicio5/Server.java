package Boletin2.Ejercicio5;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class Server {
public static void main(String[] args) {
		
		int puerto = 6666;
		NumeroAdivinar numero = new NumeroAdivinar();
				
	       try (ServerSocket servidor = new ServerSocket(puerto)) {
	           System.out.println("Servidor multihilo iniciado en el puerto " + puerto);
	           while (true) {
	               // 1. Espera a un cliente
	               Socket socketCliente = servidor.accept();
	               System.out.println("Nuevo cliente conectado: " + socketCliente.getInetAddress() +", en fecha: " +LocalDateTime.now());
	               // 2. Lanza un hilo nuevo para este cliente específico
	               // Esto permite que el bucle vuelva al accept() inmediatamente
	               new HiloServidor(socketCliente, numero).start();
	           }
	       } catch (IOException e) {
	           System.err.println("Error en el servidor: " + e.getMessage());
	       }
	}

}