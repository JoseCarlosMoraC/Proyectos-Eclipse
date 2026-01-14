package Controller;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Exception.LiveFestException;
import Models.Banda;
import Models.Concierto;
import Models.GeneroMusical;
import Services.LiveFestService;
import Utils.CreacionCSV;
import Utils.LiveFestAJson;
import Utils.XMLDomLiveFest;



public class GestionaLiveFest {
    private static final Logger logger = LogManager.getLogger(GestionaLiveFest.class);

    public static void main(String[] args) {

        XMLDomLiveFest lectorXML = new XMLDomLiveFest();
        LiveFestService servicio = new LiveFestService();
        LiveFestAJson conversorJson = new LiveFestAJson();

        try {
            logger.info("========== INICIANDO GESTIÓN DE LIVEFEST ==========");

            // ==================== APARTADO 2: CARGA DE DATOS DESDE XML ====================
            
            logger.info("\n--- APARTADO 2: CARGA DE DATOS DESDE XML ---");
            
            // Usar los métodos leerXmlBandas y leerXmlConciertos
            List<Banda> bandas = conversorJson.leerXmlBandas("livefest.xml");
            List<Concierto> conciertos = conversorJson.leerXmlConciertos("livefest.xml");
            
            // Agregar usando los métodos agregarListaBandas y agregarListaConciertos
            servicio.setListaBandas(new HashSet<>(bandas));
            servicio.setListaConciertos(new HashSet<>(conciertos));
            
            logger.info("Bandas cargadas: " + servicio.getListaBandas().size());
            logger.info("Conciertos cargados: " + servicio.getListaConciertos().size());

            // ==================== APARTADO 3: GENERACIÓN DE JSON ====================
            
            logger.info("\n--- APARTADO 3: GENERACIÓN DE JSON ---");
            
            // Llamada a getConciertosPorBanda
            try {
                String codigoBanda = "MCR"; // Código de la banda a buscar
                List<Concierto> conciertosBanda = servicio.getConciertosPorBanda(codigoBanda);
                
                logger.info("Conciertos de " + codigoBanda + ": " + conciertosBanda.size());
                
                // Llamada a generaJson en el paquete utils
                conversorJson.generaJson(conciertosBanda, "conciertos_" + codigoBanda + ".json");
                logger.info("JSON generado correctamente para conciertos de " + codigoBanda);
                
            } catch (LiveFestException e) {
                logger.error("Error al obtener conciertos: " + e.getMessage());
            }

            // ==================== CONVERSIÓN A JSON ====================
            
            logger.info("\n--- CONVIRTIENDO DATOS A JSON ---");
            
            conversorJson.escribeBandasJson(bandas, "bandas.json");
            conversorJson.escribeConciertosJson(conciertos, "conciertos.json");

            // ==================== CONSULTAS BÁSICAS DE BANDAS ====================
            
            logger.info("\n--- CONSULTAS DE BANDAS ---");
            
            // Obtener una banda específica
            Banda bandaMCR = servicio.getBanda("MCR");
            logger.info("Banda MCR: " + bandaMCR);
            
            // Bandas por país
            List<Banda> bandasUSA = servicio.getBandasPorPais("USA");
            logger.info("Bandas de USA: " + bandasUSA.size());
            for (Banda b : bandasUSA) {
                logger.info("  - " + b.getNombre() + " (" + b.getNumIntegrantes() + " integrantes)");
            }
            
            // Bandas con muchos integrantes
            List<Banda> bandasGrandes = servicio.getBandasPorNumIntegrantes(4);
            logger.info("Bandas con 4+ integrantes: " + bandasGrandes.size());

            // ==================== CONSULTAS DE CONCIERTOS ====================
            
            logger.info("\n--- CONSULTAS DE CONCIERTOS ---");
            
            // Conciertos por género
            List<Concierto> conciertosRock = servicio.getConciertosPorGenero(GeneroMusical.ROCK);
            logger.info("Conciertos de ROCK: " + conciertosRock.size());
            
            List<Concierto> conciertosElectronica = servicio.getConciertosPorGenero(GeneroMusical.ELECTRONICA);
            logger.info("Conciertos de ELECTRONICA: " + conciertosElectronica.size());
            
            // Conciertos por fecha
            List<Concierto> conciertosFecha = servicio.getConciertosPorFecha("2025-06-15");
            logger.info("Conciertos del 2025-06-15: " + conciertosFecha.size());
            
            // Conciertos por escenario
            List<Concierto> conciertosMainStage = servicio.getConciertosPorEscenario("Main Stage");
            logger.info("Conciertos en Main Stage: " + conciertosMainStage.size());

            // ==================== ESTADÍSTICAS Y RANKINGS ====================
            
            logger.info("\n--- ESTADÍSTICAS Y RANKINGS ---");
            
            // Estadísticas de conciertos por banda
            Map<String, Integer> estadisticasConciertos = servicio.getEstadisticasConciertosPorBanda();
            logger.info("Estadísticas de conciertos por banda:");
            for (Map.Entry<String, Integer> entry : estadisticasConciertos.entrySet()) {
                logger.info("  - " + entry.getKey() + ": " + entry.getValue() + " conciertos");
            }
            
            // Ranking completo de bandas
            logger.info("\nRanking de bandas por conciertos:");
            List<Map.Entry<String, Integer>> ranking = servicio.getRankingBandasPorConciertos();
            int posicion = 1;
            for (Map.Entry<String, Integer> entry : ranking) {
                logger.info("  " + posicion + ". " + entry.getKey() + " - " + entry.getValue() + " conciertos");
                posicion++;
            }
            
            // Banda con más conciertos
            String bandaTop = servicio.getBandaConMasConciertos();
            logger.info("\nBanda con más conciertos: " + bandaTop);
            
            // Estadísticas por género
            Map<GeneroMusical, Integer> estadisticasGenero = servicio.getEstadisticasPorGenero();
            logger.info("\nEstadísticas por género:");
            for (Map.Entry<GeneroMusical, Integer> entry : estadisticasGenero.entrySet()) {
                logger.info("  - " + entry.getKey() + ": " + entry.getValue() + " conciertos");
            }

            // ==================== INFORMACIÓN COMPLETA DE BANDAS ====================
            
            logger.info("\n--- INFORMACIÓN DETALLADA DE BANDAS ---");
            
            String[] bandasInteres = {"MCR", "MUSE", "DAFT"};
            for (String codigo : bandasInteres) {
                try {
                    String info = servicio.getInformacionCompletaBanda(codigo);
                    logger.info("\n" + info);
                } catch (LiveFestException e) {
                    logger.error("Error al obtener información de " + codigo + ": " + e.getMessage());
                }
            }

            // ==================== ESTADÍSTICAS GENERALES ====================
            
            logger.info("\n" + servicio.getEstadisticasGenerales());

            // ==================== EXPORTACIONES A CSV ====================
            
            logger.info("\n--- EXPORTANDO DATOS A CSV ---");
            
            CreacionCSV creadorCSV = new CreacionCSV();
            
            // Exportar bandas a CSV
            creadorCSV.escribeBandasCSV(bandas, "bandas.csv");
            logger.info("Bandas exportadas a CSV");
            
            // Exportar conciertos a CSV
            creadorCSV.escribeConciertosCSV(conciertos, "conciertos.csv");
            logger.info("Conciertos exportados a CSV");
            
            // Exportar conciertos detallados
            creadorCSV.escribeConciertosDetalladosCSV(conciertos, "conciertos_detallados.csv");
            logger.info("Conciertos detallados exportados a CSV");
            
            // Exportar ranking a CSV
            creadorCSV.escribeRankingCSV(ranking, bandas, "ranking_bandas.csv");
            logger.info("Ranking de bandas exportado a CSV");

            logger.info("\n========== GESTIÓN DE LIVEFEST FINALIZADA CORRECTAMENTE ==========");

        } catch (LiveFestException e) {
            logger.error("Error del sistema: " + e.getMessage());
        } catch (Exception e) {
            logger.error("Error inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }
}