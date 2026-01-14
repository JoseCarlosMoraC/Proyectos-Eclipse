package Controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Exception.LiveFestException;
import Models.Artista;
import Models.Concierto;
import Models.GeneroMusical;
import Services.LiveFestService;
import Utils.CreacionCSV;
import Utils.LiveFestAJSON;
import Utils.XMLDomLiveFest;

public class GestionaLiveFest {
    private static final Logger logger = LogManager.getLogger(GestionaLiveFest.class);

    public static void main(String[] args) {

        // Instancias necesarias
        XMLDomLiveFest lectorXML = new XMLDomLiveFest();
        LiveFestService servicio = new LiveFestService();
        LiveFestAJSON conversorJson = new LiveFestAJSON();

        try {
            logger.info("========== INICIANDO GESTIÓN DE LIVEFEST ==========");

            // ==================== APARTADO 2: CARGA DE DATOS DESDE XML ====================
            
            logger.info("\n--- APARTADO 2: CARGA DE DATOS DESDE XML ---");
            
            // Usar los métodos leerXmlArtistas y leerXmlConciertos
            List<Artista> artistas = conversorJson.leerXmlArtistas("liveFestData.xml");
            List<Concierto> conciertos = conversorJson.leerXmlConciertos("liveFestData.xml");
            
            // Agregar usando los métodos agregarListaArtistas y agregarListaConciertos
            servicio.setListaArtistas(new HashSet<>(artistas));
            servicio.setListaConciertos(new HashSet<>(conciertos));
            
            logger.info("Artistas cargados: " + servicio.getListaArtistas().size());
            logger.info("Conciertos cargados: " + servicio.getListaConciertos().size());

            // ==================== MOSTRAR LISTAS COMPLETAS ====================
            
            logger.info("\n========== MOSTRANDO TODOS LOS ARTISTAS CARGADOS ==========");
            List<Artista> todosLosArtistas = servicio.getTodosLosArtistas();
            logger.info("Total de artistas: " + todosLosArtistas.size());
            logger.info("-----------------------------------------------------------");
            for (Artista a : todosLosArtistas) {
                logger.info(String.format("Código: %-10s | Nombre: %-30s | Integrantes: %2d | Email: %s", 
                    a.getCodigo(), 
                    a.getNombreArtistico(), 
                    a.getNumIntegrantes(), 
                    a.getEmailManager()));
            }
            
            logger.info("\n========== MOSTRANDO TODOS LOS CONCIERTOS CARGADOS ==========");
            List<Concierto> todosLosConciertos = servicio.getTodosLosConciertos();
            logger.info("Total de conciertos: " + todosLosConciertos.size());
            logger.info("-----------------------------------------------------------");
            for (Concierto c : todosLosConciertos) {
                String artistaCabecera = (c.getArtistaCabecera() != null) ? 
                    c.getArtistaCabecera().getCodigo() : "N/A";
                logger.info(String.format("ID: %3d | Fecha: %s | Género: %-11s | Artista: %-10s | %s", 
                    c.getId(), 
                    c.getFecha(), 
                    c.getGenero(), 
                    artistaCabecera,
                    c.getDescripcion()));
            }
            
            // ==================== APARTADO 3: GENERACIÓN DE JSON ====================
            
            logger.info("\n--- APARTADO 3: GENERACIÓN DE JSON ---");
            
            // Llamada a getConciertosPorArtista
            try {
                String codigoArtista = "LP"; // Código del artista a buscar
                List<Concierto> conciertosArtista = servicio.getConciertosPorArtista(codigoArtista);
                
                logger.info("Conciertos de " + codigoArtista + ": " + conciertosArtista.size());
                
                // Llamada a generaJson en el paquete utils
                conversorJson.generaJson(conciertosArtista, "conciertos_" + codigoArtista + ".json");
                logger.info("JSON generado correctamente para conciertos de " + codigoArtista);
                
            } catch (LiveFestException e) {
                logger.error("Error al obtener conciertos del artista: " + e.getMessage());
            }
            
            // ==================== CONVERSIÓN A JSON ====================
            
            logger.info("\n--- CONVIRTIENDO DATOS A JSON ---");
            
            // Convertir artistas a JSON
            conversorJson.escribeArtistasJson(artistas, "artistas.json");
            
            // Convertir conciertos a JSON
            conversorJson.escribeConciertosJson(conciertos, "conciertos.json");

            // ==================== CONSULTAS BÁSICAS DE ARTISTAS ====================
            
            logger.info("\n--- CONSULTAS DE ARTISTAS ---");
            
            // Obtener un artista específico
            Artista artistaLP = servicio.getArtista("LP");
            logger.info("Artista LP: " + artistaLP);
            
            // Buscar artistas por nombre
            List<Artista> artistasBuscados = servicio.buscarArtistasPorNombre("The");
            logger.info("Artistas que contienen 'The': " + artistasBuscados.size());
            
            // Artistas con más de 3 integrantes
            List<Artista> artistasGrandes = servicio.getArtistasPorNumIntegrantes(4);
            logger.info("Artistas con 4+ integrantes: " + artistasGrandes.size());
            for (Artista a : artistasGrandes) {
                logger.info("  - " + a.getNombreArtistico() + " (" + a.getNumIntegrantes() + " integrantes)");
            }
            
            // Artista con más y menos integrantes
            Artista artistaMasIntegrantes = servicio.getArtistaConMasIntegrantes();
            Artista artistaMenosIntegrantes = servicio.getArtistaConMenosIntegrantes();
            logger.info("Artista con más integrantes: " + artistaMasIntegrantes.getNombreArtistico() + 
                       " (" + artistaMasIntegrantes.getNumIntegrantes() + ")");
            logger.info("Artista con menos integrantes: " + artistaMenosIntegrantes.getNombreArtistico() + 
                       " (" + artistaMenosIntegrantes.getNumIntegrantes() + ")");
            
            // Promedio de integrantes
            double promedio = servicio.getPromedioIntegrantesPorArtista();
            logger.info("Promedio de integrantes por artista: " + String.format("%.2f", promedio));

            // ==================== CONSULTAS DE CONCIERTOS ====================
            
            logger.info("\n--- CONSULTAS DE CONCIERTOS ---");
            
            // Conciertos de Linkin Park
            List<Concierto> conciertosLP = servicio.getConciertosPorArtista("LP");
            logger.info("Actuaciones de LP: " + conciertosLP.size());
            for (Concierto c : conciertosLP) {
                logger.info("  - " + c.getDescripcion() + " (" + c.getGenero() + ") - " + c.getFecha());
            }
            
            // Conciertos por género musical
            List<Concierto> conciertosRock = servicio.getConciertosPorGenero(GeneroMusical.ROCK);
            logger.info("Conciertos de ROCK: " + conciertosRock.size());
            
            List<Concierto> conciertosPop = servicio.getConciertosPorGenero(GeneroMusical.POP);
            logger.info("Conciertos de POP: " + conciertosPop.size());
            
            List<Concierto> conciertosElectronica = servicio.getConciertosPorGenero(GeneroMusical.ELECTRONICA);
            logger.info("Conciertos de ELECTRONICA: " + conciertosElectronica.size());
            
            // Conciertos por fecha
            List<Concierto> conciertosFecha = servicio.getConciertosPorFecha("2025-06-01");
            logger.info("Conciertos del 2025-06-01: " + conciertosFecha.size());
            
            // Conciertos por descripción
            List<Concierto> finales = servicio.getConciertosPorDescripcion("Final");
            logger.info("Finales del festival: " + finales.size());
            for (Concierto c : finales) {
                logger.info("  - " + c.getDescripcion() + " - Artista: " + 
                           (c.getArtistaCabecera() != null ? c.getArtistaCabecera().getCodigo() : "N/A"));
            }

            // ==================== ESTADÍSTICAS Y RANKINGS ====================
            
            logger.info("\n--- ESTADÍSTICAS Y RANKINGS ---");
            
            // Estadísticas de actuaciones por artista
            Map<String, Integer> estadisticasActuaciones = servicio.getEstadisticasActuacionesPorArtista();
            logger.info("Estadísticas de actuaciones:");
            for (Map.Entry<String, Integer> entry : estadisticasActuaciones.entrySet()) {
                logger.info("  - " + entry.getKey() + ": " + entry.getValue() + " actuaciones");
            }
            
            // Ranking completo de artistas
            logger.info("\nRanking de artistas por actuaciones:");
            List<Map.Entry<String, Integer>> ranking = servicio.getRankingArtistasPorActuaciones();
            int posicion = 1;
            for (Map.Entry<String, Integer> entry : ranking) {
                logger.info("  " + posicion + ". " + entry.getKey() + " - " + entry.getValue() + " actuaciones");
                posicion++;
            }
            
            // Top 3 artistas
            logger.info("\nTop 3 artistas:");
            List<Map.Entry<String, Integer>> top3 = servicio.getTopNArtistas(3);
            posicion = 1;
            for (Map.Entry<String, Integer> entry : top3) {
                logger.info("  " + posicion + ". " + entry.getKey() + " - " + entry.getValue() + " actuaciones");
                posicion++;
            }
            
            // Artista con más actuaciones
            String estrella = servicio.getArtistaConMasActuaciones();
            logger.info("\nArtista estrella: " + estrella);
            
            // Artistas sin actuaciones
            List<String> artistasSinActuaciones = servicio.getArtistasSinActuaciones();
            logger.info("Artistas sin actuaciones: " + artistasSinActuaciones.size());
            for (String codigo : artistasSinActuaciones) {
                logger.info("  - " + codigo);
            }
            
            // Estadísticas por género
            Map<GeneroMusical, Integer> estadisticasGenero = servicio.getEstadisticasPorGenero();
            logger.info("\nEstadísticas por género musical:");
            for (Map.Entry<GeneroMusical, Integer> entry : estadisticasGenero.entrySet()) {
                logger.info("  - " + entry.getKey() + ": " + entry.getValue() + " conciertos");
            }
            
            // Género con más conciertos
            GeneroMusical generoPopular = servicio.getGeneroConMasConciertos();
            logger.info("Género más popular: " + generoPopular);

            // ==================== INFORMACIÓN COMPLETA DE ARTISTAS ====================
            
            logger.info("\n--- INFORMACIÓN DETALLADA DE ARTISTAS ---");
            
            // Información completa de algunos artistas
            String[] artistasInteres = {"LP", "TS", "CALVIN", "MUSE"};
            for (String codigo : artistasInteres) {
                try {
                    String info = servicio.getInformacionCompletaArtista(codigo);
                    logger.info("\n" + info);
                } catch (LiveFestException e) {
                    logger.error("Error al obtener información de " + codigo + ": " + e.getMessage());
                }
            }

            // ==================== ACTUACIONES ESPECÍFICAS ====================
            
            logger.info("\n--- ACTUACIONES POR GÉNERO MUSICAL ---");
            
            // Actuaciones de LP por cada género
            int actuacionesLPRock = servicio.getNumActuacionesArtistaPorGenero("LP", GeneroMusical.ROCK);
            int actuacionesLPPop = servicio.getNumActuacionesArtistaPorGenero("LP", GeneroMusical.POP);
            int actuacionesLPElectronica = servicio.getNumActuacionesArtistaPorGenero("LP", GeneroMusical.ELECTRONICA);
            
            logger.info("Actuaciones de LP:");
            logger.info("  - ROCK: " + actuacionesLPRock);
            logger.info("  - POP: " + actuacionesLPPop);
            logger.info("  - ELECTRONICA: " + actuacionesLPElectronica);

            // ==================== AGRUPACIONES ====================
            
            logger.info("\n--- CONCIERTOS AGRUPADOS ---");
            
            // Agrupar por género
            Map<GeneroMusical, List<Concierto>> agrupadosPorGenero = 
                servicio.getConciertosAgrupadosPorGenero();
            logger.info("Conciertos agrupados por género:");
            for (Map.Entry<GeneroMusical, List<Concierto>> entry : agrupadosPorGenero.entrySet()) {
                logger.info("  - " + entry.getKey() + ": " + entry.getValue().size() + " conciertos");
            }
            
            // Agrupar por fecha
            Map<String, List<Concierto>> agrupadosPorFecha = 
                servicio.getConciertosAgrupadosPorFecha();
            logger.info("\nPrimeras 5 fechas con conciertos:");
            int contador = 0;
            for (Map.Entry<String, List<Concierto>> entry : agrupadosPorFecha.entrySet()) {
                if (contador < 5) {
                    logger.info("  - " + entry.getKey() + ": " + entry.getValue().size() + " conciertos");
                    contador++;
                }
            }

            // ==================== ESTADÍSTICAS GENERALES ====================
            
            logger.info("\n" + servicio.getEstadisticasGenerales());

            // ==================== ORDENAMIENTOS ====================
            
            logger.info("\n--- LISTADOS ORDENADOS ---");
            
            // Artistas ordenados por nombre
            List<Artista> artistasPorNombre = servicio.getArtistasOrdenadosPorNombre();
            logger.info("Primeros 5 artistas ordenados alfabéticamente:");
            for (int i = 0; i < Math.min(5, artistasPorNombre.size()); i++) {
                logger.info("  - " + artistasPorNombre.get(i).getNombreArtistico());
            }
            
            // Conciertos ordenados por fecha
            List<Concierto> conciertosPorFecha = servicio.getConciertosordenadosPorFecha();
            logger.info("\nPrimeros 5 conciertos cronológicamente:");
            for (int i = 0; i < Math.min(5, conciertosPorFecha.size()); i++) {
                Concierto c = conciertosPorFecha.get(i);
                logger.info("  - " + c.getFecha() + ": " + c.getDescripcion());
            }

            // ==================== EXPORTACIONES ADICIONALES ====================
            
            logger.info("\n--- EXPORTANDO DATOS FILTRADOS A JSON ---");
            
            // Exportar solo conciertos de ROCK
            conversorJson.escribeConciertosJson(conciertosRock, "conciertos_rock.json");
            logger.info("Conciertos de ROCK exportados a conciertos_rock.json");
            
            // Exportar artistas grandes
            conversorJson.escribeArtistasJson(artistasGrandes, "artistas_grupos.json");
            logger.info("Grupos grandes exportados a artistas_grupos.json");
            
            // Exportar finales
            conversorJson.escribeConciertosJson(finales, "finales.json");
            logger.info("Finales exportadas a finales.json");

            // ==================== EXPORTACIONES A CSV ====================
            
            logger.info("\n--- EXPORTANDO DATOS A CSV ---");
            
            CreacionCSV creadorCSV = new CreacionCSV();
            
            // Exportar artistas a CSV
            creadorCSV.escribeArtistasCSV(artistas, "artistas.csv");
            logger.info("Artistas exportados a CSV");
            
            // Exportar conciertos a CSV
            creadorCSV.escribeConciertosCSV(conciertos, "conciertos.csv");
            logger.info("Conciertos exportados a CSV");
            
            // Exportar conciertos detallados (con info completa del artista cabecera)
            creadorCSV.escribeConciertosDetalladosCSV(conciertos, "conciertos_detallados.csv");
            logger.info("Conciertos detallados exportados a CSV");
            
            // Exportar estadísticas de actuaciones a CSV
            creadorCSV.escribeEstadisticasActuacionesCSV(estadisticasActuaciones, artistas, "estadisticas_actuaciones.csv");
            logger.info("Estadísticas de actuaciones exportadas a CSV");
            
            // Exportar ranking a CSV
            creadorCSV.escribeRankingCSV(ranking, artistas, "ranking_artistas.csv");
            logger.info("Ranking de artistas exportado a CSV");

            logger.info("\n========== GESTIÓN DE LIVEFEST FINALIZADA CORRECTAMENTE ==========");

        } catch (LiveFestException e) {
            logger.error("Error del sistema: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}