package Utils;

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

import Models.Banda;
import Models.Concierto;
import Models.GeneroMusical;



public class XMLDomLiveFest {

    private static final Logger logger = LogManager.getLogger(XMLDomLiveFest.class);
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

    private Banda getBandaFromElement(Element elemento) {
        Banda b = new Banda();

        String codigo = elemento.getAttribute("codigo").trim();
        String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent().trim();
        String paisOrigen = elemento.getElementsByTagName("paisOrigen").item(0).getTextContent().trim();
        int numIntegrantes = Integer.parseInt(elemento.getElementsByTagName("numIntegrantes").item(0).getTextContent().trim());
        String email = elemento.getElementsByTagName("email").item(0).getTextContent().trim();

        b.setCodigo(codigo);
        b.setNombre(nombre);
        b.setPaisOrigen(paisOrigen);
        b.setNumIntegrantes(numIntegrantes);
        b.setEmailContacto(email);

        return b;
    }

    private Concierto getConciertoFromElement(Element elemento) {
        Concierto c = new Concierto();

        int id = Integer.parseInt(elemento.getAttribute("id").trim());
        String fecha = elemento.getElementsByTagName("fecha").item(0).getTextContent().trim();
        String escenario = elemento.getElementsByTagName("escenario").item(0).getTextContent().trim();
        String genero = elemento.getElementsByTagName("genero").item(0).getTextContent().trim();
        GeneroMusical generoMusical = GeneroMusical.valueOf(genero);

        c.setId(id);
        c.setFecha(fecha);
        c.setEscenario(escenario);
        c.setGeneroMusical(generoMusical);

        // Buscar headliner
        NodeList headliner = elemento.getElementsByTagName("headliner");
        if (headliner.getLength() > 0) {
            Element eHeadliner = (Element) headliner.item(0);
            String codigoHeadliner = eHeadliner.getAttribute("codigoRef").trim();
            
            // Crear banda temporal con el código
            Banda bandaHeadliner = new Banda();
            bandaHeadliner.setCodigo(codigoHeadliner);
            c.setBandaHeadliner(bandaHeadliner);
        }

        return c;
    }

    public List<Banda> leerBandasDesdeXML(String rutaFichero) throws Exception {
        List<Banda> bandas = new ArrayList<>();

        Document doc = getDocumentFromXML(rutaFichero);

        NodeList nodosBandas = doc.getElementsByTagName("banda");

        for (int j = 0; j < nodosBandas.getLength(); j++) {
            Node modeloNodo = nodosBandas.item(j);

            if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {
                Banda b = this.getBandaFromElement((Element) modeloNodo);
                bandas.add(b);
            }
        }

        return bandas;
    }

    public List<Concierto> leerConciertosDesdeXML(String rutaFichero) throws Exception {
        List<Concierto> conciertos = new ArrayList<>();
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