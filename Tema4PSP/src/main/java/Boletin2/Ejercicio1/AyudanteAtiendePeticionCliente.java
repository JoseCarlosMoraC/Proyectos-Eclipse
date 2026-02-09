package Boletin2.Ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalTime;

public class AyudanteAtiendePeticionCliente extends Thread {
	private Socket socket;
	

public AyudanteAtiendePeticionCliente(Socket socket) {
		super();
		this.socket = socket;
	}


@Override
public void run() {

	
		PrintWriter salida=null;
		BufferedReader entrada=null;
		String mensaje;
		
		try {
			entrada= new BufferedReader( new InputStreamReader(socket.getInputStream()));

			salida = new PrintWriter(socket.getOutputStream(), true);
			
			while ((mensaje = entrada.readLine()) != null) {
				System.out.println("Ayudante: El cliente dice " + mensaje);
                String respuesta =  mensaje + " Hilo ID: " + this.getId() + " a las " + LocalTime.now() ;
                salida.println(respuesta);
            }
			
		} 
		catch (java.net.SocketException e) {
	        // Capturamos el "Connection reset" para que no ensucie la consola
	        System.out.println("El cliente (Hilo " + this.getId() + ") cerró la conexión .");
	        }
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}
}