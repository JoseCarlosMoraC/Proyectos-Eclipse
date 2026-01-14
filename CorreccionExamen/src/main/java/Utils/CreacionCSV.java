package Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Models.Enfrentamiento;
import Models.Equipo;



/**
 * Clase encargada de generar archivos CSV.
 * Permite exportar información de equipos, enfrentamientos y estadísticas
 * del torneo a formato CSV para análisis externo.
 */
public class CreacionCSV {
    
    private static final Logger logger = LogManager.getLogger(CreacionCSV.class);
    private static final String rutaResources = "src\\main\\resources\\";
    
    /**
     * Método encargado de escribir un archivo CSV con los equipos registrados.
     * 
     * @param equipos Lista de equipos del torneo.
     * @param nombreFichero Nombre del archivo CSV a crear.
     * 
     * Este método recorre cada equipo y escribe sus datos en formato CSV.
     * Se usa PrintWriter para escribir en formato texto.
     */
    public void escribeEquiposCSV(List<Equipo> equipos, String nombreFichero) {
        PrintWriter out = null;
        FileWriter fichero = null;
        
        try {
            // Abrimos el fichero en modo escritura
            fichero = new FileWriter(rutaResources + nombreFichero);
            out = new PrintWriter(fichero);
            
            // ESCRIBIMOS LA CABECERA DEL CSV
            out.printf("Codigo,Nombre Completo,Email Contacto,Numero Jugadores\n");
            
            // Recorremos cada equipo
            for (Equipo e : equipos) {
                out.printf(
                    Locale.US, // Para evitar problemas de formato numérico
                    "%s,%s,%s,%d\n",
                    e.getCodigo(),           // Código del equipo
                    e.getNombreCompleto(),   // Nombre completo del equipo
                    e.getEmailContacto(),    // Email de contacto
                    e.getNumJugadores()      // Número de jugadores
                );
            }
            
            logger.info("CSV de equipos generado correctamente: " + nombreFichero);
            
        } catch (IOException e) {
            logger.error("Error al escribir CSV de equipos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerramos escritor y archivo correctamente
            if (out != null) {
                out.close();
            }
            if (fichero != null) {
                try {
                    fichero.close();
                } catch (IOException e) {
                    logger.error("Error al cerrar el fichero CSV de equipos");
                }
            }
        }
    }
    
    /**
     * Método encargado de escribir un archivo CSV con los enfrentamientos del torneo.
     * 
     * @param enfrentamientos Lista de enfrentamientos del torneo.
     * @param nombreFichero Nombre del archivo CSV a crear.
     * 
     * Este método recorre cada enfrentamiento y escribe sus datos en formato CSV,
     * incluyendo información del equipo ganador.
     */
    public void escribeEnfrentamientosCSV(List<Enfrentamiento> enfrentamientos, String nombreFichero) {
        PrintWriter out = null;
        FileWriter fichero = null;
        
        try {
            // Abrimos el fichero en modo escritura
            fichero = new FileWriter(rutaResources + nombreFichero);
            out = new PrintWriter(fichero);
            
            // ESCRIBIMOS LA CABECERA DEL CSV
            out.printf("ID,Fecha,Descripcion,Videojuego,Equipo Ganador,Codigo Ganador\n");
            
            // Recorremos cada enfrentamiento
            for (Enfrentamiento e : enfrentamientos) {
                String nombreGanador = "N/A";
                String codigoGanador = "N/A";
                
                // Verificamos si hay equipo ganador
                if (e.getEquipoGanador() != null) {
                    nombreGanador = e.getEquipoGanador().getNombreCompleto();
                    codigoGanador = e.getEquipoGanador().getCodigo();
                }
                
                out.printf(
                    Locale.US,
                    "%d,%s,%s,%s,%s,%s\n",
                    e.getId(),              // ID del enfrentamiento
                    e.getFecha(),           // Fecha del enfrentamiento
                    e.getDescripcion(),     // Descripción (ej: "Fase Grupos 1")
                    e.getVideojuego(),      // Videojuego (LOL, VALORANT, CSGO)
                    nombreGanador,          // Nombre del equipo ganador
                    codigoGanador           // Código del equipo ganador
                );
            }
            
            logger.info("CSV de enfrentamientos generado correctamente: " + nombreFichero);
            
        } catch (IOException e) {
            logger.error("Error al escribir CSV de enfrentamientos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerramos escritor y archivo correctamente
            if (out != null) {
                out.close();
            }
            if (fichero != null) {
                try {
                    fichero.close();
                } catch (IOException e) {
                    logger.error("Error al cerrar el fichero CSV de enfrentamientos");
                }
            }
        }
    }
    
    /**
     * Método encargado de escribir un archivo CSV con enfrentamientos y sus equipos.
     * Similar al ejemplo de CentroLogistico con Trabajadores.
     * 
     * @param enfrentamientos Lista de enfrentamientos del torneo.
     * @param nombreFichero Nombre del archivo CSV a crear.
     * 
     * Este método genera un CSV con información detallada de cada enfrentamiento
     * y el equipo ganador, incluyendo todos los datos del equipo.
     */
    public void escribeEnfrentamientosDetalladosCSV(List<Enfrentamiento> enfrentamientos, String nombreFichero) {
        PrintWriter out = null;
        FileWriter fichero = null;
        
        try {
            // Abrimos el fichero en modo escritura
            fichero = new FileWriter(rutaResources + nombreFichero);
            out = new PrintWriter(fichero);
            
            // ESCRIBIMOS LA CABECERA DEL CSV (formato detallado)
            out.printf("ID Enfrentamiento,Fecha,Descripcion,Videojuego,Codigo Equipo,Nombre Equipo,Email Equipo,Num Jugadores\n");
            
            // Recorremos cada enfrentamiento
            for (Enfrentamiento e : enfrentamientos) {
                // Verificamos si hay equipo ganador
                if (e.getEquipoGanador() != null) {
                    Equipo ganador = e.getEquipoGanador();
                    
                    out.printf(
                        Locale.US,
                        "%d,%s,%s,%s,%s,%s,%s,%d\n",
                        e.getId(),                      // ID del enfrentamiento
                        e.getFecha(),                   // Fecha
                        e.getDescripcion(),             // Descripción
                        e.getVideojuego(),              // Videojuego
                        ganador.getCodigo(),            // Código del equipo ganador
                        ganador.getNombreCompleto(),    // Nombre del equipo ganador
                        ganador.getEmailContacto(),     // Email del equipo ganador
                        ganador.getNumJugadores()       // Número de jugadores
                    );
                } else {
                    // Si no hay ganador, escribimos N/A en los campos del equipo
                    out.printf(
                        Locale.US,
                        "%d,%s,%s,%s,N/A,N/A,N/A,0\n",
                        e.getId(),
                        e.getFecha(),
                        e.getDescripcion(),
                        e.getVideojuego()
                    );
                }
            }
            
            logger.info("CSV detallado de enfrentamientos generado correctamente: " + nombreFichero);
            
        } catch (IOException e) {
            logger.error("Error al escribir CSV detallado de enfrentamientos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerramos escritor y archivo correctamente
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
     * Método para escribir estadísticas de victorias por equipo en CSV.
     * 
     * @param estadisticas Mapa con código de equipo y número de victorias.
     * @param equipos Lista de equipos para obtener información adicional.
     * @param nombreFichero Nombre del archivo CSV a crear.
     */
    public void escribeEstadisticasVictoriasCSV(java.util.Map<String, Integer> estadisticas, 
                                                  List<Equipo> equipos, 
                                                  String nombreFichero) {
        PrintWriter out = null;
        FileWriter fichero = null;
        
        try {
            fichero = new FileWriter(rutaResources + nombreFichero);
            out = new PrintWriter(fichero);
            
            // ESCRIBIMOS LA CABECERA
            out.printf("Codigo Equipo,Nombre Equipo,Numero Victorias,Num Jugadores\n");
            
            // Recorremos las estadísticas
            for (java.util.Map.Entry<String, Integer> entry : estadisticas.entrySet()) {
                String codigo = entry.getKey();
                Integer victorias = entry.getValue();
                
                // Buscamos el equipo completo
                Equipo equipo = null;
                for (Equipo e : equipos) {
                    if (e.getCodigo().equalsIgnoreCase(codigo)) {
                        equipo = e;
                        break;
                    }
                }
                
                if (equipo != null) {
                    out.printf(
                        Locale.US,
                        "%s,%s,%d,%d\n",
                        codigo,
                        equipo.getNombreCompleto(),
                        victorias,
                        equipo.getNumJugadores()
                    );
                } else {
                    out.printf(
                        Locale.US,
                        "%s,Equipo Desconocido,%d,0\n",
                        codigo,
                        victorias
                    );
                }
            }
            
            logger.info("CSV de estadísticas de victorias generado correctamente: " + nombreFichero);
            
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
     * Método para escribir un ranking de equipos en CSV.
     * 
     * @param ranking Lista ordenada de equipos con sus victorias.
     * @param equipos Lista de equipos para obtener información adicional.
     * @param nombreFichero Nombre del archivo CSV a crear.
     */
    public void escribeRankingCSV(List<java.util.Map.Entry<String, Integer>> ranking, 
                                   List<Equipo> equipos, 
                                   String nombreFichero) {
        PrintWriter out = null;
        FileWriter fichero = null;
        
        try {
            fichero = new FileWriter(rutaResources + nombreFichero);
            out = new PrintWriter(fichero);
            
            // ESCRIBIMOS LA CABECERA
            out.printf("Posicion,Codigo Equipo,Nombre Equipo,Victorias,Email\n");
            
            int posicion = 1;
            
            // Recorremos el ranking
            for (java.util.Map.Entry<String, Integer> entry : ranking) {
                String codigo = entry.getKey();
                Integer victorias = entry.getValue();
                
                // Buscamos el equipo completo
                Equipo equipo = null;
                for (Equipo e : equipos) {
                    if (e.getCodigo().equalsIgnoreCase(codigo)) {
                        equipo = e;
                        break;
                    }
                }
                
                if (equipo != null) {
                    out.printf(
                        Locale.US,
                        "%d,%s,%s,%d,%s\n",
                        posicion,
                        codigo,
                        equipo.getNombreCompleto(),
                        victorias,
                        equipo.getEmailContacto()
                    );
                } else {
                    out.printf(
                        Locale.US,
                        "%d,%s,Equipo Desconocido,%d,N/A\n",
                        posicion,
                        codigo,
                        victorias
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