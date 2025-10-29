package Tema1.XML;

import Tema1.Ejemplos.Empleado;

public class GestionaEmpleadoXML {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		XMLDom xlmEmpleado = new XMLDom();
		try {
			Empleado e = xlmEmpleado.leerEmpleadoDesdeXML("Empleado.xml");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
