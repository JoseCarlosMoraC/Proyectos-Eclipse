package Controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Models.Club;
import Models.Entrenador;
import Models.Entrenamiento;
import Models.Equipo;
import Models.Jugador;
import Models.Partido;
import Service.CoachXService;
import Utils.HibernateUtil;

public class GestionaCoachX {

    private static final Logger logger = LogManager.getLogger(GestionaCoachX.class);
    private CoachXService service;

    public GestionaCoachX() {
        this.service = new CoachXService();
    }

    public static void main(String[] args) {
        GestionaCoachX controller = new GestionaCoachX();

        try {
            logger.info("Sistema CoachX iniciado");
            controller.demoAltas();
            controller.demoActualizaciones();
            controller.demoEliminaciones();
            controller.demoConsultasAvanzadas();
            controller.demoCriteriaBuilder();
        } catch (Exception e) {
            logger.error("Error en la ejecución", e);
        } finally {
            HibernateUtil.shutdown();
            logger.info("Sistema finalizado");
        }
    }

    public void demoAltas() {

        Club club1 = new Club("CD Municipal Gerena", "info@cdmgerena.es");
        club1.setEscudo("escudo_gerena.png");
        club1.setFechaFundacion(LocalDate.of(1985, 6, 15));
        service.crearClub(club1);
        logger.info("Club creado {}", club1);

        Club club2 = new Club("Club Deportivo Sevilla Este", "contacto@sevillaeste.es");
        club2.setFechaFundacion(LocalDate.of(1990, 3, 20));
        service.crearClub(club2);
        logger.info("Club creado {}", club2);

        Equipo equipoJuvenil = new Equipo("Juvenil A", "Juvenil", "2025/2026");
        Equipo equipoInfantil = new Equipo("Infantil B", "Infantil", "2025/2026");
        Equipo equipoCadete = new Equipo("Cadete A", "Cadete", "2025/2026");

        club1.addEquipo(equipoJuvenil);
        club1.addEquipo(equipoInfantil);
        club2.addEquipo(equipoCadete);

        service.actualizarClub(club1);
        service.actualizarClub(club2);

        Jugador jugador1 = new Jugador("Carlos", "García López",
                LocalDate.of(2008, 5, 12), 10, "Delantero");
        jugador1.setEstadoFisico("Disponible");

        Jugador jugador2 = new Jugador("Miguel", "Rodríguez Pérez",
                LocalDate.of(2008, 8, 22), 7, "Centrocampista");
        jugador2.setEstadoFisico("Disponible");

        Jugador jugador3 = new Jugador("Antonio", "Fernández Silva",
                LocalDate.of(2008, 3, 30), 1, "Portero");
        jugador3.setEstadoFisico("Disponible");

        Jugador jugador4 = new Jugador("José", "Martínez Ruiz",
                LocalDate.of(2009, 7, 18), 5, "Defensa");
        jugador4.setEstadoFisico("Lesionado");

        equipoJuvenil.addJugador(jugador1);
        equipoJuvenil.addJugador(jugador2);
        equipoInfantil.addJugador(jugador3);
        equipoCadete.addJugador(jugador4);

        service.actualizarClub(club1);
        service.actualizarClub(club2);

        Entrenador entrenador1 = new Entrenador("Francisco", "Corrales Angelina",
                "paco.corrales@cdmgerena.es", "654321987");
        entrenador1.setLicencia("UEFA B");

        Entrenador entrenador2 = new Entrenador("Antonio", "Polo Márquez",
                "antonio.polo@cdmgerena.es", "612345678");
        entrenador2.setLicencia("UEFA A");

        Entrenador entrenador3 = new Entrenador("Juan", "López Sánchez",
                "juan.lopez@sevillaeste.es", "698765432");
        entrenador3.setLicencia("UEFA C");

        equipoJuvenil.setEntrenador(entrenador1);
        equipoInfantil.setEntrenador(entrenador2);
        equipoCadete.setEntrenador(entrenador3);

        service.actualizarClub(club1);
        service.actualizarClub(club2);

        Entrenamiento entrenamiento1 = new Entrenamiento(
                LocalDateTime.now().plusDays(1), 90, "Técnico", "Control");
        entrenamiento1.setCargaFisica(6);

        Entrenamiento entrenamiento2 = new Entrenamiento(
                LocalDateTime.now().plusDays(3), 120, "Físico", "Resistencia");
        entrenamiento2.setCargaFisica(8);

        jugador1.addEntrenamiento(entrenamiento1);
        jugador2.addEntrenamiento(entrenamiento1);
        jugador1.addEntrenamiento(entrenamiento2);

        service.crearEntrenamiento(entrenamiento1);
        service.crearEntrenamiento(entrenamiento2);

        Partido partido1 = new Partido(
                LocalDateTime.now().plusDays(7),
                "CD Utrera",
                "Gerena");
        partido1.setEquipo(equipoJuvenil);

        service.crearPartido(partido1);

  
    }

    public void demoActualizaciones() {

        Club club = service.buscarClubPorNombre("CD Municipal Gerena");
        if (club != null) {
            club.setContacto("nuevo.contacto@cdmgerena.es");
            service.actualizarClub(club);
        }

        List<Equipo> equipos = service.buscarEquiposPorCategoria("Juvenil");
        if (!equipos.isEmpty()) {
            Equipo equipo = equipos.get(0);
            equipo.setTemporada("2026/2027");
            service.actualizarEquipo(equipo);
        }

        List<Jugador> porteros = service.buscarJugadoresPorPosicion("Portero");
        if (!porteros.isEmpty()) {
            Jugador jugador = porteros.get(0);
            jugador.setEstadoFisico("Recuperación");
            service.actualizarJugador(jugador);
        }
    }

    public void demoEliminaciones() {

        List<Partido> partidos = service.buscarPartidosPorRival("Atlético Alcalá");
        if (!partidos.isEmpty()) {
            service.eliminarPartido(partidos.get(0));
        }

        Jugador jugador = new Jugador("Temporal", "Sin Equipo",
                LocalDate.of(2009, 1, 1), null, "Prueba");
        service.crearJugador(jugador);
        service.eliminarJugador(jugador);
    }

    public void demoConsultasAvanzadas() {

        Club primerClub = service.getPrimerClub();
        logger.info("Primer club {}", primerClub);

        List<String> nombresClub = service.getNombresClub();
        for (int i = 0; i < nombresClub.size(); i++) {
            logger.info(nombresClub.get(i));
        }

        List<Object[]> datosClub = service.getNombreYContactoClub();
        for (int i = 0; i < datosClub.size(); i++) {
            Object[] d = datosClub.get(i);
            logger.info(d[0] + " " + d[1]);
        }

        Club club = service.buscarClubPorNombre("CD Municipal Gerena");
        logger.info("Club encontrado {}", club);

        Long totalClubes = service.contarClubes();
        logger.info("Total clubes {}", totalClubes);

        Double media = service.getPromedioJugadoresPorEquipo();
        logger.info("Promedio jugadores {}", media);

        List<Club> clubesOrdenados = service.getClubesOrdenados();
        for (int i = 0; i < clubesOrdenados.size(); i++) {
            logger.info(clubesOrdenados.get(i).getNombre());
        }

        List<Jugador> jugadoresOrdenados = service.getJugadoresOrdenadosPorDorsal();
        for (int i = 0; i < jugadoresOrdenados.size(); i++) {
            logger.info(jugadoresOrdenados.get(i).getNombre());
        }
    }

    public void demoCriteriaBuilder() {

        Club club = service.getPrimerClub();
        if (club != null) {
            service.actualizarContactoClub(club.getId(), "criteria@club.es");
        }

        Jugador jugador = service.getPrimerJugadorPorNombre("Carlos");
        if (jugador != null) {
            service.actualizarEstadoFisicoJugador(jugador.getId(), "Óptimo");
        }

        service.eliminarJugadoresSinEquipo();
        service.eliminarClubesSinEquipos();
    }

 
    
}
