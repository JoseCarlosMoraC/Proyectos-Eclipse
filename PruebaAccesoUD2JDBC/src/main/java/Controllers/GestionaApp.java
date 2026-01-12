package Controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Exceptions.MiExcepcion;
import Models.PlanActivo;
import Models.Usuario;
import Services.ServicioApp;



public class GestionaApp {
	 private static final Logger logger = LogManager.getLogger(GestionaApp.class);

	    public static void main(String[] args) throws MiExcepcion {

	        ServicioApp servicioApp = new ServicioApp();
	        List<Usuario> buscarUsuario = servicioApp.buscarJugadorPorId("usr002");
            logger.info("Usuario: " + buscarUsuario);
            List<Usuario> plan = servicioApp.buscarPorPlan(PlanActivo.MENSUAL);
            logger.info("Planes: " + plan);
	    }
	    
	}
