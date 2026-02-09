package Boletin2.Ejercicio4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        int puerto = 4444;
        String host = "localhost";
        PrintWriter salida= null;
        BufferedReader entrada=null;
        Scanner sc= new Scanner(System.in);
        
        try {
            Socket socket = new Socket(host, puerto);
            salida = new PrintWriter(socket.getOutputStream(), true);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //mensaje conectado con el id
            String mensaje= entrada.readLine();
            
            System.out.println("Servidor dice " + mensaje);
            //mensaje de que posicion quiere jugar el cliente
            boolean seguir=true;
            while(seguir) {
            String segundomensaje=entrada.readLine();
            
            System.out.println("Servidor dice " + segundomensaje);
            //Le dice la posicion que quiere jugar
            String mensajeparaservidor= sc.nextLine();
            if(mensajeparaservidor.equals("salir")){
            	seguir=false;
        		socket.close();

            }
            else {
         
            salida.println(mensajeparaservidor);
            //El servidor le envia el resultado de la apuesta
            System.out.println("Servidor: Resultado apuesta" + entrada.readLine());
            }
            socket.getInputStream().read(); 
            }
        } catch (Exception e) {
            System.err.println("Conexión finalizada.");
        }
    }
}