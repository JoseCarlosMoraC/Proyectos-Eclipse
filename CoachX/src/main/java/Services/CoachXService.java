package Services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import com.mongodb.client.MongoDatabase;

import Models.Club;
import Models.Equipo;
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
    	
	    List<Jugador> jugadores = new ArrayList<>();

	    Iterator<Club> itClub = repo.getListaClub().iterator();
	    while (itClub.hasNext()) {
	        Club c = itClub.next();

	        Iterator<Equipo> itEquipo = c.getEquipos().iterator();
	        while (itEquipo.hasNext()) {
	            Equipo e = itEquipo.next();

	            Iterator<Jugador> itJugador = e.getJugadores().iterator();
	            while (itJugador.hasNext()) {
	                Jugador j = itJugador.next();

	   
	                if (j.isEstadoFisico() == estado) {
	                    jugadores.add(j);
	                }
	            }
	        }
	    }

	    return jugadores;
	}


    public List<Jugador> ordenarJugadoresPorNombre() {
        return repo.ordenarJugadoresPorNombre();
    }

    public List<Jugador> ordenarJugadoresPorDorsal() {
	    List<Jugador> jugadores = new ArrayList<>();


	    Iterator<Club> itClub = repo.getListaClub().iterator();
	    while (itClub.hasNext()) {
	        Club c = itClub.next();

	        Iterator<Equipo> itEquipo = c.getEquipos().iterator();
	        while (itEquipo.hasNext()) {
	            Equipo e = itEquipo.next();

	            Iterator<Jugador> itJugador = e.getJugadores().iterator();
	            while (itJugador.hasNext()) {
	                jugadores.add(itJugador.next());
	            }
	        }
	    }


	    Collections.sort(jugadores, new Comparator<Jugador>() {
	        @Override
	        public int compare(Jugador j1, Jugador j2) {
	            return Integer.compare(j1.getDorsal(), j2.getDorsal());
	        }
	    });

	    return jugadores;
	}

}
