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

// Esta clase se encarga de LEER el XML usando DOM
// y convertir los nodos en objetos CentroLogistico y Trabajador.
// Esto responde al Apartado 2 del enunciado.

public class XMLDomBancoAlimentos {

    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger(XMLDomBancoAlimentos.class);

    // Ruta donde se encuentra el XML
    // Se usa en getDocumentFromXML
    private static final String rutaFichero = "src\\main\\java\\Simulacro\\BancoDeAlimentos\\Utils\\";

    // Método que abre y carga el XML en memoria como Document
    private Document getDocumentFromXML(String nombrefichero) {

        File file = new File(rutaFichero + nombrefichero);
        Document documento = null;

        try {
            // Creamos el constructor DOM
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

            // Parseamos el XML -> almacenamos la estructura completa
            documento = dBuilder.parse(file);

        } catch (Exception e) {
            // Usamos Logger tal como exige el enunciado
            logger.error(e.getMessage());
        }

        return documento;
    }

    // Convierte un nodo <CentroLogistico> en un objeto CentroLogistico
    private CentroLogistico getCentroFromElement(Element elemento) {

        // Creamos el centro vacío (su set de trabajadores ya está inicializado en el constructor)
        CentroLogistico c = new CentroLogistico();

        // Extraemos la información del XML para guardarla en el objeto
        String id = elemento.getElementsByTagName("ID").item(0).getTextContent().trim();
        String nombre = elemento.getElementsByTagName("Nombre").item(0).getTextContent().trim();
        String ciudad = elemento.getElementsByTagName("Ciudad").item(0).getTextContent().trim();
        int numComedores = Integer.parseInt(elemento.getElementsByTagName("ComedoresAbastecidos").item(0).getTextContent().trim());

        c.setId(id);
        c.setNombre(nombre);
        c.setCiudad(ciudad);
        c.setNumComedores(numComedores);

        // Ahora obtenemos los trabajadores dentro del centro
        NodeList trabajadoresNodo = elemento.getElementsByTagName("Trabajador");
        for (int i = 0; i < trabajadoresNodo.getLength(); i++) {
            Node nodo = trabajadoresNodo.item(i);

            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                Element eTrabajador = (Element) nodo;
                Trabajador t = getTrabajadorFromElement(eTrabajador);

                // Asignamos el ID del centro al trabajador (relación establecida en el enunciado)
                t.setIdCentro(id);

                // Añadimos el trabajador al Set del centro
                c.getTrabajadores().add(t);
            }
        }

        return c;
    }

    // Convierte un nodo <Trabajador> en objeto Trabajador
    private Trabajador getTrabajadorFromElement(Element elemento) {

        Trabajador t = new Trabajador();

        // Extraemos los datos del trabajador
        String nombre = elemento.getElementsByTagName("Nombre").item(0).getTextContent().trim();
        String dni = elemento.getElementsByTagName("DNI").item(0).getTextContent().trim();
        LocalDate fecha = LocalDate.parse(elemento.getElementsByTagName("FechaNacimiento").item(0).getTextContent().trim());
        String tipo = elemento.getElementsByTagName("Tipo").item(0).getTextContent().trim();

        // Convertimos el texto a enum Tipo (ASALARIADO / VOLUNTARIO)
        Tipo tipoTrabajador = Tipo.valueOf(tipo.toUpperCase());

        // Guardamos los datos en el objeto
        t.setNombre(nombre);
        t.setDni(dni);
        t.setFecha(fecha);
        t.setTipo(tipoTrabajador);

        return t;
    }

    // Lee toda la lista de centros logísticos del XML
    public List<CentroLogistico> leerCentroLogisticoDesdeXML(String rutaFichero) throws Exception {

        List<CentroLogistico> centros = new ArrayList<CentroLogistico>();

        // Primero cargamos el DOM completo
        Document doc = getDocumentFromXML(rutaFichero);

        // Obtenemos los nodos <CentroLogistico>
        NodeList nodoscentros = doc.getElementsByTagName("CentroLogistico");

        // Recorremos cada nodo y convertimos a objeto
        for (int j = 0; j < nodoscentros.getLength(); j++) {
            Node modeloNodo = nodoscentros.item(j);

            if (modeloNodo.getNodeType() == Node.ELEMENT_NODE) {
                CentroLogistico c = this.getCentroFromElement((Element) modeloNodo);
                centros.add(c);
            }
        }

        return centros;
    }

    // Lee todos los trabajadores del XML (independientes del centro)
    public List<Trabajador> leerTrabajadorDesdeXML(String rutaFichero) throws Exception {

        List<Trabajador> trabajadores = new ArrayList<Trabajador>();
        Document doc = getDocumentFromXML(rutaFichero);

        NodeList nodosproductos = doc.getElementsByTagName("Trabajador");

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
