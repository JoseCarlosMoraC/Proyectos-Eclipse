package Tema1.Boletin1;

import java.io.IOException;

public class Ejemplo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	/*
		Runtime rt = Runtime.getRuntime();
	 	
		String[] informacionProceso = {"Notepad.exe"};
		
		Process proceso;
		
		try {
			proceso = rt.exec(informacionProceso);
			int codigoRetorno = proceso.waitFor(); //Espero a que termine
			System.out.println("---Despues del wait:"+ codigoRetorno);//Espero a que termine
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/

		String [] comando ={"cmd.exe" , "/C", "Start", "cmd.exe", "/K", "tasklist"};
		ProcessBuilder pb = new ProcessBuilder(comando);
		
		try {
			Process p = pb.start();	
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	
	}
}
