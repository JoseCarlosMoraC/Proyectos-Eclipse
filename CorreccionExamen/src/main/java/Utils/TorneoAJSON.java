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

import Models.Enfrentamiento;
import Models.Equipo;



public class TorneoAJSON {
    
    private static final Logger logger = LogManager.getLogger(TorneoAJSON.class);
    
    private static final String rutaResources = "src\\main\\resources\\";
    
    /**
     * Lee equipos desde XML (usando XMLDomEsports)
     * XML esperado: código, nombreCompleto, email, numJugadores
     */
    public List<Equipo> leeEquiposXml(String ruta) throws Exception {
        XMLDomEsports lectorXML = new XMLDomEsports();
        List<Equipo> listaEquipos = lectorXML.leerEquipoDesdeXML(ruta);
        logger.info("Equipos leídos desde XML: " + listaEquipos.size());
        return listaEquipos;
    }
    
    /**
     * Lee enfrentamientos desde XML (usando XMLDomEsports)
     * XML esperado: id, fecha, descripcion, videojuego, equipoGanador
     */
    public List<Enfrentamiento> leeEnfrentamientosXml(String ruta) throws Exception {
        XMLDomEsports lectorXML = new XMLDomEsports();
        List<Enfrentamiento> listaEnfrentamientos = lectorXML.leerEnfrentamientoDesdeXML(ruta);
        logger.info("Enfrentamientos leídos desde XML: " + listaEnfrentamientos.size());
        return listaEnfrentamientos;
    }
    
    /**
     * Escribe la lista de equipos en JSON
     */
    public void escribeEquiposJson(List<Equipo> equipos, String nombreFichero) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(equipos);
        FileWriter fichero = null;
        try {
            fichero = new FileWriter(rutaResources + nombreFichero);
            fichero.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fichero != null) {
                try {
                    fichero.close();
                } catch (IOException e) {
                    logger.error("Error al escribir equipos");
                }           
            }       
        }
        logger.info("Archivo JSON generado correctamente: " + nombreFichero);
    }
    
    /**
     * Escribe la lista de enfrentamientos en JSON
     */
    public void escribeEnfrentamientosJson(List<Enfrentamiento> enfrentamientos, String nombreFichero) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(enfrentamientos);
        FileWriter fichero = null;
        try {
            fichero = new FileWriter(rutaResources + nombreFichero);
            fichero.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fichero != null) {
                try {
                    fichero.close();
                } catch (IOException e) {
                    logger.error("Error al escribir enfrentamientos");
                }           
            }       
        }
        logger.info("Archivo JSON generado correctamente: " + nombreFichero);
    }
    
    /**
     * Lee equipos desde JSON
     */
    public List<Equipo> leeEquiposJson(String nombreFichero) {
        Gson gson = new Gson();
        FileReader fichero = null;
        List<Equipo> equipos = null;
        
        try {
            fichero = new FileReader(rutaResources + nombreFichero);
            Type tipoLista = new TypeToken<List<Equipo>>(){}.getType();
            equipos = gson.fromJson(fichero, tipoLista);
        } catch (IOException e) {
            logger.error("Error al leer equipos desde JSON");
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
        
        logger.info("Equipos leídos desde JSON: " + (equipos != null ? equipos.size() : 0));
        return equipos;
    }
    
    /**
     * Lee enfrentamientos desde JSON
     */
    public List<Enfrentamiento> leeEnfrentamientosJson(String nombreFichero) {
        Gson gson = new Gson();
        FileReader fichero = null;
        List<Enfrentamiento> enfrentamientos = null;
        
        try {
            fichero = new FileReader(rutaResources + nombreFichero);
            Type tipoLista = new TypeToken<List<Enfrentamiento>>(){}.getType();
            enfrentamientos = gson.fromJson(fichero, tipoLista);
        } catch (IOException e) {
            logger.error("Error al leer enfrentamientos desde JSON");
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
        
        logger.info("Enfrentamientos leídos desde JSON: " + (enfrentamientos != null ? enfrentamientos.size() : 0));
        return enfrentamientos;
    }
}