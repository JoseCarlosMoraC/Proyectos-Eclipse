package Services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Exceptions.MiExcepcion;
import Models.Partida;
import Models.Resultado;
import Repositories.RepositorioPartida;

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

    public void actualizarPuntuacionNOAcertante (int idJugador, Resultado resultado) throws MiExcepcion {
        repo.actualizarPuntuacionNOAcertante(idJugador, resultado);
    }

    public void actualizarPuntuacionAcertante(int idJugador, Resultado resultado) {
        try {
            repo.actualizarPuntuacionAcertante(idJugador, resultado);
        } catch (MiExcepcion e) {
            e.printStackTrace();
        }
    }

    public List<Partida> mostrarPartidas() throws MiExcepcion{
        return repo.mostrarPartidas();
    }
}
