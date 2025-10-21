package LecturaYEscritura.Modelo;

import java.io.FileNotFoundException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GestionaContadorPalabras {
	private static final Logger logger = LogManager.getLogger(GestionaContadorPalabras.class);
	
	private static final String rutaResources = "src/main/resources/";
	public static void main(String[] args) {
		ContadorPalabras c = new ContadorPalabras();
		try {
			int variable = c.cuentaPalabraEnFichero(rutaResources + "fichero.txt", "es");
			logger.info(variable);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
