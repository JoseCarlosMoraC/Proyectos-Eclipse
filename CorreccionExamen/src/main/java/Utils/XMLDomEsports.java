package Utils;

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

import Models.Enfrentamiento;
import Models.Equipo;
import Models.Videojuego;


public class XMLDomEsports {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(XMLDomEsports.class);

    private static final String rutaFichero = "src/main/java/Utils/";



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

    private Equipo getEquipoFromElement(Element elemento) {
        Equipo e = new Equipo();

        String codigo = elemento.getAttribute("codigo").trim();
        String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent().trim();
        String email = elemento.getElementsByTagName("email").item(0).getTextContent().trim();
        int numJugadores = Integer.parseInt(elemento.getElementsByTagName("numJugadores").item(0).getTextContent().trim());

        e.setCodigo(codigo);
        e.setNombreCompleto(nombre);
        e.setEmailContacto(email);
        e.setNumJugadores(numJugadores);

        return e;
    }

    private Enfrentamiento getEnfrentamientoFromElement(Element elemento) {
        Enfrentamiento en = new Enfrentamiento();

        int id = Integer.parseInt(elemento.getAttribute("id").trim());
        String fecha = elemento.getElementsByTagName("fecha").item(0).getTextContent().trim();
        String descripcion = elemento.getElementsByTagName("descripcion").item(0).getTextContent().trim();
        String videojuego = elemento.getElementsByTagName("videojuego").item(0).getTextContent().trim();
        Videojuego tipoVideojuego = Videojuego.valueOf(videojuego);
        
        en.setId(id);
        en.setFecha(fecha);
        en.setDescripcion(descripcion);
        en.setVideojuego(tipoVideojuego);

        // Corregido: buscar "ganador" en lugar de "equipoGanador"
        NodeList ganador = elemento.getElementsByTagName("ganador");
        if (ganador.getLength() > 0) {
            Element eGanador = (Element) ganador.item(0);
            String codigoGanador = eGanador.getAttribute("codigoRef").trim();
            
            // Crear un equipo temporal con el código
            // En un caso real, buscarías el equipo completo desde la lista
            Equipo equipoGanador = new Equipo();
            equipoGanador.setCodigo(codigoGanador);
            en.setEquipoGanador(equipoGanador);
        }

        return en;
    }

    public List<Equipo> leerEquipoDesdeXML(String rutaFichero) throws Exception {
        List<Equipo> equipos = new ArrayList<Equipo>();

        Document doc = getDocumentFromXML(rutaFichero);

        NodeList nodoscentros = doc.getElementsByTagName("equipo");

        for (int j = 0; j < nodoscentros.getLength(); j++) {
            Node modeloNodo = nodoscentros.item(j);

            if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {
                Equipo e = this.getEquipoFromElement((Element) modeloNodo);
                equipos.add(e);
            }
        }

        return equipos;
    }

    public List<Enfrentamiento> leerEnfrentamientoDesdeXML(String rutaFichero) throws Exception {
        List<Enfrentamiento> enfrentamientos = new ArrayList<Enfrentamiento>();
        Document doc = getDocumentFromXML(rutaFichero);

        NodeList nodosproductos = doc.getElementsByTagName("enfrentamiento");

        for (int j = 0; j < nodosproductos.getLength(); j++) {
            Node modeloNodo = nodosproductos.item(j);

            if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {
                Enfrentamiento en = this.getEnfrentamientoFromElement((Element) modeloNodo);
                enfrentamientos.add(en);
            }
        }

        return enfrentamientos;
    }
}