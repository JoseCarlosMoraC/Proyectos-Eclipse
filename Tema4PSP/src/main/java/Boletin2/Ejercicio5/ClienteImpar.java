package Boletin2.Ejercicio5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClienteImpar extends Thread{

	@Override
	public void run() {
		String Host = "localhost"; 
		int Puerto = 6666;
		Socket cliente = null;
		String fichero = "src\\main\\resources\\impar.txt";
		
		try {
			cliente = new Socket(Host, Puerto);			
			BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(), true);

            // voy a poner para que lea el fichero aunque me he generado uno para probar
            
            BufferedReader leerFichero = new BufferedReader(new FileReader(fichero));
            String mensaje = entrada.readLine();
			System.out.println("Cliente Par: conexión establecida");
			
			// leer numero del fichero y ver si acierta

			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}