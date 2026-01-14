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

import Models.Artista;
import Models.Concierto;
import Models.GeneroMusical;

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

    private Artista getArtistaFromElement(Element elemento) {
        Artista a = new Artista();

        String codigo = elemento.getAttribute("codigo").trim();
        String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent().trim();
        String email = elemento.getElementsByTagName("email").item(0).getTextContent().trim();
        int numIntegrantes = Integer.parseInt(elemento.getElementsByTagName("numIntegrantes").item(0).getTextContent().trim());

        a.setCodigo(codigo);
        a.setNombreArtistico(nombre);
        a.setEmailManager(email);
        a.setNumIntegrantes(numIntegrantes);

        return a;
    }

    private Concierto getConciertoFromElement(Element elemento) {
        Concierto c = new Concierto();

        int id = Integer.parseInt(elemento.getAttribute("id").trim());
        String fecha = elemento.getElementsByTagName("fecha").item(0).getTextContent().trim();
        String descripcion = elemento.getElementsByTagName("descripcion").item(0).getTextContent().trim();
        String genero = elemento.getElementsByTagName("genero").item(0).getTextContent().trim();
        GeneroMusical tipoGenero = GeneroMusical.valueOf(genero);
        
        c.setId(id);
        c.setFecha(fecha);
        c.setDescripcion(descripcion);
        c.setGenero(tipoGenero);

        // Buscar artista cabecera
        NodeList cabecera = elemento.getElementsByTagName("cabecera");
        if (cabecera.getLength() > 0) {
            Element eCabecera = (Element) cabecera.item(0);
            String codigoCabecera = eCabecera.getAttribute("codigoRef").trim();
            
            // Crear un artista temporal con el código
            Artista artistaCabecera = new Artista();
            artistaCabecera.setCodigo(codigoCabecera);
            c.setArtistaCabecera(artistaCabecera);
        }

        return c;
    }

    public List<Artista> leerArtistaDesdeXML(String rutaFichero) throws Exception {
        List<Artista> artistas = new ArrayList<Artista>();

        Document doc = getDocumentFromXML(rutaFichero);

        NodeList nodosArtistas = doc.getElementsByTagName("artista");

        for (int j = 0; j < nodosArtistas.getLength(); j++) {
            Node modeloNodo = nodosArtistas.item(j);

            if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {
                Artista a = this.getArtistaFromElement((Element) modeloNodo);
                artistas.add(a);
            }
        }

        return artistas;
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