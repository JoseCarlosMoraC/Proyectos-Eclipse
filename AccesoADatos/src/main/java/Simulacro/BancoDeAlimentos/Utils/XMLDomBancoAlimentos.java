package Simulacro.BancoDeAlimentos.Utils;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import Simulacro.BancoDeAlimentos.Models.CentroLogistico;
import Simulacro.BancoDeAlimentos.Models.Tipo;
import Simulacro.BancoDeAlimentos.Models.Trabajador;


public class XMLDomBancoAlimentos {
	private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(XMLDomBancoAlimentos.class);

	private static final String rutaFichero = "src\\main\\java\\Simulacro\\BancoDeAlimentos\\Utils\\";

	private Document getDocumentFromXML(String nombrefichero) {

		File file = new File(rutaFichero + nombrefichero);

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
	
	private CentroLogistico getCentroFromElement(Element elemento) {

	    CentroLogistico c = new CentroLogistico(); // el Set de trabajadores ya debe estar inicializado en el constructor

	    // Leer datos del centro
	    String id = elemento.getElementsByTagName("ID").item(0).getTextContent().trim();
	    String nombre = elemento.getElementsByTagName("Nombre").item(0).getTextContent().trim();
	    String ciudad = elemento.getElementsByTagName("Ciudad").item(0).getTextContent().trim();
	    int numComedores = Integer.parseInt(elemento.getElementsByTagName("ComedoresAbastecidos").item(0).getTextContent().trim());

	    c.setId(id);
	    c.setNombre(nombre);
	    c.setCiudad(ciudad);
	    c.setNumComedores(numComedores);

	    // Leer trabajadores
	    NodeList trabajadoresNodo = elemento.getElementsByTagName("Trabajador"); // cada nodo <Trabajador>
	    for (int i = 0; i < trabajadoresNodo.getLength(); i++) {
	        Node nodo = trabajadoresNodo.item(i);
	        if (nodo.getNodeType() == Node.ELEMENT_NODE) {
	            Element eTrabajador = (Element) nodo;
	            Trabajador t = getTrabajadorFromElement(eTrabajador);
	            t.setIdCentro(id);              
	            c.getTrabajadores().add(t);    
	        }
	    }

	    return c;
	}


	private Trabajador getTrabajadorFromElement(Element elemento) {
		Trabajador t = new Trabajador();
		
		String nombre = elemento.getElementsByTagName("Nombre").item(0).getTextContent().trim();
		
		String dni = elemento.getElementsByTagName("DNI").item(0).getTextContent().trim();
		
		LocalDate fecha = LocalDate.parse(elemento.getElementsByTagName("FechaNacimiento").item(0).getTextContent().trim());
		
		String tipo = elemento.getElementsByTagName("Tipo").item(0).getTextContent().trim();
		
		Tipo tipoTrabajador = Tipo.valueOf(tipo.toUpperCase()); 
		
		t.setNombre(nombre);
		t.setDni(dni);
		t.setFecha(fecha);
		t.setTipo(tipoTrabajador);
		
		return t;
	}
	
	public List<CentroLogistico> leerCentroLogisticoDesdeXML(String rutaFichero) throws Exception {

		List<CentroLogistico> centros = new ArrayList<CentroLogistico>();

		// 1. Calcula el dom

		Document doc = getDocumentFromXML(rutaFichero);

		// 2. Obtener todos los nodos con etiqueta empleados

		NodeList nodoscentros = doc.getElementsByTagName("CentroLogistico");

 // 3. Recorro la lista de los nodos empleado

		for (int j = 0; j < nodoscentros.getLength(); j++) {

			Node modeloNodo = nodoscentros.item(j);

			if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {

				CentroLogistico c = this.getCentroFromElement((Element) modeloNodo);

				centros.add(c);

			}

		}

		return centros;

	}	

	public List<Trabajador> leerTrabajadorDesdeXML(String rutaFichero) throws Exception {

		List<Trabajador> trabajadores = new ArrayList<Trabajador>();

		// 1. Calcula el dom

		Document doc = getDocumentFromXML(rutaFichero);

		// 2. Obtener todos los nodos con etiqueta empleados

		NodeList nodosproductos = doc.getElementsByTagName("Trabajador");

		// 3. Recorro la lista de los nodos empleado

		for (int j = 0; j < nodosproductos.getLength(); j++) {

			Node modeloNodo = nodosproductos.item(j);

			if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {

				Trabajador t = this.getTrabajadorFromElement((Element) modeloNodo);

				trabajadores.add(t);

			}

		}

		return trabajadores;

	}	
}
	

