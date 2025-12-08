package Examen.Controllers;


import java.util.HashSet;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Examen.Exception.TorneoException;
import Examen.Models.Enfrentamiento;
import Examen.Models.Equipo;
import Examen.Services.TorneoService;
import Examen.Utils.XMLDomEsports;




public class GestionaTorneo {
	private static final Logger logger = LogManager.getLogger(GestionaTorneo.class);

	    public static void main(String[] args) {

	        
	        XMLDomEsports lectorXML = new XMLDomEsports();

	        TorneoService servicio = new TorneoService();

	 

	        try {
	           
	            List<Equipo> equipos = lectorXML.leerEquipoDesdeXML("torneoGamer.xml");

	     
	            servicio.setListaEquipos(new HashSet<>(equipos));

	   
	            logger.info("Equipos cargados: " + servicio.getListaEquipos());
	            
	            List<Enfrentamiento> enfrentamientos = lectorXML.leerEnfrentamientoDesdeXML("torneoGamer.xml");

	   	     
	            servicio.setListaEnfrentamientos(new HashSet<>(enfrentamientos));

	   
	            logger.info("Enfrentamientos cargados: " + servicio.getListaEnfrentamientos());
	            
	           /* for (Enfrentamiento t : servicio.getEnfrentamientosPorEquipo("codigo")) {
	                logger.info(t);
	            }*/

	        } catch (TorneoException e) {
	            logger.error("Error del sistema: " + e.getMessage());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}


