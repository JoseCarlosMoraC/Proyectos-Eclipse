package Tema1.BoletinFicherosXMLDom;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class PeliculaDom {
	private String rutaResources = "src\\main\\resources\\";
	private static final Logger logger = LogManager.getLogger(PeliculaDom.class);
	
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
	private Pelicula getPeliculaFromElemento(Element elemento) {
		Pelicula p = new Pelicula();
		String titulo = elemento.getElementsByTagName("Titulo").item(0).getTextContent();
		int fecha = Integer.parseInt(elemento.getElementsByTagName("Fecha").item(0).getTextContent());
		String director = elemento.getElementsByTagName("Director").item(0).getTextContent();
		
		p.setTitulo(titulo);
		p.setFecha(fecha);
		p.setDirector(director);
		Node e = elemento.getElementsByTagName("Actores").item(0);
		if(e!= null && e.getNodeType() == Node.ELEMENT_NODE )
		{
			p.setActores(this.getActoresFromElemento((Element)e));
		}
		return p;
	}
	private List<String> getActoresFromElemento(Element elemento){
		List<String> actores = new ArrayList<>();
		NodeList actorNodo = elemento.getElementsByTagName("Actor");

		for (int j = 0; j < actorNodo.getLength(); j++) {
			Node modeloNodo = actorNodo.item(j);
			if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {
				
				 Element hijo = (Element) modeloNodo;
				 actores.add( hijo.getFirstChild().getNodeValue());
			}
		}
		return actores;
	}
	public List<Pelicula> leerPeliculaDesdeXML(String rutaFichero) throws Exception {
		List<Pelicula> peliculas = new ArrayList<Pelicula>();

		Document doc = getDocumentFromXML(rutaFichero);
		NodeList nodosPeliculas = doc.getElementsByTagName("Pelicula");

		for (int j = 0; j < nodosPeliculas.getLength(); j++) {
			Node modeloNodo = nodosPeliculas.item(j);
			if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {
				Pelicula p = this.getPeliculaFromElemento((Element) modeloNodo);
				peliculas.add(p);
			}
		}
		return peliculas;
	}

	}


	

