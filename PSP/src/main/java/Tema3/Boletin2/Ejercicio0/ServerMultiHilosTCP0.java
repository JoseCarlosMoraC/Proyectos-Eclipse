package Tema3.Boletin2.Ejercicio0;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Tema3.Clase.ManejadorHilosCliente;

public class ServerMultiHilosTCP0 {
	 public static void main(String[] args) {
		    int puerto = 5555;
	        try (ServerSocket servidor = new ServerSocket(puerto)) {
	            System.out.println("Servidor multihilo iniciado en el puerto " + puerto);
	            
	            while (true) {
	                // 1. Espera a un cliente
	                Socket socketCliente = servidor.accept();
	                System.out.println("Nuevo cliente conectado: " + socketCliente.getInetAddress());
	                
	                // 2. Lanza un hilo nuevo para este cliente específico
	                // Esto permite que el bucle vuelva al accept() inmediatamente
	                new ManejadorHilosCliente(socketCliente).start();
	            }
	        } catch (IOException e) {
	            System.err.println("Error en el servidor: " + e.getMessage());
	        }
	    }
	}