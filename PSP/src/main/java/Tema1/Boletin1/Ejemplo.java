package Tema1.Boletin1;

import java.io.IOException;

public class Ejemplo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runtime kernel = Runtime.getRuntime();
		
		String[] argumentos = {"notepad", "adios.txt"};
		
		try {
			kernel.exec(argumentos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
