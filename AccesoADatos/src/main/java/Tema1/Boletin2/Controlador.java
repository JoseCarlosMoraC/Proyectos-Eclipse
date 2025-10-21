package Tema1.Boletin2;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



public class Controlador {
	private static final Logger logger = LogManager.getLogger(Controlador.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			DiffFolder diff = new DiffFolder();
			diff.setFolders(
				new File(diff.getFolder1(), "carpeta1"),
				new File(diff.getFolder2(), "carpeta2")
			);

			for (ResultadoComparacion r : diff.compare()) {

				logger.info(r);
				}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Error ");
		}
		
	}

}


