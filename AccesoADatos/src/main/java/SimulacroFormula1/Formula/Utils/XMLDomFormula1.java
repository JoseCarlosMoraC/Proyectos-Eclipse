	package SimulacroFormula1.Formula.Utils;
	
	import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import SimulacroFormula1.Formula.Models.Equipo;
import SimulacroFormula1.Formula.Models.Piloto;
	

	public class XMLDomFormula1 {
	    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(XMLDomFormula1.class);
	
	    // Ajusta la ruta al fichero según dónde pongas formula1.xml
	    private static final String rutaFichero = "src\\main\\java\\Simulacro\\Formula1\\Utils\\";
	
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
	
	    private Piloto getPilotoFromElement(Element elemento) {
	        Piloto p = new Piloto();
	
	        String idPiloto = elemento.getAttribute("identificadorPiloto").trim();
	        String nombre = elemento.getElementsByTagName("nombrePiloto").item(0).getTextContent().trim();
	        int puntos = Integer.parseInt(elemento.getElementsByTagName("puntos").item(0).getTextContent().trim());
	        String idEquipo = elemento.getElementsByTagName("identificadorEquipo").item(0).getTextContent().trim();
	        String pais = elemento.getElementsByTagName("pais").item(0).getTextContent().trim();
	
	        p.setIdentificadorPiloto(idPiloto);
	        p.setNombrePiloto(nombre);
	        p.setPuntos(puntos);
	        p.setIdentificadorEquipo(idEquipo);
	        p.setPais(pais);
	
	        return p;
	    }
	
	    private Equipo getEquipoFromElement(Element elemento) {
	        Equipo e = new Equipo();
	
	        String idEquipo = elemento.getAttribute("identificadorEquipo").trim();
	        String nombreEquipo = elemento.getElementsByTagName("nombreEquipo").item(0).getTextContent().trim();
	        int puntos = Integer.parseInt(elemento.getElementsByTagName("puntos").item(0).getTextContent().trim());
	
	        e.setIdentificadorEquipo(idEquipo);
	        e.setNombreEquipo(nombreEquipo);
	        e.setPuntos(puntos);
	        // pilotos queda vacío inicialmente
	
	        return e;
	    }
	
	    public List<Piloto> leerPilotosDesdeXML(String nombreFichero) throws Exception {
	        List<Piloto> pilotos = new ArrayList<Piloto>();
	
	        Document doc = getDocumentFromXML(nombreFichero);
	
	        NodeList nodosPilotos = doc.getElementsByTagName("piloto");
	
	        for (int i = 0; i < nodosPilotos.getLength(); i++) {
	            Node nodo = nodosPilotos.item(i);
	            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
	                Piloto p = getPilotoFromElement((Element) nodo);
	                pilotos.add(p);
	            }
	        }
	
	        return pilotos;
	    }
	
	    public List<Equipo> leerEquiposDesdeXML(String nombreFichero) throws Exception {
	        List<Equipo> equipos = new ArrayList<Equipo>();
	
	        Document doc = getDocumentFromXML(nombreFichero);
	
	        NodeList nodosEquipos = doc.getElementsByTagName("equipo");
	
	        for (int i = 0; i < nodosEquipos.getLength(); i++) {
	            Node nodo = nodosEquipos.item(i);
	            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
	                Equipo e = getEquipoFromElement((Element) nodo);
	                equipos.add(e);
	            }
	        }
	
	        return equipos;
	    }
	}
