package Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Models.Banda;
import Models.Concierto;


public class CreacionCSV {
    
    private static final Logger logger = LogManager.getLogger(CreacionCSV.class);
    private static final String rutaResources = "src\\main\\resources\\";
    
    /**
     * Escribe bandas en CSV
     */
    public void escribeBandasCSV(List<Banda> bandas, String nombreFichero) {
        PrintWriter out = null;
        FileWriter fichero = null;
        
        try {
            fichero = new FileWriter(rutaResources + nombreFichero);
            out = new PrintWriter(fichero);
            
            // CABECERA
            out.printf("Codigo,Nombre,Pais Origen,Num Integrantes,Email\n");
            
            // Recorremos cada banda
            for (Banda b : bandas) {
                out.printf(
                    Locale.US,
                    "%s,%s,%s,%d,%s\n",
                    b.getCodigo(),
                    b.getNombre(),
                    b.getPaisOrigen(),
                    b.getNumIntegrantes(),
                    b.getEmailContacto()
                );
            }
            
            logger.info("CSV de bandas generado correctamente: " + nombreFichero);
            
        } catch (IOException e) {
            logger.error("Error al escribir CSV de bandas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (fichero != null) {
                try {
                    fichero.close();
                } catch (IOException e) {
                    logger.error("Error al cerrar fichero CSV");
                }
            }
        }
    }
    
    /**
     * Escribe conciertos en CSV
     */
    public void escribeConciertosCSV(List<Concierto> conciertos, String nombreFichero) {
        PrintWriter out = null;
        FileWriter fichero = null;
        
        try {
            fichero = new FileWriter(rutaResources + nombreFichero);
            out = new PrintWriter(fichero);
            
            // CABECERA
            out.printf("ID,Fecha,Escenario,Genero,Banda Headliner,Codigo Banda\n");
            
            // Recorremos cada concierto
            for (Concierto c : conciertos) {
                String nombreBanda = "N/A";
                String codigoBanda = "N/A";
                
                if (c.getBandaHeadliner() != null) {
                    nombreBanda = c.getBandaHeadliner().getNombre();
                    codigoBanda = c.getBandaHeadliner().getCodigo();
                }
                
                out.printf(
                    Locale.US,
                    "%d,%s,%s,%s,%s,%s\n",
                    c.getId(),
                    c.getFecha(),
                    c.getEscenario(),
                    c.getGeneroMusical(),
                    nombreBanda,
                    codigoBanda
                );
            }
            
            logger.info("CSV de conciertos generado correctamente: " + nombreFichero);
            
        } catch (IOException e) {
            logger.error("Error al escribir CSV de conciertos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (fichero != null) {
                try {
                    fichero.close();
                } catch (IOException e) {
                    logger.error("Error al cerrar fichero CSV");
                }
            }
        }
    }
    
    /**
     * Escribe conciertos detallados (con toda la info de la banda)
     */
    public void escribeConciertosDetalladosCSV(List<Concierto> conciertos, String nombreFichero) {
        PrintWriter out = null;
        FileWriter fichero = null;
        
        try {
            fichero = new FileWriter(rutaResources + nombreFichero);
            out = new PrintWriter(fichero);
            
            // CABECERA
            out.printf("ID Concierto,Fecha,Escenario,Genero,Codigo Banda,Nombre Banda,Pais Banda,Num Integrantes\n");
            
            for (Concierto c : conciertos) {
                if (c.getBandaHeadliner() != null) {
                    Banda banda = c.getBandaHeadliner();
                    
                    out.printf(
                        Locale.US,
                        "%d,%s,%s,%s,%s,%s,%s,%d\n",
                        c.getId(),
                        c.getFecha(),
                        c.getEscenario(),
                        c.getGeneroMusical(),
                        banda.getCodigo(),
                        banda.getNombre(),
                        banda.getPaisOrigen(),
                        banda.getNumIntegrantes()
                    );
                } else {
                    out.printf(
                        Locale.US,
                        "%d,%s,%s,%s,N/A,N/A,N/A,0\n",
                        c.getId(),
                        c.getFecha(),
                        c.getEscenario(),
                        c.getGeneroMusical()
                    );
                }
            }
            
            logger.info("CSV detallado generado correctamente: " + nombreFichero);
            
        } catch (IOException e) {
            logger.error("Error al escribir CSV detallado: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (fichero != null) {
                try {
                    fichero.close();
                } catch (IOException e) {
                    logger.error("Error al cerrar fichero CSV");
                }
            }
        }
    }
    
    /**
     * Escribe ranking de bandas en CSV
     */
    public void escribeRankingCSV(List<Map.Entry<String, Integer>> ranking, 
                                   List<Banda> bandas, 
                                   String nombreFichero) {
        PrintWriter out = null;
        FileWriter fichero = null;
        
        try {
            fichero = new FileWriter(rutaResources + nombreFichero);
            out = new PrintWriter(fichero);
            
            // CABECERA
            out.printf("Posicion,Codigo,Nombre Banda,Conciertos,Pais\n");
            
            int posicion = 1;
            
            for (Map.Entry<String, Integer> entry : ranking) {
                String codigo = entry.getKey();
                Integer conciertos = entry.getValue();
                
                // Buscar banda completa
                Banda banda = null;
                for (Banda b : bandas) {
                    if (b.getCodigo().equalsIgnoreCase(codigo)) {
                        banda = b;
                        break;
                    }
                }
                
                if (banda != null) {
                    out.printf(
                        Locale.US,
                        "%d,%s,%s,%d,%s\n",
                        posicion,
                        codigo,
                        banda.getNombre(),
                        conciertos,
                        banda.getPaisOrigen()
                    );
                } else {
                    out.printf(
                        Locale.US,
                        "%d,%s,Banda Desconocida,%d,N/A\n",
                        posicion,
                        codigo,
                        conciertos
                    );
                }
                
                posicion++;
            }
            
            logger.info("CSV de ranking generado correctamente: " + nombreFichero);
            
        } catch (IOException e) {
            logger.error("Error al escribir CSV de ranking: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (fichero != null) {
                try {
                    fichero.close();
                } catch (IOException e) {
                    logger.error("Error al cerrar fichero CSV");
                }
            }
        }
    }
}