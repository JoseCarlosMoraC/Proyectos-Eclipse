package Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Models.Artista;
import Models.Concierto;

/**
 * Clase encargada de generar archivos CSV.
 * Permite exportar información de artistas, conciertos y estadísticas
 * del festival a formato CSV para análisis externo.
 */
public class CreacionCSV {
    
    private static final Logger logger = LogManager.getLogger(CreacionCSV.class);
    private static final String rutaResources = "src\\main\\resources\\";
    
    /**
     * Método encargado de escribir un archivo CSV con los artistas registrados.
     * 
     * @param artistas Lista de artistas del festival.
     * @param nombreFichero Nombre del archivo CSV a crear.
     */
    public void escribeArtistasCSV(List<Artista> artistas, String nombreFichero) {
        PrintWriter out = null;
        FileWriter fichero = null;
        
        try {
            fichero = new FileWriter(rutaResources + nombreFichero);
            out = new PrintWriter(fichero);
            
            // ESCRIBIMOS LA CABECERA DEL CSV
            out.printf("Codigo,Nombre Artistico,Email Manager,Numero Integrantes\n");
            
            for (Artista a : artistas) {
                out.printf(
                    Locale.US,
                    "%s,%s,%s,%d\n",
                    a.getCodigo(),
                    a.getNombreArtistico(),
                    a.getEmailManager(),
                    a.getNumIntegrantes()
                );
            }
            
            logger.info("CSV de artistas generado correctamente: " + nombreFichero);
            
        } catch (IOException e) {
            logger.error("Error al escribir CSV de artistas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (fichero != null) {
                try {
                    fichero.close();
                } catch (IOException e) {
                    logger.error("Error al cerrar el fichero CSV de artistas");
                }
            }
        }
    }
    
    /**
     * Método encargado de escribir un archivo CSV con los conciertos del festival.
     * 
     * @param conciertos Lista de conciertos del festival.
     * @param nombreFichero Nombre del archivo CSV a crear.
     */
    public void escribeConciertosCSV(List<Concierto> conciertos, String nombreFichero) {
        PrintWriter out = null;
        FileWriter fichero = null;
        
        try {
            fichero = new FileWriter(rutaResources + nombreFichero);
            out = new PrintWriter(fichero);
            
            // ESCRIBIMOS LA CABECERA DEL CSV
            out.printf("ID,Fecha,Descripcion,Genero,Artista Cabecera,Codigo Cabecera\n");
            
            for (Concierto c : conciertos) {
                String nombreCabecera = "N/A";
                String codigoCabecera = "N/A";
                
                if (c.getArtistaCabecera() != null) {
                    nombreCabecera = c.getArtistaCabecera().getNombreArtistico();
                    codigoCabecera = c.getArtistaCabecera().getCodigo();
                }
                
                out.printf(
                    Locale.US,
                    "%d,%s,%s,%s,%s,%s\n",
                    c.getId(),
                    c.getFecha(),
                    c.getDescripcion(),
                    c.getGenero(),
                    nombreCabecera,
                    codigoCabecera
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
                    logger.error("Error al cerrar el fichero CSV de conciertos");
                }
            }
        }
    }
    
    /**
     * Método encargado de escribir un archivo CSV con conciertos y sus artistas.
     * 
     * @param conciertos Lista de conciertos del festival.
     * @param nombreFichero Nombre del archivo CSV a crear.
     */
    public void escribeConciertosDetalladosCSV(List<Concierto> conciertos, String nombreFichero) {
        PrintWriter out = null;
        FileWriter fichero = null;
        
        try {
            fichero = new FileWriter(rutaResources + nombreFichero);
            out = new PrintWriter(fichero);
            
            // ESCRIBIMOS LA CABECERA DEL CSV (formato detallado)
            out.printf("ID Concierto,Fecha,Descripcion,Genero,Codigo Artista,Nombre Artista,Email Manager,Num Integrantes\n");
            
            for (Concierto c : conciertos) {
                if (c.getArtistaCabecera() != null) {
                    Artista cabecera = c.getArtistaCabecera();
                    
                    out.printf(
                        Locale.US,
                        "%d,%s,%s,%s,%s,%s,%s,%d\n",
                        c.getId(),
                        c.getFecha(),
                        c.getDescripcion(),
                        c.getGenero(),
                        cabecera.getCodigo(),
                        cabecera.getNombreArtistico(),
                        cabecera.getEmailManager(),
                        cabecera.getNumIntegrantes()
                    );
                } else {
                    out.printf(
                        Locale.US,
                        "%d,%s,%s,%s,N/A,N/A,N/A,0\n",
                        c.getId(),
                        c.getFecha(),
                        c.getDescripcion(),
                        c.getGenero()
                    );
                }
            }
            
            logger.info("CSV detallado de conciertos generado correctamente: " + nombreFichero);
            
        } catch (IOException e) {
            logger.error("Error al escribir CSV detallado de conciertos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (fichero != null) {
                try {
                    fichero.close();
                } catch (IOException e) {
                    logger.error("Error al cerrar el fichero CSV detallado");
                }
            }
        }
    }
    
    /**
     * Método para escribir estadísticas de actuaciones por artista en CSV.
     * 
     * @param estadisticas Mapa con código de artista y número de actuaciones.
     * @param artistas Lista de artistas para obtener información adicional.
     * @param nombreFichero Nombre del archivo CSV a crear.
     */
    public void escribeEstadisticasActuacionesCSV(java.util.Map<String, Integer> estadisticas, 
                                                   List<Artista> artistas, 
                                                   String nombreFichero) {
        PrintWriter out = null;
        FileWriter fichero = null;
        
        try {
            fichero = new FileWriter(rutaResources + nombreFichero);
            out = new PrintWriter(fichero);
            
            // ESCRIBIMOS LA CABECERA
            out.printf("Codigo Artista,Nombre Artista,Numero Actuaciones,Num Integrantes\n");
            
            for (java.util.Map.Entry<String, Integer> entry : estadisticas.entrySet()) {
                String codigo = entry.getKey();
                Integer actuaciones = entry.getValue();
                
                Artista artista = null;
                for (Artista a : artistas) {
                    if (a.getCodigo().equalsIgnoreCase(codigo)) {
                        artista = a;
                        break;
                    }
                }
                
                if (artista != null) {
                    out.printf(
                        Locale.US,
                        "%s,%s,%d,%d\n",
                        codigo,
                        artista.getNombreArtistico(),
                        actuaciones,
                        artista.getNumIntegrantes()
                    );
                } else {
                    out.printf(
                        Locale.US,
                        "%s,Artista Desconocido,%d,0\n",
                        codigo,
                        actuaciones
                    );
                }
            }
            
            logger.info("CSV de estadísticas de actuaciones generado correctamente: " + nombreFichero);
            
        } catch (IOException e) {
            logger.error("Error al escribir CSV de estadísticas: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
            if (fichero != null) {
                try {
                    fichero.close();
                } catch (IOException e) {
                    logger.error("Error al cerrar el fichero CSV de estadísticas");
                }
            }
        }
    }
    
    /**
     * Método para escribir un ranking de artistas en CSV.
     * 
     * @param ranking Lista ordenada de artistas con sus actuaciones.
     * @param artistas Lista de artistas para obtener información adicional.
     * @param nombreFichero Nombre del archivo CSV a crear.
     */
    public void escribeRankingCSV(List<java.util.Map.Entry<String, Integer>> ranking, 
                                   List<Artista> artistas, 
                                   String nombreFichero) {
        PrintWriter out = null;
        FileWriter fichero = null;
        
        try {
            fichero = new FileWriter(rutaResources + nombreFichero);
            out = new PrintWriter(fichero);
            
            // ESCRIBIMOS LA CABECERA
            out.printf("Posicion,Codigo Artista,Nombre Artista,Actuaciones,Email\n");
            
            int posicion = 1;
            
            for (java.util.Map.Entry<String, Integer> entry : ranking) {
                String codigo = entry.getKey();
                Integer actuaciones = entry.getValue();
                
                Artista artista = null;
                for (Artista a : artistas) {
                    if (a.getCodigo().equalsIgnoreCase(codigo)) {
                        artista = a;
                        break;
                    }
                }
                
                if (artista != null) {
                    out.printf(
                        Locale.US,
                        "%d,%s,%s,%d,%s\n",
                        posicion,
                        codigo,
                        artista.getNombreArtistico(),
                        actuaciones,
                        artista.getEmailManager()
                    );
                } else {
                    out.printf(
                        Locale.US,
                        "%d,%s,Artista Desconocido,%d,N/A\n",
                        posicion,
                        codigo,
                        actuaciones
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
                    logger.error("Error al cerrar el fichero CSV de ranking");
                }
            }
        }
    }
}