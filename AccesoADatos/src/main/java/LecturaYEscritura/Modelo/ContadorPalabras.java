package LecturaYEscritura.Modelo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Tema1.Boletin2.Controlador;

public class ContadorPalabras {
private static final Logger logger = LogManager.getLogger(ContadorPalabras.class);
public int cuentaPalabraEnFichero(String ruta, String palabra) throws FileNotFoundException{
	FileReader f = new FileReader(ruta);
	Scanner in = new Scanner(f);
	int contador = 0;
	while(in.hasNextLine()){
		String linea = in.nextLine();
		String[] partes = linea.split(" ");
		for (String p : partes) {
			if(p.equalsIgnoreCase(palabra)) {
				contador++;
			}
		}
	}
	return contador ++;
}
}