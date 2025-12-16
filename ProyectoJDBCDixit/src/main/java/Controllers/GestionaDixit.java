package Controllers;

import java.time.LocalDate;

import Exceptions.MiExcepcion;
import Models.Jugador;
import Models.Partida;
import Models.Resultado;
import Services.ServicioJugador;
import Services.ServicioPartida;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GestionaDixit {

    private static final Logger logger = LogManager.getLogger(GestionaDixit.class);

    public static void main(String[] args) {

        try {
            ServicioJugador servicioJugador = new ServicioJugador();
            ServicioPartida servicioPartida = new ServicioPartida();

            Jugador j1 = new Jugador(0, "Ana", "ana@mail.com",15);
            Jugador j2 = new Jugador(0, "Luis", "luis@mail.com", 12);
            Jugador j3 = new Jugador(0, "Marta", "marta@mail.com", 7);
            Jugador j4 = new Jugador(0, "Pablo", "pablo@mail.com", 3);

            servicioJugador.addJugador(j1);
            servicioJugador.addJugador(j2);
            servicioJugador.addJugador(j3);
            servicioJugador.addJugador(j4);

      
            Partida[] partidas = {
                new Partida(0, 1, j1, LocalDate.now(), Resultado.ALGUNOS),
                new Partida(0, 1, j2, LocalDate.now(), Resultado.TODOS),
                new Partida(0, 1, j3, LocalDate.now(), Resultado.NADIE),
                new Partida(0, 1, j4, LocalDate.now(), Resultado.ALGUNOS),
                new Partida(0, 1, j1, LocalDate.now(), Resultado.TODOS),
                new Partida(0, 1, j2, LocalDate.now(), Resultado.NADIE) 
            };

            for (Partida p : partidas) {
                try {
                    servicioPartida.addPartida(p);
                    logger.info("Partida añadida: " + p);
                } catch (MiExcepcion e) {
                    logger.error("EXCEPCIÓN CONTROLADA: " + e.getMessage());
                }
            }


            logger.info("Jugador con mayor puntuación: " + servicioJugador.mostrarJugadorMayorPuntuacion());

      
            logger.info("Puntuaciones de todos los jugadores: " + servicioJugador.mostrarPuntuaciones());

  
            logger.info("Partidas ordenadas por fecha: " + servicioPartida.mostrarPartidas());

        } catch (MiExcepcion e) {
            logger.error("ERROR INICIAL: " + e.getMessage());
        }
    }
}
