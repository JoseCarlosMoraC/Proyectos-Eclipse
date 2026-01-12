package Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Exceptions.MiExcepcion;
import Models.Partida;
import Models.Resultado;
import Repositories.RepositorioPartida;

/*
 * ServicioPartida
 * ----------------
 * Clase de servicio que actúa como intermediario entre el controlador y el repositorio de partidas.
 * Encapsula la lógica de negocio relacionada con las partidas y las puntuaciones de los jugadores.
 */
public class ServicioPartida {

    private static final Logger logger = LogManager.getLogger(ServicioPartida.class);
    private RepositorioPartida repo;

    public ServicioPartida() throws MiExcepcion {
        super();
        this.repo = new RepositorioPartida();
    }

    public RepositorioPartida getRepoPartida() {
        return repo;
    }

    public void setRepoPartida(RepositorioPartida repoPartida) {
        this.repo = repoPartida;
    }

    public void addPartida(Partida partida) throws MiExcepcion {
        repo.agregarPartido(partida);
    }

    public void actualizarPuntuacionNarrador(int idJugador, Resultado resultado) {
        try {
            repo.actualizarPuntuacionNarrador(idJugador, resultado);
        } catch (MiExcepcion e) {
            e.printStackTrace();
        }
    }

    public void actualizarPuntuacionNOAcertante(int idJugador, Resultado resultado) throws MiExcepcion {
        repo.actualizarPuntuacionNOAcertante(idJugador, resultado);
    }

    public void actualizarPuntuacionAcertante(int idJugador, Resultado resultado) {
        try {
            repo.actualizarPuntuacionAcertante(idJugador, resultado);
        } catch (MiExcepcion e) {
            e.printStackTrace();
        }
    }

    public List<Partida> mostrarPartidas() throws MiExcepcion {
        return repo.mostrarPartidas();
    }

    // -------------------------
    // MÉTODOS ADICIONALES PARA EXÁMENES
    // -------------------------

    // Buscar partidas por ID de partida
    public List<Partida> buscarPorId(int id) {
        List<Partida> lista = new ArrayList<>();
        for(Partida p : repo.getListaPartidas()) {
            if(p.getId() == id) {
                lista.add(p);
            }
        }
        return lista;
    }

    // Buscar partidas por ID de torneo
    public List<Partida> buscarPorTorneoId(int torneoId) {
        List<Partida> lista = new ArrayList<>();
        for(Partida p : repo.getListaPartidas()) {
            if(p.getTorneoId() == torneoId) {
                lista.add(p);
            }
        }
        return lista;
    }

    // Buscar partidas por ID de narrador
    public List<Partida> buscarPorNarradorId(int narradorId) {
        List<Partida> lista = new ArrayList<>();
        for(Partida p : repo.getListaPartidas()) {
            if(p.getNarradorId().getId() == narradorId) {
                lista.add(p);
            }
        }
        return lista;
    }

    // Buscar partidas por fecha exacta
    public List<Partida> buscarPorFecha(LocalDate fecha) {
        List<Partida> lista = new ArrayList<>();
        for(Partida p : repo.getListaPartidas()) {
            if(p.getFecha() != null && p.getFecha().equals(fecha)) {
                lista.add(p);
            }
        }
        return lista;
    }

    // Buscar partidas por rango de fechas
    public List<Partida> buscarPorRangoFecha(LocalDate inicio, LocalDate fin) {
        List<Partida> lista = new ArrayList<>();
        for(Partida p : repo.getListaPartidas()) {
            if(p.getFecha() != null && (p.getFecha().isEqual(inicio) || p.getFecha().isAfter(inicio)) 
               && (p.getFecha().isEqual(fin) || p.getFecha().isBefore(fin))) {
                lista.add(p);
            }
        }
        return lista;
    }

    // Buscar partidas por resultado
    public List<Partida> buscarPorResultado(Resultado resultado) {
        List<Partida> lista = new ArrayList<>();
        for(Partida p : repo.getListaPartidas()) {
            if(p.getResultado() == resultado) {
                lista.add(p);
            }
        }
        return lista;
    }

    // Obtener partidas sin resultado asignado (resultado null)
    public List<Partida> partidasSinResultado() {
        List<Partida> lista = new ArrayList<>();
        for(Partida p : repo.getListaPartidas()) {
            if(p.getResultado() == null) {
                lista.add(p);
            }
        }
        return lista;
    }

    // Obtener partidas con resultado distinto de un tipo específico
    public List<Partida> partidasConResultadoDiferente(Resultado resultado) {
        List<Partida> lista = new ArrayList<>();
        for(Partida p : repo.getListaPartidas()) {
            if(p.getResultado() != null && p.getResultado() != resultado) {
                lista.add(p);
            }
        }
        return lista;
    }

    // Obtener partidas por rango de IDs
    public List<Partida> partidasEntreIds(int idInicio, int idFin) {
        List<Partida> lista = new ArrayList<>();
        for(Partida p : repo.getListaPartidas()) {
            if(p.getId() >= idInicio && p.getId() <= idFin) {
                lista.add(p);
            }
        }
        return lista;
    }
}
