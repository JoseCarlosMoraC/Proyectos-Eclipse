package Utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import Models.Artista;
import Models.Concierto;

public class LiveFestAJSON {
    
    private static final Logger logger = LogManager.getLogger(LiveFestAJSON.class);
    
    private static final String rutaResources = "src\\main\\resources\\";
    
    /**
     * Lee artistas desde XML (usando XMLDomLiveFest)
     */
    public List<Artista> leerXmlArtistas(String ruta) throws Exception {
        XMLDomLiveFest lectorXML = new XMLDomLiveFest();
        List<Artista> listaArtistas = lectorXML.leerArtistaDesdeXML(ruta);
        logger.info("Artistas leídos desde XML: " + listaArtistas.size());
        return listaArtistas;
    }
    
    /**
     * Lee conciertos desde XML (usando XMLDomLiveFest)
     */
    public List<Concierto> leerXmlConciertos(String ruta) throws Exception {
        XMLDomLiveFest lectorXML = new XMLDomLiveFest();
        List<Concierto> listaConciertos = lectorXML.leerConciertoDesdeXML(ruta);
        logger.info("Conciertos leídos desde XML: " + listaConciertos.size());
        return listaConciertos;
    }
    
    /**
     * Escribe la lista de artistas en JSON
     */
    public void escribeArtistasJson(List<Artista> artistas, String nombreFichero) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(artistas);
        FileWriter fichero = null;
        try {
            fichero = new FileWriter(rutaResources + nombreFichero);
            fichero.write(json);
        } catch (IOException e) {
            logger.error("Error al escribir artistas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (fichero != null) {
                try {
                    fichero.close();
                } catch (IOException e) {
                    logger.error("Error al cerrar archivo de artistas");
                }           
            }       
        }
        logger.info("Archivo JSON generado correctamente: " + nombreFichero);
    }
    
    /**
     * Escribe la lista de conciertos en JSON
     */
    public void escribeConciertosJson(List<Concierto> conciertos, String nombreFichero) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(conciertos);
        FileWriter fichero = null;
        try {
            fichero = new FileWriter(rutaResources + nombreFichero);
            fichero.write(json);
        } catch (IOException e) {
            logger.error("Error al escribir conciertos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (fichero != null) {
                try {
                    fichero.close();
                } catch (IOException e) {
                    logger.error("Error al cerrar archivo de conciertos");
                }           
            }       
        }
        logger.info("Archivo JSON generado correctamente: " + nombreFichero);
    }
    
    /**
     * APARTADO 3: Genera JSON con lista de conciertos de un artista
     */
    public void generaJson(List<Concierto> listaConciertos, String nombreFichero) {
        escribeConciertosJson(listaConciertos, nombreFichero);
    }
    
    /**
     * Lee artistas desde JSON
     */
    public List<Artista> leeArtistasJson(String nombreFichero) {
        Gson gson = new Gson();
        FileReader fichero = null;
        List<Artista> artistas = null;
        
        try {
            fichero = new FileReader(rutaResources + nombreFichero);
            Type tipoLista = new TypeToken<List<Artista>>(){}.getType();
            artistas = gson.fromJson(fichero, tipoLista);
        } catch (IOException e) {
            logger.error("Error al leer artistas desde JSON");
            e.printStackTrace();
        } finally {
            if (fichero != null) {
                try {
                    fichero.close();
                } catch (IOException e) {
                    logger.error("Error al cerrar archivo");
                }
            }
        }
        
        logger.info("Artistas leídos desde JSON: " + (artistas != null ? artistas.size() : 0));
        return artistas;
    }
    
    /**
     * Lee conciertos desde JSON
     */
    public List<Concierto> leeConciertosJson(String nombreFichero) {
        Gson gson = new Gson();
        FileReader fichero = null;
        List<Concierto> conciertos = null;
        
        try {
            fichero = new FileReader(rutaResources + nombreFichero);
            Type tipoLista = new TypeToken<List<Concierto>>(){}.getType();
            conciertos = gson.fromJson(fichero, tipoLista);
        } catch (IOException e) {
            logger.error("Error al leer conciertos desde JSON");
            e.printStackTrace();
        } finally {
            if (fichero != null) {
                try {
                    fichero.close();
                } catch (IOException e) {
                    logger.error("Error al cerrar archivo");
                }
            }
        }
        
        logger.info("Conciertos leídos desde JSON: " + (conciertos != null ? conciertos.size() : 0));
        return conciertos;
    }
}