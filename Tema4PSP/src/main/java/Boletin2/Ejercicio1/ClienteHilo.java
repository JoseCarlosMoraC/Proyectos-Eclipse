package Boletin2.Ejercicio1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteHilo extends Thread{

    
    @Override
    public void run() {
    	int puerto = 4444;
        String host = "localhost";
		PrintWriter salida=null;
		BufferedReader entrada= null;
		Scanner sc= new Scanner (System.in);

        try {
            Socket socket = new Socket(host, puerto);
            salida = new PrintWriter(socket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Conectado al servidor " + socket.getInetAddress());
            
            salida.println("Hola desde el hilo " + this.getId());
            System.out.println("Respuesta recibida ECO:" + entrada.readLine());
            
        } catch (Exception e) {
            System.err.println("Conexión finalizada.");
        }
    }
}