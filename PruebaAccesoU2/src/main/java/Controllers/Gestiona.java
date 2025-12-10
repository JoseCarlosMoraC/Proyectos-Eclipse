package Controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mongodb.client.MongoDatabase;

import Config.MongoDBConexion;
import Models.Usuario;
import Service.AppService;


public class Gestiona {
	private static final Logger logger = LogManager.getLogger(Gestiona.class);

    public static void main(String[] args) {
    	  MongoDBConexion conexion = new MongoDBConexion();
          MongoDatabase db = conexion.getDb();
          AppService appService = new AppService(db);
          
          logger.info("Inicio");
          logger.info("Listado de todos los usuarios");
          List<Usuario> todosUsuario = appService.read();
          if (todosUsuario.isEmpty()) {
              logger.warn("No se encontraron usuarios");
          } else {
              logger.info("Total de usuario encontrados: " + todosUsuario.size());
              for (Usuario u : todosUsuario) {
                  logger.info(u.getId() + " - " + u.getUsername());
              }
          }
          logger.info("Ordenar");
          List<Usuario> usuariosOrdenados = appService.ordenDescendenteYAscendente(); 
          logger.info("Orden: " + usuariosOrdenados);
          
          logger.info("Crear");
          // Usuario creaUsuario = appService.crearUsuario(null);
          
          logger.info("Buscar Usuario");
          Usuario prueba = appService.getUsuarioPorId("usr002");
          if (prueba != null) {
              logger.info("Usuario encontrado: " + prueba.getId() + " - " + prueba.getUsername() + " - " + prueba.getEmail());
          } else {
              logger.warn("Usuario con ID usr002 no encontrado.");
          }

          appService.actualizarAESP();
          logger.info("Actualizado");

          prueba = appService.getUsuarioPorId("usr02");
          
          if (prueba != null) {
              logger.info("Actualizado" + prueba.getId() + ": " +
                          prueba.getPreferencias().getIdioma());
          } else {
              logger.error("No se pudo actualizar");
          }
         // appService.eliminarPlanActivo();;
          logger.info("Elimnado");
          logger.info("Agregar Evento a Usuario");
          appService.agregarUsuario("usr008");
          logger.info("Evento añadido");
          logger.info("Fin");
    }
    
}
