package Tema1.Boletin1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

	public class LanzadorPython {

	    public static void ejecutarScript(String rutaScript) {
	        try {
	            ProcessBuilder pb = new ProcessBuilder("python", rutaScript);
	            pb.redirectErrorStream(true);
	            Process proceso = pb.start();

	            BufferedReader reader = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
	            String linea;
	            while ((linea = reader.readLine()) != null) {
	                System.out.println(linea);
	            }

	            int exitCode = proceso.waitFor();
	            System.out.println("Proceso terminado con código: " + exitCode);
	        } catch (IOException | InterruptedException e) {
	            e.printStackTrace();
	        }
	    }
	}
