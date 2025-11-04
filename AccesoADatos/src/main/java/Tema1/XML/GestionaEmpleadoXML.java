package Tema1.XML;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Tema1.Ejemplos.Empleado;

public class GestionaEmpleadoXML {
	private static final Logger logger = LogManager.getLogger(GestionaEmpleadoXML.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		XMLDom xlmEmpleado = new XMLDom();
		try {
			Empleado e = xlmEmpleado.leerEmpleadoDesdeXML("Empleado.xml");
			logger.info(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
