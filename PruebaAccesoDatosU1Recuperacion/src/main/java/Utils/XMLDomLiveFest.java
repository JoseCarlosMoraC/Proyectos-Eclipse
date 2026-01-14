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

import Models.Grupo;
import Models.Concierto;
import Models.Escenario;

public class XMLDomLiveFest {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(XMLDomLiveFest.class);

    private static final String rutaFichero = "src\\main\\java\\Utils\\";

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

    private Grupo getGrupoFromElement(Element elemento) {
        Grupo a = new Grupo();

        String codigo = elemento.getElementsByTagName("codigo").item(0).getTextContent().trim();
        String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent().trim();
        int numIntegrantes = Integer.parseInt(elemento.getElementsByTagName("numIntegrantes").item(0).getTextContent().trim());
        String email = elemento.getElementsByTagName("email").item(0).getTextContent().trim();
       

        a.setCodigo(codigo);
        a.setNombre(nombre);
        a.setNumIntegrantes(numIntegrantes);
        a.setEmail(email);

        return a;
    }

    private Concierto getConciertoFromElement(Element elemento) {
        Concierto c = new Concierto();

        int id = Integer.parseInt(elemento.getElementsByTagName("id").item(0).getTextContent().trim());
        String fecha = elemento.getElementsByTagName("fecha").item(0).getTextContent().trim();
        String descripcion = elemento.getElementsByTagName("descripcion").item(0).getTextContent().trim();
        String escenario = elemento.getElementsByTagName("escenario").item(0).getTextContent().trim();
        Escenario tipoEscenario = Escenario.valueOf(escenario);
        
        c.setId(id);
        c.setFecha(fecha);
        c.setDescripcion(descripcion);
        

    
        NodeList grupoNodo = elemento.getElementsByTagName("grupo");
        for (int i = 0; i < grupoNodo.getLength(); i++) {
            Node nodo = grupoNodo.item(i);

            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element eGrupo = (Element) nodo;
                Grupo g = getGrupoFromElement(eGrupo);
                c.getGrupo().add(g);
            }
        }
        c.setEscenario(tipoEscenario);
        return c;
    }
    public List<Grupo> leerGrupoDesdeXML(String rutaFichero) throws Exception {
        List<Grupo> grupos = new ArrayList<Grupo>();

        Document doc = getDocumentFromXML(rutaFichero);

        NodeList nodosGrupos = doc.getElementsByTagName("grupo");

        for (int j = 0; j < nodosGrupos.getLength(); j++) {
            Node modeloNodo = nodosGrupos.item(j);

            if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {
                Grupo a = this.getGrupoFromElement((Element) modeloNodo);
                grupos.add(a);
            }
        }

        return grupos;
    }

    public List<Concierto> leerConciertoDesdeXML(String rutaFichero) throws Exception {
        List<Concierto> conciertos = new ArrayList<Concierto>();
        Document doc = getDocumentFromXML(rutaFichero);

        NodeList nodosConciertos = doc.getElementsByTagName("concierto");

        for (int j = 0; j < nodosConciertos.getLength(); j++) {
            Node modeloNodo = nodosConciertos.item(j);

            if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {
                Concierto c = this.getConciertoFromElement((Element) modeloNodo);
                conciertos.add(c);
            }
        }

        return conciertos;
    }
}