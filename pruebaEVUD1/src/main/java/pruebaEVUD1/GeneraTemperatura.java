package pruebaEVUD1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class GeneraTemperatura {
	private static final String fichero = "src/main/resources/lecturas.txt";
	   public static void main(String[] args) throws FileNotFoundException {
	        if (args.length < 1) {
	            System.out.println("Se debe pasar el fichero como argumento");
	            return;
	        }
	        
	        String lectura = args[0];
	       
	        GeneraTemperatura g = new GeneraTemperatura();
	        List<String> lineas = g.separaTemperatura("TEMPERATURA", fichero);
	        g.escribeFichero("Temperatura.txt", lineas);
	        System.out.println(lineas.size());
}
	   public List<String> separaTemperatura(String temp, String ruta) throws FileNotFoundException {
	        List<String> listaTemperatura = new ArrayList<>();
	
	        
	        Scanner in = null;

	        try {
	            FileReader f = new FileReader(ruta);
	            in = new Scanner(f);

	            while (in.hasNextLine()) {
	                String linea = in.nextLine();
	                

	             
	                if (linea.startsWith("TEMPERATURA")) {
	                	listaTemperatura.add(linea);
	                }
	         
	        }
	        }finally {
	          
	            if (in != null) in.close();
	        }

	        
	        return listaTemperatura;
	        
	    }

	   public void escribeFichero(String ruta, List<String> resul) {
	        PrintWriter wr = null;
	        try {
	            FileWriter ficheroResultado = new FileWriter(ruta);
	            wr = new PrintWriter(ficheroResultado);

	         
	            for (String linea : resul) {
	                wr.println(linea);
	            }

	        } catch (IOException e) {
	            System.out.println("Error, no se ha creado fichero");

	        } finally {
	          
	            if (wr != null) {
	                wr.close();
	            }
	        }
	    }
	}
