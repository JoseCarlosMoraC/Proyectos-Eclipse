package Tema1.Boletin1;

import java.io.IOException;

public class Boletin1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Ejercicio 1
		Runtime kernel = Runtime.getRuntime();
		
		String[] chrome = {"C:\\\\Program Files\\\\Google\\\\Chrome\\\\Application\\\\chrome.exe"};
		
		try {
			kernel.exec(chrome);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Ejercicio 2
        ProcessBuilder chrome2 = new ProcessBuilder("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");

        try {
            Process Process = chrome2.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Ejercicio 3
        String[] comando = {"cmd.exe", "/C", "Start", "cmd.exe", "/K", "tasklist"};
        ProcessBuilder pb = new ProcessBuilder(comando);

        try {
            Process p = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	// Ejercicio 4
	
}