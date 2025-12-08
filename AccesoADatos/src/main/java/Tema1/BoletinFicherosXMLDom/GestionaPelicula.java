package Tema1.BoletinFicherosXMLDom;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Tema1.Ejemplos.Empleado;
import Tema1.XML.GestionaEmpleadoXML;
import Tema1.XML.XMLDom;

public class GestionaPelicula {
private static final Logger logger = LogManager.getLogger(GestionaPelicula.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PeliculaDom xlmPelicula = new PeliculaDom();
		try {
			List<Pelicula> p = xlmPelicula.leerPeliculaDesdeXML("peliculas.xml");
			logger.info(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
