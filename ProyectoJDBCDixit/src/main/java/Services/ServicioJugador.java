package Services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Exceptions.MiExcepcion;
import Models.Jugador;
import Repositories.RepositorioJugadores;

public class ServicioJugador {

    private static final Logger logger = LogManager.getLogger(ServicioJugador.class);
    private RepositorioJugadores repo;

    public ServicioJugador() throws MiExcepcion {
        super();
        this.repo = new RepositorioJugadores();
    }

    public RepositorioJugadores getRepoJugador() {
        return repo;
    }

    public void setRepoJugador(RepositorioJugadores repoJugador) {
        this.repo = repoJugador;
    }

    public void addJugador(Jugador jugador) {
        try {
            repo.agregarJugador(jugador);
        } catch (MiExcepcion e) {
            logger.error("No se ha podido añadir el jugador: "+ e.getMessage());
        }
    }

    public Jugador mostrarJugadorMayorPuntuacion() {
        Jugador j = new Jugador();
        try {
            j = repo.mostrarJugadorConMayorPuntuacion();
        } catch (MiExcepcion e) {
            logger.error("No se ha encontrado jugador con mayor puntuación: "+ e.getMessage());
        }
        return j;
    }

    public List<Jugador> mostrarPuntuaciones(){
        List<Jugador> listaPuntos = new ArrayList<>();
        try {
            listaPuntos = repo.mostrarPuntuaciones();
        } catch (MiExcepcion e) {
            logger.error("Error al obtener puntuaciones: " + e.getMessage());
        }
        return listaPuntos;
    }

    // -------------------------
    // MÉTODOS ADICIONALES PARA EXÁMENES
    // -------------------------

    // Buscar jugador por ID (sin break)
    public List<Jugador> buscarJugadorPorId(int id) {
        List<Jugador> lista = new ArrayList<>();
        for(Jugador j : repo.getListaJugadores()) {
            if(j.getId() == id) {
                lista.add(j);
            }
        }
        return lista;
    }

    // Buscar jugador por nombre
    public List<Jugador> buscarJugadorPorNombre(String nombre) {
        List<Jugador> lista = new ArrayList<>();
        for(Jugador j : repo.getListaJugadores()) {
            if(j.getNombre().equalsIgnoreCase(nombre)) {
                lista.add(j);
            }
        }
        return lista;
    }

    // Buscar jugador por email
    public List<Jugador> buscarJugadorPorEmail(String email) {
        List<Jugador> lista = new ArrayList<>();
        for(Jugador j : repo.getListaJugadores()) {
            if(j.getEmail().equalsIgnoreCase(email)) {
                lista.add(j);
            }
        }
        return lista;
    }

    // Obtener jugadores con puntuación mayor a un valor
    public List<Jugador> jugadoresConPuntosMayorA(int puntos) {
        List<Jugador> lista = new ArrayList<>();
        for(Jugador j : repo.getListaJugadores()) {
            if(j.getPuntosTotales() > puntos) {
                lista.add(j);
            }
        }
        return lista;
    }

    // Obtener jugadores con puntuación menor a un valor
    public List<Jugador> jugadoresConPuntosMenorA(int puntos) {
        List<Jugador> lista = new ArrayList<>();
        for(Jugador j : repo.getListaJugadores()) {
            if(j.getPuntosTotales() < puntos) {
                lista.add(j);
            }
        }
        return lista;
    }

    // Obtener jugadores con puntuación entre dos valores
    public List<Jugador> jugadoresConPuntosEntre(int min, int max) {
        List<Jugador> lista = new ArrayList<>();
        for(Jugador j : repo.getListaJugadores()) {
            if(j.getPuntosTotales() >= min && j.getPuntosTotales() <= max) {
                lista.add(j);
            }
        }
        return lista;
    }
}
