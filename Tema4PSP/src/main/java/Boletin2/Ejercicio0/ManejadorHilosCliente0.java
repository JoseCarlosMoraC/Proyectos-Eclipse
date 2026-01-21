package Boletin2.Ejercicio0;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ManejadorHilosCliente0 extends Thread {
    private Socket socket;

    
    public ManejadorHilosCliente0(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader entrada = null;
        PrintWriter salida = null;

        try {
            
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new PrintWriter(socket.getOutputStream(), true);
            

            salida.println("Conexión establecida con: ");
            
            String mensaje;
            Thread.sleep(5000);
            System.out.println("Conexión finalizada");
           
            
        
           
        } catch (IOException | InterruptedException e) {
            System.err.println("Error en ManejadorHilosCliente: " + e.getMessage());
            e.printStackTrace();
        } 
        }
    }
