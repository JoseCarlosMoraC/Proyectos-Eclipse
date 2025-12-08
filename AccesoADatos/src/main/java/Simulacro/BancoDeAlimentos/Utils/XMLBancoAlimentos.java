package Simulacro.BancoDeAlimentos.Utils;

import java.io.File;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import Simulacro.BancoDeAlimentos.Models.CentroLogistico;
import Simulacro.BancoDeAlimentos.Models.Trabajador;

public class XMLBancoAlimentos {

    private static final Logger logger = LogManager.getLogger(XMLBancoAlimentos.class);

    private static final String rutaResources = "src\\main\\resources\\";

    /**
     * Escribe una lista de centros logísticos (y sus trabajadores) en un archivo XML.
     * Se cambia 
     */
    public void escribeCentrosEnXML(String nombreFichero, List<CentroLogistico> centros) {
        try {
            // Creamos la raíz <centros>
            Document documento = this.construyoObjetoDocumento("centros");

            for (CentroLogistico c : centros) {
                // Creamos un elemento <centro>
                Element elementoCentro = this.creaElemento("centro", null, documento.getDocumentElement(), documento);

                // Agregamos la información del centro y sus trabajadores
                agregaCentroADocumento(documento, elementoCentro, c);
            }

            // Escribimos el documento en el fichero
            escribeDocumentoEnFichero(documento, rutaResources + nombreFichero);
            logger.info("Archivo XML generado correctamente: " + nombreFichero);

        } catch (ParserConfigurationException | TransformerException e1) {
            logger.error("Error al escribir XML: " + e1.getMessage());
        }
    }

    /**
     * Crea y escribe el documento XML en el fichero físico.
     * No se cambia, siempre igual
     */
    private void escribeDocumentoEnFichero(Document documento, String nombreFichero) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

        DOMSource source = new DOMSource(documento);
        StreamResult resultado = new StreamResult(new File(nombreFichero));

        transformer.transform(source, resultado);
    }

    /**
     * Añade la información de un centro y sus trabajadores al documento.
     * Se cambia adaptantadolo
     */
    private void agregaCentroADocumento(Document documento, Element padre, CentroLogistico c) {
        // Información del centro
        this.creaElemento("nombreCentro", c.getNombre(), padre, documento);
        this.creaElemento("ciudad", c.getCiudad(), padre, documento);
        this.creaElemento("numComedores", String.valueOf(c.getNumComedores()), padre, documento);

        // Lista de trabajadores
        Element trabajadores = this.creaElemento("trabajadores", null, padre, documento);

        for (Trabajador t : c.getTrabajadores()) {
            Element trabajador = this.creaElemento("trabajador", null, trabajadores, documento);

            this.creaElemento("nombre", t.getNombre(), trabajador, documento);
            this.creaElemento("dni", t.getDni(), trabajador, documento);
            this.creaElemento("fechaNacimiento", t.getFecha().toString(), trabajador, documento);
            this.creaElemento("tipo", t.getTipo().toString(), trabajador, documento);
            this.creaElemento("idCentro", t.getIdCentro(), trabajador, documento);

            // Atributo identificador del trabajador
            trabajador.setAttribute("dni", t.getDni());
        }

        // Atributo identificador del centro
        padre.setAttribute("idCentro", c.getId());
    }

    /**
     * Crea un elemento XML (con o sin valor) y lo añade al nodo padre.
     * No se cambia
     */
    private Element creaElemento(String nombreElemento, String valorElemento, Element padre, Document documento) {
        Element elemento = documento.createElement(nombreElemento);
        if (valorElemento != null) {
            Text texto = documento.createTextNode(valorElemento);
            elemento.appendChild(texto);
        }
        padre.appendChild(elemento);
        return elemento;
    }

    /**
     * Crea la estructura base de un documento XML con una raíz.
     * No se cambia
     */
    private Document construyoObjetoDocumento(String nombreRaiz) throws ParserConfigurationException {
        DocumentBuilderFactory factoria = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factoria.newDocumentBuilder();
        DOMImplementation implementacion = builder.getDOMImplementation();
        return implementacion.createDocument(null, nombreRaiz, null);
    }
}
