package Controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Exception.GrupoMusicaException;
import Models.Concierto;
import Models.Grupo;
import Services.LiveFestService;
import Utils.CreacionCSV;
import Utils.XMLDomLiveFest;

public class GestionaLiveFest {
	private static final Logger logger = LogManager.getLogger(GestionaLiveFest.class);

	    public static void main(String[] args) {

	        
	        XMLDomLiveFest lectorXML = new XMLDomLiveFest();

	        LiveFestService servicio = new LiveFestService();

	 

	        try {
	            List<Concierto> c = lectorXML.leerConciertoDesdeXML("conciertosFestival.xml");
	            servicio.getRegistroFestival().setListaConciertos(new HashSet<>(c));
	            
	            for (Concierto concierto : c) {
	                for (Grupo grupo : concierto.getGrupo()) {
	                    try {
	                        servicio.agregarGrupo(grupo);
	                        logger.info("Grupo agregado: " + grupo.getNombre());
	                    } catch (GrupoMusicaException e) {
	                        logger.debug("Grupo duplicado: " + grupo.getNombre());
	                    }
	                }
	            }
	            
	            logger.info("Total de conciertos: " + c.size());
	            logger.info("Total de grupos: " + servicio.getRegistroFestival().getListaGrupos().size());
	            
	          
	            List<Grupo> gruposEncontrados = servicio.getGruposPorNombre("vetusta morla");
	            logger.info("Grupos encontrados: " + gruposEncontrados);
	            
	            servicio.getConciertoPorNombreGrupo("vetusta morla");
	            
	            CreacionCSV creador = new CreacionCSV();
	            creador.escribeCSV(c, "src/main/resources/festivalConcierto.csv");
	            logger.info("CSV generado correctamente en festivalConcierto.csv");
	            
	        } catch (GrupoMusicaException e) {
	            logger.error("Error del sistema: " + e.getMessage());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
}