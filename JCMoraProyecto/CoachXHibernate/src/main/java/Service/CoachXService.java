package Service;

import java.util.List;

import Models.Club;
import Models.Entrenador;
import Models.Entrenamiento;
import Models.Equipo;
import Models.Jugador;
import Models.Partido;
import Repositories.ClubDao;
import Repositories.EntrenadorDao;
import Repositories.EntrenamientoDao;
import Repositories.EquipoDao;
import Repositories.JugadorDao;
import Repositories.PartidoDao;


public class CoachXService {

    private ClubDao clubDao;
    private EquipoDao equipoDao;
    private JugadorDao jugadorDao;
    private EntrenadorDao entrenadorDao;
    private EntrenamientoDao entrenamientoDao;
    private PartidoDao partidoDao;

    public CoachXService() {
        this.clubDao = new ClubDao();
        this.equipoDao = new EquipoDao();
        this.jugadorDao = new JugadorDao();
        this.entrenadorDao = new EntrenadorDao();
        this.entrenamientoDao = new EntrenamientoDao();
        this.partidoDao = new PartidoDao();
    }


    
    public void crearClub(Club club) {
        clubDao.create(club);
    }

    public Club obtenerClub(int id) {
        return clubDao.get(id);
    }

    public List<Club> obtenerTodosClubes() {
        return clubDao.getAll();
    }

    public void actualizarClub(Club club) {
        clubDao.update(club);
    }

    public void eliminarClub(Club club) {
        clubDao.delete(club);
    }

    public Club getPrimerClub() {
        return clubDao.getPrimerClub();
    }

    public List<String> getNombresClub() {
        return clubDao.getNombresClub();
    }

    public List<Object[]> getNombreYContactoClub() {
        return clubDao.getNombreYContacto();
    }

    public Club buscarClubPorNombre(String nombre) {
        return clubDao.findByNombre(nombre);
    }

    public List<Club> buscarClubesQueContengan(String texto) {
        return clubDao.findByNombreContiene(texto);
    }

    public Long contarClubes() {
        return clubDao.contarClubes();
    }

    public List<Club> getClubesOrdenados() {
        return clubDao.getClubesOrdenadosPorNombre();
    }

    public int actualizarContactoClub(Long clubId, String nuevoContacto) {
        return clubDao.actualizarContacto(clubId, nuevoContacto);
    }

    public int eliminarClubesSinEquipos() {
        return clubDao.eliminarClubesSinEquipos();
    }

    
    public void crearEquipo(Equipo equipo) {
        equipoDao.create(equipo);
    }

    public Equipo obtenerEquipo(int id) {
        return equipoDao.get(id);
    }

    public List<Equipo> obtenerTodosEquipos() {
        return equipoDao.getAll();
    }

    public void actualizarEquipo(Equipo equipo) {
        equipoDao.update(equipo);
    }

    public void eliminarEquipo(Equipo equipo) {
        equipoDao.delete(equipo);
    }


    public List<Equipo> buscarEquiposPorCategoria(String categoria) {
        return equipoDao.findByCategoria(categoria);
    }

    public Double getPromedioJugadoresPorEquipo() {
        return equipoDao.getPromedioJugadoresPorEquipo();
    }

    public List<Equipo> getEquiposOrdenados() {
        return equipoDao.getEquiposOrdenadosPorNombreYCategoria();
    }

    public int actualizarTemporadaEquipo(Long equipoId, String temporada) {
        return equipoDao.actualizarTemporada(equipoId, temporada);
    }

    public List<String> getNombresEquiposPorCategoria(String categoria) {
        return equipoDao.getNombresEquiposPorCategoria(categoria);
    }


    
    public void crearJugador(Jugador jugador) {
        jugadorDao.create(jugador);
    }

    public Jugador obtenerJugador(int id) {
        return jugadorDao.get(id);
    }

    public List<Jugador> obtenerTodosJugadores() {
        return jugadorDao.getAll();
    }

    public void actualizarJugador(Jugador jugador) {
        jugadorDao.update(jugador);
    }

    public void eliminarJugador(Jugador jugador) {
        jugadorDao.delete(jugador);
    }

    public List<Jugador> buscarJugadoresPorPosicion(String posicion) {
        return jugadorDao.findByPosicion(posicion);
    }

    public Long contarJugadoresPorEquipo(Long equipoId) {
        return jugadorDao.contarJugadoresPorEquipo(equipoId);
    }

    public List<Object[]> getNombreYApellidosJugadores() {
        return jugadorDao.getNombreYApellidos();
    }

    public List<Jugador> getJugadoresOrdenadosPorDorsal() {
        return jugadorDao.getJugadoresOrdenadosPorDorsal();
    }

    public int actualizarEstadoFisicoJugador(Long jugadorId, String estado) {
        return jugadorDao.actualizarEstadoFisico(jugadorId, estado);
    }

    public int eliminarJugadoresSinEquipo() {
        return jugadorDao.eliminarJugadoresSinEquipo();
    }

    public Jugador getPrimerJugadorPorNombre(String nombre) {
        return jugadorDao.getPrimerJugadorPorNombre(nombre);
    }


    
    public Entrenador buscarPorEmail(String email) {
        return entrenadorDao.findByEmail(email);
    }

    public String obtenerNombre(Long id) {
        return entrenadorDao.getNombrePorId(id);
    }

    public Object[] obtenerNombreYEmail(Long id) {
        return entrenadorDao.getNombreYEmail(id);
    }

    public List<Entrenador> entrenadoresPorLicencia(String licencia) {
        return entrenadorDao.getEntrenadoresPorLicencia(licencia);
    }

    public Long totalEntrenadores() {
        return entrenadorDao.contarEntrenadores();
    }

    public Double mediaTelefonos() {
        return entrenadorDao.mediaTelefono();
    }

    public List<Entrenador> listadoOrdenado() {
        return entrenadorDao.getEntrenadoresOrdenadosPorNombre();
    }

    public void cambiarTelefono(Long id, String telefono) {
        entrenadorDao.actualizarTelefono(id, telefono);
    }

    public void eliminarPorEmail(String email) {
        entrenadorDao.borrarPorEmail(email);
    }



    
    public void crearEntrenamiento(Entrenamiento entrenamiento) {
        entrenamientoDao.create(entrenamiento);
    }

    public Entrenamiento obtenerEntrenamiento(int id) {
        return entrenamientoDao.get(id);
    }

    public List<Entrenamiento> obtenerTodosEntrenamientos() {
        return entrenamientoDao.getAll();
    }

    public void actualizarEntrenamiento(Entrenamiento entrenamiento) {
        entrenamientoDao.update(entrenamiento);
    }

    public void eliminarEntrenamiento(Entrenamiento entrenamiento) {
        entrenamientoDao.delete(entrenamiento);
    }


    public List<Entrenamiento> buscarEntrenamientosPorTipo(String tipo) {
        return entrenamientoDao.findByTipo(tipo);
    }

    public List<Entrenamiento> getEntrenamientosOrdenados() {
        return entrenamientoDao.getEntrenamientosOrdenadosPorFecha();
    }

    public Double getPromedioDuracionEntrenamientos() {
        return entrenamientoDao.getPromedioDuracion();
    }

    public List<Entrenamiento> getEntrenamientosFuturos() {
        return entrenamientoDao.getEntrenamientosFuturos();
    }


    public void crearPartido(Partido partido) {
        if (partido.getEquipo() != null && partido.getEquipo().getId() == null) {
            equipoDao.create(partido.getEquipo());
        }
        partidoDao.create(partido);
    }

    public Partido obtenerPartido(int id) {
        return partidoDao.get(id);
    }

    public List<Partido> obtenerTodosPartidos() {
        return partidoDao.getAll();
    }

    public void actualizarPartido(Partido partido) {
        partidoDao.update(partido);
    }

    public void eliminarPartido(Partido partido) {
        partidoDao.delete(partido);
    }


    public List<Partido> buscarPartidosPorRival(String rival) {
        return partidoDao.findByRival(rival);
    }

    public List<Partido> getPartidosOrdenados() {
        return partidoDao.getPartidosOrdenadosPorFecha();
    }

    public Long contarPartidos() {
        return partidoDao.contarPartidos();
    }

    public List<Partido> getPartidosFuturos() {
        return partidoDao.getPartidosFuturos();
    }
}
