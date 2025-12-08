package Services;

import java.util.List;

import com.mongodb.client.MongoDatabase;

import Models.Club;
import Models.Jugador;
import Repositories.CoachXRepository;

public class CoachXService {

    private final CoachXRepository repo;

    public CoachXService(MongoDatabase db) {
        this.repo = new CoachXRepository(db);
    }


    public void save(Club c) {
        repo.save(c);
    }

    public List<Club> read() {
        return repo.read();
    }

    public Club crearClub(Club c) {
        return repo.crearClub(c);
    }

    public Club buscarClub(int id) {
        return repo.buscarClub(id);
    }

    public void borrarClub(int id) {
        repo.borrarClub(id);
    }

    public void actualizarClub(Club c) {
        repo.actualizarClub(c);
    }


    public List<Jugador> getJugadoresPorPosicion(String posicion) {
        return repo.getJugadoresPorPosicion(posicion);
    }

    public List<Jugador> getJugadoresPorEstadoFisico(boolean estado) {
        return repo.getJugadoresPorEstadoFisico(estado);
    }


    public List<Jugador> ordenarJugadoresPorNombre() {
        return repo.ordenarJugadoresPorNombre();
    }

    public List<Jugador> ordenarJugadoresPorDorsal() {
        return repo.ordenarJugadoresPorDorsal();
    }

}
