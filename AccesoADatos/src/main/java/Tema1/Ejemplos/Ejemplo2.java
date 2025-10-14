package Tema1.Ejemplos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Tema1.Boletin1.Ejercicio1;

public class Ejemplo2 {
	private final static String rutaSource = ("C:\\Users\\alumno\\Desktop\\DAM\\Proyectos Eclipse\\AccesoADatos\\src\\test\\resources\\Prueba.txt");
	private static final Logger logger = LogManager.getLogger(Ejemplo2.class);
	void muestraContenidoFich(String rutaYNombre) throws FileNotFoundException {
		Scanner in = null;
		try {
			// abre el fichero
			FileReader fichero = new FileReader(rutaYNombre);
			//Se crea el flujo
			in = new Scanner(fichero);
			// lee el fichero
			while (in.hasNext()) { //Lectura palabra a palabra
				logger.info(in.next());
				
				// Aquí se hará la lectura in.next()
			}
		} finally {
			if (in != null) {
				in.close();
			}
		}
	}

}
