package Tema1.XML;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Tema1.Ejemplos.Empleado;

public class XMLDom {
	private String rutaResources = "src\\main\\resources\\";
	private static final Logger logger = LogManager.getLogger(XMLDom.class);

	private Document getDocumentFromXML(String nombrefichero) {
		//Este es el siempre mismo
		File file = new File(rutaResources + nombrefichero);
		Document documento = null;
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			documento = dBuilder.parse(file);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return documento;
	}

	private Empleado getEmpleadoFromElement(Element elemento) {
		Empleado e = new Empleado();
		String nombre = elemento.getElementsByTagName("nombreApellido").item(0).getTextContent();
		int edad = Integer.parseInt(elemento.getElementsByTagName("edad").item(0).getTextContent());
		String empresa = elemento.getElementsByTagName("empresa").item(0).getTextContent();
		String id = elemento.getAttribute("identificador");

		e.setEdad(edad);
		e.setNombreApellido(nombre);
		e.setId(id);
		e.setEmpresa(empresa);
		return e;
	}

	public Empleado leerEmpleadoDesdeXML(String rutaFichero) throws Exception {
		Document doc = getDocumentFromXML(rutaFichero);
		Element elementoEmpleado = doc.getDocumentElement();
		return getEmpleadoFromElement(elementoEmpleado);
	}
}
