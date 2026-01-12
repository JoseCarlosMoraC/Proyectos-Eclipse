package Controllers;

import java.time.LocalDate;
import java.util.List;

import Exceptions.MiExcepcion;
import Models.Jugador;
import Models.Partida;
import Models.Resultado;
import Services.ServicioJugador;
import Services.ServicioPartida;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/*
 * GestionaDixit
 * --------------
 * Clase principal que actúa como controlador de la aplicación.
 * Crea jugadores y partidas, las añade a la base de datos y muestra resultados.
 */
public class GestionaDixit {

    private static final Logger logger = LogManager.getLogger(GestionaDixit.class);

    public static void main(String[] args) {

        try {
            // Inicializar servicios
            ServicioJugador servicioJugador = new ServicioJugador();
            ServicioPartida servicioPartida = new ServicioPartida();

            // Crear jugadores
            Jugador j1 = new Jugador(0, "Ana", "ana@mail.com",15);
            Jugador j2 = new Jugador(0, "Luis", "luis@mail.com", 12);
            Jugador j3 = new Jugador(0, "Marta", "marta@mail.com", 7);
            Jugador j4 = new Jugador(0, "Pablo", "pablo@mail.com", 3);

            // Añadir jugadores
            servicioJugador.addJugador(j1);
            servicioJugador.addJugador(j2);
            servicioJugador.addJugador(j3);
            servicioJugador.addJugador(j4);

            // Crear partidas
            Partida[] partidas = {
                new Partida(0, 1, j1, LocalDate.now(), Resultado.ALGUNOS),
                new Partida(0, 1, j2, LocalDate.now(), Resultado.TODOS),
                new Partida(0, 1, j3, LocalDate.now(), Resultado.NADIE),
                new Partida(0, 1, j4, LocalDate.now(), Resultado.ALGUNOS),
                new Partida(0, 1, j1, LocalDate.now(), Resultado.TODOS),
                new Partida(0, 1, j2, LocalDate.now(), Resultado.NADIE) 
            };

            // Añadir partidas y manejar excepciones
            for (Partida p : partidas) {
                try {
                    servicioPartida.addPartida(p);
                    logger.info("Partida añadida: " + p);
                } catch (MiExcepcion e) {
                    logger.error("EXCEPCIÓN CONTROLADA: " + e.getMessage());
                }
            }

            // Mostrar jugador con mayor puntuación
            logger.info("Jugador con mayor puntuación: " + servicioJugador.mostrarJugadorMayorPuntuacion());

            // Mostrar puntuaciones de todos los jugadores
            logger.info("Puntuaciones de todos los jugadores: " + servicioJugador.mostrarPuntuaciones());

            // Mostrar todas las partidas ordenadas por fecha
            List<Partida> todasPartidas = servicioPartida.mostrarPartidas();
            logger.info("Partidas ordenadas por fecha: " + todasPartidas);

            // -------------------------
            // CONSULTAS ADICIONALES PARA EXÁMEN
            // -------------------------

            // Partidas por ID de torneo
            List<Partida> torneo1 = servicioPartida.buscarPorTorneoId(1);
            logger.info("Partidas del torneo 1: " + torneo1);

            // Partidas por narrador
            List<Partida> narradorJ1 = servicioPartida.buscarPorNarradorId(j1.getId());
            logger.info("Partidas con narrador Ana: " + narradorJ1);

            // Partidas por resultado
            List<Partida> todosAcertaron = servicioPartida.buscarPorResultado(Resultado.TODOS);
            logger.info("Partidas donde todos acertaron: " + todosAcertaron);

            // Partidas por rango de fechas
            LocalDate inicio = LocalDate.now().minusDays(1);
            LocalDate fin = LocalDate.now().plusDays(1);
            List<Partida> rangoFechas = servicioPartida.buscarPorRangoFecha(inicio, fin);
            logger.info("Partidas en rango de fechas: " + rangoFechas);

            // Partidas sin resultado
            List<Partida> sinResultado = servicioPartida.partidasSinResultado();
            logger.info("Partidas sin resultado: " + sinResultado);

            // Partidas por rango de IDs
            List<Partida> rangoIds = servicioPartida.partidasEntreIds(1, 3);
            logger.info("Partidas con ID entre 1 y 3: " + rangoIds);

        } catch (MiExcepcion e) {
            logger.error("ERROR INICIAL: " + e.getMessage());
        }
    }
}
