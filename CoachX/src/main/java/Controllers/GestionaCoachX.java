package Controllers;

import java.time.LocalDate;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.client.MongoDatabase;

import Config.MongoDBConexion;
import Models.Club;
import Models.Equipo;
import Models.Estadistica;
import Models.Jugador;
import Models.LocalDateAdapter;
import Services.CoachXService;



public class GestionaCoachX {
	private static final Logger logger = LogManager.getLogger(GestionaCoachX.class);
    public static void main(String[] args) {
        MongoDBConexion conexion = new MongoDBConexion();
        MongoDatabase db = conexion.getDb();

        CoachXService service = new CoachXService(db);

        Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .create();

        Estadistica stats = new Estadistica(1, 5, 2, 300, 0, 7.5);
        Jugador jugador1 = new Jugador(101, "Juan Pérez", 10, LocalDate.of(2010, 5, 12), "delantero", true, stats);
        Jugador jugador2 = new Jugador(102, "Ana López", 8, LocalDate.of(2011, 8, 23), "portero", true, stats);

        Equipo equipo = new Equipo(201, "Prebenjamin A", "prebenjamin", List.of(jugador1, jugador2), null, null);

        Club club = new Club(1, "Club Deportivo Municipal Gerena", "escudo.png", "contacto@cdmgerena.com", List.of(equipo));

        service.save(club);
        System.out.println("Club guardado: " + club.getNombre());

        List<Club> clubes = service.read();
        System.out.println("Clubes en la base de datos:");
        String jsonClubes = gson.toJson(clubes);
        System.out.println(jsonClubes); 

        Club buscado = service.buscarClub(1);
        if (buscado != null) {
            logger.info("Club encontrado: " + buscado.getNombre());
            logger.info("Detalles del club encontrado: " + gson.toJson(buscado));
        }

        club.setContacto("CDMGerena@gmail.com");
        service.actualizarClub(club);
        logger.info("Club actualizado: " + club.getContacto());

        List<Jugador> delanteros = service.getJugadoresPorPosicion("delantero");
        System.out.println("Jugadores delanteros:");
        for (Jugador j : delanteros) {
        	logger.info(" - " + j.getNombre());
        }

        List<Jugador> estadoFisico = service.getJugadoresPorEstadoFisico(true);
        System.out.println("Jugadores en buen estado físico:");
        for (Jugador j : estadoFisico) {
        	logger.info(" - " + j.getNombre());
        }

        List<Jugador> ordenNombre = service.ordenarJugadoresPorNombre();
        System.out.println("Jugadores ordenados por nombre:");
        for (Jugador j : ordenNombre) {
        	logger.info(" - " + j.getNombre());
        }

        List<Jugador> ordenDorsal = service.ordenarJugadoresPorDorsal();
        System.out.println("Jugadores ordenados por dorsal:");
        for (Jugador j : ordenDorsal) {
        	logger.info(" - " + j.getNombre() + " (dorsal " + j.getDorsal() + ")");
        }

        service.borrarClub(1);
        logger.info("Club borrado con ID 1");
    }
}
