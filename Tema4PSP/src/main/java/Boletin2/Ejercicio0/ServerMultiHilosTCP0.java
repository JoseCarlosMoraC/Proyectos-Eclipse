package Boletin2.Ejercicio0;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;



public class ServerMultiHilosTCP0 {
	 public static void main(String[] args) {
		    int puerto = 5555;
	        try (ServerSocket servidor = new ServerSocket(puerto)) {
	            System.out.println("Servidor multihilo iniciado en el puerto " + puerto);
	            
	            while (true) {
	  
	                Socket socketCliente = servidor.accept();
	                System.out.println("Nuevo cliente conectado: " + socketCliente.getInetAddress());
	            
	                new ManejadorHilosCliente0(socketCliente).start();
	            }
	        } catch (IOException e) {
	            System.err.println("Error en el servidor: " + e.getMessage());
	        }
	    }
	}