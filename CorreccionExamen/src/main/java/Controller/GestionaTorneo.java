package Controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Exception.TorneoException;
import Models.Enfrentamiento;
import Models.Equipo;
import Models.Videojuego;
import Services.TorneoService;
import Utils.CreacionCSV;
import Utils.TorneoAJSON;
import Utils.XMLDomEsports;


public class GestionaTorneo {
    private static final Logger logger = LogManager.getLogger(GestionaTorneo.class);

    public static void main(String[] args) {

        // Instancias necesarias
        XMLDomEsports lectorXML = new XMLDomEsports();
        TorneoService servicio = new TorneoService();
        TorneoAJSON conversorJson = new TorneoAJSON();

        try {
            logger.info("========== INICIANDO GESTIÓN DE TORNEO ==========");

            // ==================== APARTADO 2: CARGA DE DATOS DESDE XML ====================
            
            logger.info("\n--- APARTADO 2: CARGA DE DATOS DESDE XML ---");
            
            // Usar los métodos leerXmlEquipos y leerXmlEnfrentamientos
            List<Equipo> equipos = conversorJson.leerXmlEquipos("torneoGamer.xml");
            List<Enfrentamiento> enfrentamientos = conversorJson.leerXmlEnfrentamientos("torneoGamer.xml");
            
            // Agregar usando los métodos agregarListaEquipos y agregarListaEnfrentamientos
            servicio.setListaEquipos(new HashSet<>(equipos));
            servicio.setListaEnfrentamientos(new HashSet<>(enfrentamientos));
            
            logger.info("Equipos cargados: " + servicio.getListaEquipos().size());
            logger.info("Enfrentamientos cargados: " + servicio.getListaEnfrentamientos().size());

            // ==================== APARTADO 3: GENERACIÓN DE JSON ====================
            
            logger.info("\n--- APARTADO 3: GENERACIÓN DE JSON ---");
            
            // Llamada a getEnfrentamientosGanados (debe comparar String con Equipo correctamente)
            try {
                String codigoEquipo = "G2"; // Código del equipo a buscar
                List<Enfrentamiento> enfrentamientosGanados = servicio.getEnfrentamientosPorEquipo(codigoEquipo);
                
                logger.info("Enfrentamientos ganados por " + codigoEquipo + ": " + enfrentamientosGanados.size());
                
                // Llamada a generaJson en el paquete utils
                conversorJson.generaJson(enfrentamientosGanados, "enfrentamientosGanados_" + codigoEquipo + ".json");
                logger.info("JSON generado correctamente para enfrentamientos ganados por " + codigoEquipo);
                
            } catch (TorneoException e) {
                logger.error("Error al obtener enfrentamientos ganados: " + e.getMessage());
            }
            
            // ==================== CONVERSIÓN A JSON ====================
            
            logger.info("\n--- CONVIRTIENDO DATOS A JSON ---");
            
            // Convertir equipos a JSON
            conversorJson.escribeEquiposJson(equipos, "equipos.json");
            
            // Convertir enfrentamientos a JSON
            conversorJson.escribeEnfrentamientosJson(enfrentamientos, "enfrentamientos.json");

            // ==================== CONSULTAS BÁSICAS DE EQUIPOS ====================
            
            logger.info("\n--- CONSULTAS DE EQUIPOS ---");
            
            // Obtener un equipo específico
            Equipo equipoG2 = servicio.getEquipo("G2");
            logger.info("Equipo G2: " + equipoG2);
            
            // Buscar equipos por nombre
            List<Equipo> equiposBuscados = servicio.buscarEquiposPorNombre("Team");
            logger.info("Equipos que contienen 'Team': " + equiposBuscados.size());
            
            // Equipos con más de 5 jugadores
            List<Equipo> equiposGrandes = servicio.getEquiposPorNumJugadores(6);
            logger.info("Equipos con 6+ jugadores: " + equiposGrandes.size());
            for (Equipo e : equiposGrandes) {
                logger.info("  - " + e.getNombreCompleto() + " (" + e.getNumJugadores() + " jugadores)");
            }
            
            // Equipo con más y menos jugadores
            Equipo equipoMasJugadores = servicio.getEquipoConMasJugadores();
            Equipo equipoMenosJugadores = servicio.getEquipoConMenosJugadores();
            logger.info("Equipo con más jugadores: " + equipoMasJugadores.getNombreCompleto() + 
                       " (" + equipoMasJugadores.getNumJugadores() + ")");
            logger.info("Equipo con menos jugadores: " + equipoMenosJugadores.getNombreCompleto() + 
                       " (" + equipoMenosJugadores.getNumJugadores() + ")");
            
            // Promedio de jugadores
            double promedio = servicio.getPromedioJugadoresPorEquipo();
            logger.info("Promedio de jugadores por equipo: " + String.format("%.2f", promedio));

            // ==================== CONSULTAS DE ENFRENTAMIENTOS ====================
            
            logger.info("\n--- CONSULTAS DE ENFRENTAMIENTOS ---");
            
            // Enfrentamientos ganados por G2
            List<Enfrentamiento> victoriasG2 = servicio.getEnfrentamientosPorEquipo("G2");
            logger.info("Victorias de G2: " + victoriasG2.size());
            for (Enfrentamiento e : victoriasG2) {
                logger.info("  - " + e.getDescripcion() + " (" + e.getVideojuego() + ") - " + e.getFecha());
            }
            
            // Enfrentamientos por videojuego
            List<Enfrentamiento> enfrentamientosLOL = servicio.getEnfrentamientosPorVideojuego(Videojuego.LOL);
            logger.info("Enfrentamientos de LOL: " + enfrentamientosLOL.size());
            
            List<Enfrentamiento> enfrentamientosVALORANT = servicio.getEnfrentamientosPorVideojuego(Videojuego.VALORANT);
            logger.info("Enfrentamientos de VALORANT: " + enfrentamientosVALORANT.size());
            
            List<Enfrentamiento> enfrentamientosCSGO = servicio.getEnfrentamientosPorVideojuego(Videojuego.CSGO);
            logger.info("Enfrentamientos de CSGO: " + enfrentamientosCSGO.size());
            
            // Enfrentamientos por fecha
            List<Enfrentamiento> enfrentamientosFecha = servicio.getEnfrentamientosPorFecha("2025-11-01");
            logger.info("Enfrentamientos del 2025-11-01: " + enfrentamientosFecha.size());
            
            // Enfrentamientos por descripción
            List<Enfrentamiento> finales = servicio.getEnfrentamientosPorDescripcion("Final");
            logger.info("Finales del torneo: " + finales.size());
            for (Enfrentamiento e : finales) {
                logger.info("  - " + e.getDescripcion() + " - Ganador: " + 
                           (e.getEquipoGanador() != null ? e.getEquipoGanador().getCodigo() : "N/A"));
            }

            // ==================== ESTADÍSTICAS Y RANKINGS ====================
            
            logger.info("\n--- ESTADÍSTICAS Y RANKINGS ---");
            
            // Estadísticas de victorias por equipo
            Map<String, Integer> estadisticasVictorias = servicio.getEstadisticasVictoriasPorEquipo();
            logger.info("Estadísticas de victorias:");
            for (Map.Entry<String, Integer> entry : estadisticasVictorias.entrySet()) {
                logger.info("  - " + entry.getKey() + ": " + entry.getValue() + " victorias");
            }
            
            // Ranking completo de equipos
            logger.info("\nRanking de equipos por victorias:");
            List<Map.Entry<String, Integer>> ranking = servicio.getRankingEquiposPorVictorias();
            int posicion = 1;
            for (Map.Entry<String, Integer> entry : ranking) {
                logger.info("  " + posicion + ". " + entry.getKey() + " - " + entry.getValue() + " victorias");
                posicion++;
            }
            
            // Top 3 equipos
            logger.info("\nTop 3 equipos:");
            List<Map.Entry<String, Integer>> top3 = servicio.getTopNEquipos(3);
            posicion = 1;
            for (Map.Entry<String, Integer> entry : top3) {
                logger.info("  " + posicion + ". " + entry.getKey() + " - " + entry.getValue() + " victorias");
                posicion++;
            }
            
            // Equipo con más victorias
            String campeon = servicio.getEquipoConMasVictorias();
            logger.info("\nEquipo campeón: " + campeon);
            
            // Equipos sin victorias
            List<String> equiposSinVictorias = servicio.getEquiposSinVictorias();
            logger.info("Equipos sin victorias: " + equiposSinVictorias.size());
            for (String codigo : equiposSinVictorias) {
                logger.info("  - " + codigo);
            }
            
            // Estadísticas por videojuego
            Map<Videojuego, Integer> estadisticasVideojuego = servicio.getEstadisticasPorVideojuego();
            logger.info("\nEstadísticas por videojuego:");
            for (Map.Entry<Videojuego, Integer> entry : estadisticasVideojuego.entrySet()) {
                logger.info("  - " + entry.getKey() + ": " + entry.getValue() + " enfrentamientos");
            }
            
            // Videojuego con más enfrentamientos
            Videojuego videojuegoPopular = servicio.getVideojuegoConMasEnfrentamientos();
            logger.info("Videojuego más popular: " + videojuegoPopular);

            // ==================== INFORMACIÓN COMPLETA DE EQUIPOS ====================
            
            logger.info("\n--- INFORMACIÓN DETALLADA DE EQUIPOS ---");
            
            // Información completa de algunos equipos
            String[] equiposInteres = {"G2", "KOI", "FNC", "T1"};
            for (String codigo : equiposInteres) {
                try {
                    String info = servicio.getInformacionCompletaEquipo(codigo);
                    logger.info("\n" + info);
                } catch (TorneoException e) {
                    logger.error("Error al obtener información de " + codigo + ": " + e.getMessage());
                }
            }

            // ==================== VICTORIAS ESPECÍFICAS ====================
            
            logger.info("\n--- VICTORIAS POR VIDEOJUEGO ---");
            
            // Victorias de G2 por cada videojuego
            int victoriasG2LOL = servicio.getNumVictoriasEquipoPorVideojuego("G2", Videojuego.LOL);
            int victoriasG2VALORANT = servicio.getNumVictoriasEquipoPorVideojuego("G2", Videojuego.VALORANT);
            int victoriasG2CSGO = servicio.getNumVictoriasEquipoPorVideojuego("G2", Videojuego.CSGO);
            
            logger.info("Victorias de G2:");
            logger.info("  - LOL: " + victoriasG2LOL);
            logger.info("  - VALORANT: " + victoriasG2VALORANT);
            logger.info("  - CSGO: " + victoriasG2CSGO);

            // ==================== AGRUPACIONES ====================
            
            logger.info("\n--- ENFRENTAMIENTOS AGRUPADOS ---");
            
            // Agrupar por videojuego
            Map<Videojuego, List<Enfrentamiento>> agrupadosPorVideojuego = 
                servicio.getEnfrentamientosAgrupadosPorVideojuego();
            logger.info("Enfrentamientos agrupados por videojuego:");
            for (Map.Entry<Videojuego, List<Enfrentamiento>> entry : agrupadosPorVideojuego.entrySet()) {
                logger.info("  - " + entry.getKey() + ": " + entry.getValue().size() + " enfrentamientos");
            }
            
            // Agrupar por fecha
            Map<String, List<Enfrentamiento>> agrupadosPorFecha = 
                servicio.getEnfrentamientosAgrupadosPorFecha();
            logger.info("\nPrimeras 5 fechas con enfrentamientos:");
            int contador = 0;
            for (Map.Entry<String, List<Enfrentamiento>> entry : agrupadosPorFecha.entrySet()) {
                if (contador < 5) {
                    logger.info("  - " + entry.getKey() + ": " + entry.getValue().size() + " enfrentamientos");
                    contador++;
                }
            }

            // ==================== ESTADÍSTICAS GENERALES ====================
            
            logger.info("\n" + servicio.getEstadisticasGenerales());

            // ==================== ORDENAMIENTOS ====================
            
            logger.info("\n--- LISTADOS ORDENADOS ---");
            
            // Equipos ordenados por nombre
            List<Equipo> equiposPorNombre = servicio.getEquiposOrdenadosPorNombre();
            logger.info("Primeros 5 equipos ordenados alfabéticamente:");
            for (int i = 0; i < Math.min(5, equiposPorNombre.size()); i++) {
                logger.info("  - " + equiposPorNombre.get(i).getNombreCompleto());
            }
            
            // Enfrentamientos ordenados por fecha
            List<Enfrentamiento> enfrentamientosPorFecha = servicio.getEnfrentamientosOrdenadosPorFecha();
            logger.info("\nPrimeros 5 enfrentamientos cronológicamente:");
            for (int i = 0; i < Math.min(5, enfrentamientosPorFecha.size()); i++) {
                Enfrentamiento e = enfrentamientosPorFecha.get(i);
                logger.info("  - " + e.getFecha() + ": " + e.getDescripcion());
            }

            // ==================== EXPORTACIONES ADICIONALES ====================
            
            logger.info("\n--- EXPORTANDO DATOS FILTRADOS A JSON ---");
            
            // Exportar solo enfrentamientos de LOL
            conversorJson.escribeEnfrentamientosJson(enfrentamientosLOL, "enfrentamientos_lol.json");
            logger.info("Enfrentamientos de LOL exportados a enfrentamientos_lol.json");
            
            // Exportar equipos grandes
            conversorJson.escribeEquiposJson(equiposGrandes, "equipos_grandes.json");
            logger.info("Equipos grandes exportados a equipos_grandes.json");
            
            // Exportar finales
            conversorJson.escribeEnfrentamientosJson(finales, "finales.json");
            logger.info("Finales exportadas a finales.json");

            // ==================== EXPORTACIONES A CSV ====================
            
            logger.info("\n--- EXPORTANDO DATOS A CSV ---");
            
            CreacionCSV creadorCSV = new CreacionCSV();
            
            // Exportar equipos a CSV
            creadorCSV.escribeEquiposCSV(equipos, "equipos.csv");
            logger.info("Equipos exportados a CSV");
            
            // Exportar enfrentamientos a CSV
            creadorCSV.escribeEnfrentamientosCSV(enfrentamientos, "enfrentamientos.csv");
            logger.info("Enfrentamientos exportados a CSV");
            
            // Exportar enfrentamientos detallados (con info completa del equipo ganador)
            creadorCSV.escribeEnfrentamientosDetalladosCSV(enfrentamientos, "enfrentamientos_detallados.csv");
            logger.info("Enfrentamientos detallados exportados a CSV");
            
            // Exportar estadísticas de victorias a CSV
            creadorCSV.escribeEstadisticasVictoriasCSV(estadisticasVictorias, equipos, "estadisticas_victorias.csv");
            logger.info("Estadísticas de victorias exportadas a CSV");
            
            // Exportar ranking a CSV
            creadorCSV.escribeRankingCSV(ranking, equipos, "ranking_equipos.csv");
            logger.info("Ranking de equipos exportado a CSV");

            logger.info("\n========== GESTIÓN DE TORNEO FINALIZADA CORRECTAMENTE ==========");

        } catch (TorneoException e) {
            logger.error("Error del sistema: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}