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

import Models.Banda;
import Models.Concierto;


public class LiveFestAJson {
    
    private static final Logger logger = LogManager.getLogger(LiveFestAJson.class);
    private static final String rutaResources = "src\\main\\resources\\";
    
    /**
     * Lee bandas desde XML (usando XMLDomLiveFest)
     */
    public List<Banda> leerXmlBandas(String ruta) throws Exception {
        XMLDomLiveFest lectorXML = new XMLDomLiveFest();
        List<Banda> listaBandas = lectorXML.leerBandasDesdeXML(ruta);
        logger.info("Bandas leídas desde XML: " + listaBandas.size());
        return listaBandas;
    }
    
    /**
     * Lee conciertos desde XML (usando XMLDomLiveFest)
     */
    public List<Concierto> leerXmlConciertos(String ruta) throws Exception {
        XMLDomLiveFest lectorXML = new XMLDomLiveFest();
        List<Concierto> listaConciertos = lectorXML.leerConciertosDesdeXML(ruta);
        logger.info("Conciertos leídos desde XML: " + listaConciertos.size());
        return listaConciertos;
    }
    
    /**
     * Escribe la lista de bandas en JSON
     */
    public void escribeBandasJson(List<Banda> bandas, String nombreFichero) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(bandas);
        FileWriter fichero = null;
        try {
            fichero = new FileWriter(rutaResources + nombreFichero);
            fichero.write(json);
        } catch (IOException e) {
            logger.error("Error al escribir bandas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (fichero != null) {
                try {
                    fichero.close();
                } catch (IOException e) {
                    logger.error("Error al cerrar archivo de bandas");
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
     * APARTADO 3: Genera JSON con lista de conciertos de una banda
     */
    public void generaJson(List<Concierto> listaConciertosBanda, String nombreFichero) {
        escribeConciertosJson(listaConciertosBanda, nombreFichero);
    }
    
    /**
     * Lee bandas desde JSON
     */
    public List<Banda> leeBandasJson(String nombreFichero) {
        Gson gson = new Gson();
        FileReader fichero = null;
        List<Banda> bandas = null;
        
        try {
            fichero = new FileReader(rutaResources + nombreFichero);
            Type tipoLista = new TypeToken<List<Banda>>(){}.getType();
            bandas = gson.fromJson(fichero, tipoLista);
        } catch (IOException e) {
            logger.error("Error al leer bandas desde JSON");
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
        
        logger.info("Bandas leídas desde JSON: " + (bandas != null ? bandas.size() : 0));
        return bandas;
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